package com.wds.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wds.adapter.ContactsAdapter;
import com.wds.utils.Constants;
import com.wds.utils.SharedPreferencesUtils;
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
    private ContactsAdapter contactsAdapter;

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
        String  curName = (String) SharedPreferencesUtils.getParam(getActivity(), Constants.NAME, "a");
        ArrayList<String> list = new ArrayList<>();
        if (!curName.equals("a")){
            list.add("a");
        }  if (!curName.equals("b")){
            list.add("b");
        }  if (!curName.equals("c")){
            list.add("c");
        }
        if (lists.size()<=0){
        lists.addAll(list);
        }
        contactsAdapter.notifyDataSetChanged();
    }

    private void initView() {
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        lists = new ArrayList<>();
        contactsAdapter = new ContactsAdapter(lists, getActivity());
        recycler.setAdapter(contactsAdapter);
        contactsAdapter.setOnClickItem(new ContactsAdapter.onClickItem() {
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
