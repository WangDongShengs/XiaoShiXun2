package com.wds.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wds.adapter.DailyAdapter;
import com.wds.base.BaseMVPFragment;
import com.wds.bean.DailyListBean;
import com.wds.presenter.DailyPresenter;
import com.wds.view.DailyView;
import com.wds.weizixun.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DailyFragment extends BaseMVPFragment<DailyPresenter, DailyView> implements DailyView {


    @BindView(R.id.cl_recycler)
    RecyclerView clRecycler;
    private DailyAdapter dailyAdapter;

    @Override
    protected int getLayout() {
        return R.layout.layout_daily;
    }

    @Override
    protected DailyView initMVPView() {
        return this;
    }

    @Override
    protected DailyPresenter initMVPPresenter() {
        return new DailyPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        clRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        dailyAdapter = new DailyAdapter(getActivity());
        clRecycler.setAdapter(dailyAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        presenter.dailyPresenter();
    }

    @Override
    public void onSuccess(DailyListBean dailyListBean) {
        List<DailyListBean.TopStoriesBean> top_stories = dailyListBean.getTop_stories();
        List<DailyListBean.StoriesBean> stories = dailyListBean.getStories();
        dailyAdapter.setBanner(top_stories);
        dailyAdapter.setStoryList(stories,"知乎日报");
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
