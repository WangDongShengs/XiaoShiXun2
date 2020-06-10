package com.wds.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.chat.EMConversation;
import com.wds.weizixun.R;

import java.util.List;


public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ViewHolder> {

    private List<EMConversation> list;
    private Context context;

    public ConversationAdapter(List<EMConversation> list, Context context) {
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
        EMConversation emConversation = list.get(i);
        String name = emConversation.conversationId();
        viewHolder.tvItemUsers.setText(name);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickItem!=null){
                    onClickItem.item(i);
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

    public void setOnClickItem(ConversationAdapter.onClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public interface onClickItem{
        void item(int i);
    }
}
