package com.wds.weizixun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wds.base.BaseActivity;

public class DailyDetailActivity extends BaseActivity{

    private int id;
    private String title;
    private String image;

    @Override
    protected int getLayout() {
        return R.layout.activity_daily_detail;
    }

    @Override
    protected void initView() {
        super.initView();
        id = getIntent().getIntExtra("ID", 0);
        title = getIntent().getStringExtra("title");
        image = getIntent().getStringExtra("image");

    }
}
