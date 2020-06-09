package com.wds.fragment;


import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wds.adapter.FrendsAdapter;
import com.wds.utils.Constants;
import com.wds.weizixun.ChatActivity;
import com.wds.weizixun.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {


    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;
    private ArrayList<String> lists;
    private FrendsAdapter frendsAdapter;

    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_contacts, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        initData();
        return inflate;

    }

    private void initData() {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        if (lists.size()<=0){
        lists.addAll(list);
        }
        frendsAdapter.notifyDataSetChanged();
    }

    private void initView() {
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        lists = new ArrayList<>();
        frendsAdapter = new FrendsAdapter(lists, getActivity());
        recycler.setAdapter(frendsAdapter);
        frendsAdapter.setOnClickItem(new FrendsAdapter.onClickItem() {
            @Override
            public void item(int i, String name) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra(Constants.NAME,name);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
