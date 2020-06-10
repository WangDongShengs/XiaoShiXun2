package com.wds.weizixun;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wds.base.BaseActivity;
import com.wds.fragment.ContactsFragment;
import com.wds.fragment.ConversationFragment;
import com.wds.fragment.DiscoveryFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.tab_main)
    TabLayout tabMain;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.nv_main)
    NavigationView nvMain;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    private FragmentManager supportFragmentManager;
    private ArrayList<String> list;
    private int slatPosition;
    private ArrayList<Fragment> frame;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {
                int right = nvMain.getRight();
                llMain.setX(right);
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
        nvMain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.zhihu:
                        break;
                    case R.id.weixing:
                        break;
                    case R.id.qunliao:
                    startActivity(new Intent(MainActivity.this,ChatGroupActivity.class));
                        break;
                }
                return false;
            }
        });
        ImageView img = nvMain.getHeaderView(0).findViewById(R.id.iv_cl_header);
        TextView text = nvMain.getHeaderView(0).findViewById(R.id.tv_cl_header);
        RequestOptions requestOptions = new RequestOptions().circleCrop();
        Glide.with(this).load(R.drawable.hander_image).apply(requestOptions).into(img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast("点击头像");
            }
        });
       /* text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });*/
        supportFragmentManager = getSupportFragmentManager();
        initTabTitles();
        initTabs();
        initFragments();
        showDefaultFragment();
    }

    private void showDefaultFragment() {
        switchFragment(0);
    }

    private void switchFragment(int position) {
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        Fragment curFragment=frame.get(position);
        Fragment lastFragment=frame.get(slatPosition);
        if (!curFragment.isAdded()){
            fragmentTransaction.add(R.id.fl_container,curFragment);
        }
        fragmentTransaction.hide(lastFragment).show(curFragment).commit();
        slatPosition=position;
    }

    private void initFragments() {
        frame = new ArrayList<>();
        frame.add(new ConversationFragment());
        frame.add(new ContactsFragment());
        frame.add(new DiscoveryFragment());
    }

    private void initTabs() {
        for (int i = 0; i < list.size(); i++) {
            tabMain.addTab(tabMain.newTab().setText(list.get(i)));
        }
    }

    private void initTabTitles() {
        list = new ArrayList<>();
        list.add(getResources().getString(R.string.conversation));
        list.add(getResources().getString(R.string.contacts));
        list.add(getResources().getString(R.string.discovery));
    }

    @Override
    protected void initData() {
        super.initData();
        tabMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switchFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
