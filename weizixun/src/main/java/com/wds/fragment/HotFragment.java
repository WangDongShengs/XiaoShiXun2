package com.wds.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.wds.adapter.HotAdapter;
import com.wds.base.BaseMVPFragment;
import com.wds.bean.HotListBean;
import com.wds.presenter.HotPresenter;
import com.wds.view.HotView;
import com.wds.weizixun.R;

import butterknife.BindView;


public class HotFragment extends BaseMVPFragment<HotPresenter, HotView> implements HotView {
    @BindView(R.id.cl_recycler)
    RecyclerView clRecycler;
    private HotAdapter hotAdapter;

    @Override
    protected void initData() {
        presenter.hotPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        clRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        hotAdapter = new HotAdapter(getActivity());
        clRecycler.setAdapter(hotAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_hot;
    }

    @Override
    protected HotView initMVPView() {
        return this;
    }

    @Override
    protected HotPresenter initMVPPresenter() {
        return new HotPresenter();
    }

    @Override
    public void onSuccess(HotListBean hotListBean) {
    hotAdapter.setRecentBeans(hotListBean.getRecent());
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
