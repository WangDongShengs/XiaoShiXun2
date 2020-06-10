package com.wds.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.wds.adapter.ConversationAdapter;
import com.wds.utils.Constants;
import com.wds.weizixun.ChatActivity;
import com.wds.weizixun.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ConversationFragment extends Fragment {

    @BindView(R.id.cl_rv)
    RecyclerView clRv;
    private Unbinder bind;
    private ArrayList<EMConversation> list;
    private ConversationAdapter conversationAdapter;

    public ConversationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_conversation, container, false);
        bind = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    private void initView() {
        clRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        conversationAdapter = new ConversationAdapter(list, getActivity());
        clRv.setAdapter(conversationAdapter);
        conversationAdapter.setOnClickItem(new ConversationAdapter.onClickItem() {
            @Override
            public void item(int i) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                String name = list.get(i).conversationId();
                intent.putExtra(Constants.NAME,name);
                startActivity(intent);
            }
        });
    }
    private void initData() {
        List<EMConversation> emConversations = loadConversationList();
        list.addAll(emConversations);
        conversationAdapter.notifyDataSetChanged();

    }
    /**
     * load conversation list
     *
     * @return
    +    */
    protected List<EMConversation> loadConversationList(){
        // get all conversations
        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        List<Pair<Long, EMConversation>> sortList = new ArrayList<Pair<Long, EMConversation>>();
        /**
         * lastMsgTime will change if there is new message during sorting
         * so use synchronized to make sure timestamp of last message won't change.
         */
        synchronized (conversations) {
            for (EMConversation conversation : conversations.values()) {
                if (conversation.getAllMessages().size() != 0) {
                    sortList.add(new Pair<Long, EMConversation>(conversation.getLastMessage().getMsgTime(), conversation));
                }
            }
        }
        try {
            // Internal is TimSort algorithm, has bug
            sortConversationByLastChatTime(sortList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<EMConversation> list = new ArrayList<EMConversation>();
        for (Pair<Long, EMConversation> sortItem : sortList) {
            list.add(sortItem.second);
        }
        return list;
    }

    /**
     * sort conversations according time stamp of last message
     *
     * @param conversationList
     */
    private void sortConversationByLastChatTime(List<Pair<Long, EMConversation>> conversationList) {
        Collections.sort(conversationList, new Comparator<Pair<Long, EMConversation>>() {
            @Override
            public int compare(final Pair<Long, EMConversation> con1, final Pair<Long, EMConversation> con2) {

                if (con1.first.equals(con2.first)) {
                    return 0;
                } else if (con2.first.longValue() > con1.first.longValue()) {
                    return 1;
                } else {
                    return -1;
                }
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }
}
