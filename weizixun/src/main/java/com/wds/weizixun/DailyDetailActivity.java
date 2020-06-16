package com.wds.weizixun;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wds.base.BaseMVPActivity;
import com.wds.bean.DailyDetailBean;
import com.wds.presenter.DailyDetailPresenter;
import com.wds.view.DailyDetailView;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyDetailActivity extends BaseMVPActivity<DailyDetailPresenter, DailyDetailView> implements DailyDetailView {

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.html_text)
    HtmlTextView htmlText;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.app_ctl)
    CollapsingToolbarLayout appCtl;
    private int id;


    @Override
    protected int getLayout() {
        return R.layout.activity_daily_detail;
    }

    @Override
    protected void initView() {
        super.initView();
        id = getIntent().getIntExtra("ID", 0);
        presenter.dailyDetailPresenter(id);
        //张开时候标题颜色
        appCtl.setExpandedTitleColor(Color.BLUE);
        //折叠时候标题颜色
        appCtl.setCollapsedTitleTextColor(Color.WHITE);
    }

    @Override
    protected void initData() {
        super.initData();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(fab, "我是Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("信息", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(DailyDetailActivity.this, "点击信息", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    protected DailyDetailView initMVPView() {
        return this;
    }

    @Override
    protected DailyDetailPresenter initMVPPresenter() {
        return new DailyDetailPresenter();
    }

    @Override
    public void onSuccess(DailyDetailBean dailyDetailBean) {
        toolbar.setTitle(dailyDetailBean.getTitle());
        setSupportActionBar(toolbar);
        Glide.with(this).load(dailyDetailBean.getImage()).into(img);
        htmlText.setHtml(dailyDetailBean.getBody(), new HtmlHttpImageGetter(htmlText));

    }

    @Override
    public void onFail(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

}
