package com.wds.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter , V extends BaseView>extends Fragment {

    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayout(), container, false);
        bind = ButterKnife.bind(this, inflate);
        initView();
        initData();
        return inflate;
    }

    private void initData() {

    }

    private void initView() {

    }

    protected abstract int getLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}