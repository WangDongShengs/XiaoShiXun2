package com.wds.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wds.adapter.SectionAdapter;
import com.wds.base.BaseMVPFragment;
import com.wds.bean.SectionListBean;
import com.wds.presenter.SectionPresenter;
import com.wds.view.SectionView;
import com.wds.weizixun.R;


import butterknife.BindView;



public class SectionFragment extends BaseMVPFragment<SectionPresenter, SectionView> implements SectionView {


    @BindView(R.id.cl_recycler)
    RecyclerView clRecycler;
    private SectionAdapter sectionAdapter;

    @Override
    protected int getLayout() {
        return R.layout.layout_section;
    }

    @Override
    protected SectionView initMVPView() {
        return this;
    }
    @Override
    protected SectionPresenter initMVPPresenter() {
        return new SectionPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        clRecycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
        sectionAdapter = new SectionAdapter(getActivity());
        clRecycler.setAdapter(sectionAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        presenter.sectionPresenter();
    }

    @Override
    public void onSuccess(SectionListBean sectionListBean) {
        sectionAdapter.setDataBeans(sectionListBean.getData());
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }


}
