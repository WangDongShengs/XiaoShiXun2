package com.wds.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        initMVP();
        initView();
        initData();
        initReceiver();
        initHistory();
    }

    protected void initHistory() {

    }

    protected void initReceiver() {

    }

    protected void initMVP() {

    }


    protected void initData() {

    }

    protected void initView() {

    }

    protected abstract int getLayout();


    protected void Toast(String mag){
        Toast.makeText(this, mag, Toast.LENGTH_SHORT).show();
    }
}
