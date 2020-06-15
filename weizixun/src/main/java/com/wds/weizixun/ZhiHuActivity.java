package com.wds.weizixun;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.wds.base.BaseActivity;
import com.wds.fragment.DailyFragment;
import com.wds.fragment.HotFragment;
import com.wds.fragment.SectionFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class ZhiHuActivity extends BaseActivity {
    @BindView(R.id.cl_tab)
    TabLayout clTab;
    @BindView(R.id.cl_viewPager)
    ViewPager clViewPager;

    @Override
    protected int getLayout() {
        return R.layout.activity_zhi_hu;
    }

    @Override
    protected void initView() {
        super.initView();
        final ArrayList<Fragment> list = new ArrayList<>();
        list.add(new DailyFragment());
        list.add(new SectionFragment());
        list.add(new HotFragment());
        clViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        clTab.setupWithViewPager(clViewPager);
        clTab.getTabAt(0).setText("日报");
        clTab.getTabAt(1).setText("专栏");
        clTab.getTabAt(2).setText("热门");
    }
}
