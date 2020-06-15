package com.wds.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wds.bean.HotListBean;
import com.wds.weizixun.R;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder> {
    private Context context;
    private List<HotListBean.RecentBean> recentBeans = new ArrayList<>();

    public HotAdapter(Context context) {
        this.context = context;
    }

    public void setRecentBeans(List<HotListBean.RecentBean> recentBeans) {
        this.recentBeans.addAll(recentBeans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.hot_item, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        HotListBean.RecentBean recentBean = recentBeans.get(i);
        viewHolder.tvClHot.setText(recentBean.getTitle());
        Glide.with(context).load(recentBean.getThumbnail()).into(viewHolder.ivClHot);
    }

    @Override
    public int getItemCount() {
        return recentBeans.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cl_hot)
        ImageView ivClHot;
        @BindView(R.id.tv_cl_hot)
        TextView tvClHot;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
