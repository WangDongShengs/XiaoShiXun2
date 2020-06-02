package com.wds.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter , V extends BaseView> extends AppCompatActivity {
    protected P presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        presenter = initMVPPresenter();
        if (presenter!=null){
            presenter.setView(initMVPView());
        }
        initView();
        initData();
    }

    protected abstract V initMVPView();

    protected abstract P initMVPPresenter();

    private void initData() {

    }

    private void initView() {

    }

    protected abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
    protected void Tost(String tost){
        Toast.makeText(this, tost, Toast.LENGTH_SHORT).show();
    }
}
