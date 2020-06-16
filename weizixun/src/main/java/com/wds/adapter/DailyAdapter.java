package com.wds.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wds.bean.DailyListBean;
import com.wds.weizixun.DailyDetailActivity;
import com.wds.weizixun.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter {
    public static final int BANNER_STYLE = 0;
    public static final int LIST_STYLE = 2;
    public static final int DATE=1;
    private Context context;
    private String date;
    private List<DailyListBean.TopStoriesBean> banner = new ArrayList<>();
    private List<DailyListBean.StoriesBean> storyList = new ArrayList<>();
    private List<String> list=new ArrayList<>();

    public DailyAdapter(Context context) {
        this.context = context;
    }

    public void setBanner(List<DailyListBean.TopStoriesBean> banner) {
        this.banner.addAll(banner);
        notifyDataSetChanged();
        if (banner.size()>0&&list.size()==0){
            for (DailyListBean.TopStoriesBean topStoriesBean : banner) {
                list.add(topStoriesBean.getTitle());
            }
        }
    }
    public void setStoryList(List<DailyListBean.StoriesBean> storyList, String date) {
        this.date=date;
        this.storyList.addAll(storyList);
        notifyDataSetChanged();
    }
    public void setBeForeStoryList(List<DailyListBean.StoriesBean> storyList, String date) {
        this.banner.clear();
        this.storyList.clear();
        this.storyList.addAll(storyList);
        this.date=date;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && banner.size() > 0) {
            return BANNER_STYLE;
        } else if ((position==0&banner.size()==0)||(position==1&&banner.size()>0)){
            return DATE;
        }else {
            return LIST_STYLE;
        }

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i){
            case BANNER_STYLE:
                View inflate = LayoutInflater.from(context).inflate(R.layout.layout_banner, viewGroup, false);
                return new BannerViewHolder(inflate);
            case DATE:
                inflate = LayoutInflater.from(context).inflate(R.layout.layout_date, viewGroup, false);
                return new DateViewHolder(inflate);
            case LIST_STYLE:
                inflate = LayoutInflater.from(context).inflate(R.layout.layout_list, viewGroup, false);
                return new ListViewHolder(inflate);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);
        switch (itemViewType){
            case BANNER_STYLE:
                BannerViewHolder viewHolders = (BannerViewHolder) viewHolder;
                viewHolders.banner.setImages(banner)
                        .setBannerTitles(list)
                        .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                        .setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                DailyListBean.TopStoriesBean bean = (DailyListBean.TopStoriesBean) path;
                                Glide.with(context).load(bean.getImage()).into(imageView);
                            }
                        }).start();
                break;
            case DATE:
                DateViewHolder dateViewHolder = (DateViewHolder) viewHolder;
                dateViewHolder.text.setText(date);
                break;
            case LIST_STYLE:
                if (banner.size()>0){
                    i=i-1;
                }
                //i-1 第二个布局永远都在，所有i-1
                DailyListBean.StoriesBean storiesBean = storyList.get(i-1);
                ListViewHolder listViewHolder = (ListViewHolder) viewHolder;
                listViewHolder.title.setText(storiesBean.getTitle());
                Glide.with(context).load(storiesBean.getImages().get(0)).into(listViewHolder.image);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DailyDetailActivity.class);
                        intent.putExtra("ID",storiesBean.getId());
                        context.startActivity(intent);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        int size=banner.size()>0 ? storyList.size()+1: storyList.size();
        //不管banner 是否存在，第二个布局都存在 storyList.size()要加1；
        return size+1;
    }

    private class BannerViewHolder extends RecyclerView.ViewHolder {
        Banner banner;
        public BannerViewHolder(View inflate) {
            super(inflate);
            banner=inflate.findViewById(R.id.banner);
        }
    }

    private class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        public ListViewHolder(View inflate) {
            super(inflate);
            image=inflate.findViewById(R.id.iv_daily_item_image);
            title=inflate.findViewById(R.id.tv_daily_item_title);
        }
    }

    private class DateViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        public DateViewHolder(View inflate) {
            super(inflate);
            text=inflate.findViewById(R.id.tv_daily_item_text);
        }
    }
}
