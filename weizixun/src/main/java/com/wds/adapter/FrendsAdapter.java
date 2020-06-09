package com.wds.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wds.weizixun.R;

import java.util.List;


public class FrendsAdapter extends RecyclerView.Adapter<FrendsAdapter.ViewHolder> {

    private List<String> list;
    private Context context;

    public FrendsAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_users, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String string = list.get(i);
        viewHolder.tvItemUsers.setText(string);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickItem!=null){
                    onClickItem.item(i,string);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivItemUsers;
        TextView tvItemUsers;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           tvItemUsers=itemView.findViewById(R.id.tv_item_users);
           ivItemUsers=itemView.findViewById(R.id.iv_item_users);
        }
    }
    onClickItem onClickItem;

    public void setOnClickItem(FrendsAdapter.onClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public interface onClickItem{
        void item(int i, String name);
    }
}
