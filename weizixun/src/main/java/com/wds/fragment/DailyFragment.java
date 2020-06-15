package com.wds.fragment;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.wds.adapter.DailyAdapter;
import com.wds.base.BaseMVPFragment;
import com.wds.bean.DailyListBean;
import com.wds.presenter.DailyPresenter;
import com.wds.view.DailyView;
import com.wds.weizixun.CalenderActivity;
import com.wds.weizixun.R;

import butterknife.BindView;

import butterknife.OnClick;


public class DailyFragment extends BaseMVPFragment<DailyPresenter, DailyView> implements DailyView {


    @BindView(R.id.cl_recycler)
    RecyclerView clRecycler;
    @BindView(R.id.cl_fab)
    FloatingActionButton clFab;

    private DailyAdapter dailyAdapter;
    private String date;

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
        if (dailyListBean.getTop_stories()==null){
            dailyAdapter.setBeForeStoryList(dailyListBean.getStories(),date);
        }else {
            dailyAdapter.setBanner(dailyListBean.getTop_stories());
            dailyAdapter.setStoryList(dailyListBean.getStories(), "知乎日报");
        }
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.cl_fab)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), CalenderActivity.class);
        startActivityForResult(intent,100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100){
            date = data.getStringExtra("date");
            presenter.dailyBeforePresenter(date);
        }
    }
}
