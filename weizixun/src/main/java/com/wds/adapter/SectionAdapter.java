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
import com.wds.bean.SectionListBean;
import com.wds.weizixun.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {
    private Context context;
    private List<SectionListBean.DataBean> dataBeans = new ArrayList<>();

    public SectionAdapter(Context context) {
        this.context = context;
    }

    public void setDataBeans(List<SectionListBean.DataBean> dataBeans) {
        this.dataBeans.addAll(dataBeans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_item, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SectionListBean.DataBean dataBean = dataBeans.get(i);
        viewHolder.tvClSection.setText(dataBean.getName());
        Glide.with(context).load(dataBean.getThumbnail()).into(viewHolder.ivClSection);
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cl_section)
        ImageView ivClSection;
        @BindView(R.id.tv_cl_section)
        TextView tvClSection;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
