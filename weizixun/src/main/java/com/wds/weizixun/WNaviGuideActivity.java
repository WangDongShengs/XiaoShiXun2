package com.wds.weizixun;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.baidu.mapapi.walknavi.WalkNavigateHelper;
import com.baidu.mapapi.walknavi.adapter.IWNaviStatusListener;
import com.baidu.mapapi.walknavi.adapter.IWRouteGuidanceListener;
import com.baidu.mapapi.walknavi.model.RouteGuideKind;
import com.baidu.platform.comapi.walknavi.WalkNaviModeSwitchListener;

public class WNaviGuideActivity extends Activity {

    private WalkNavigateHelper mNaviHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取WalkNavigateHelper实例
        try {
            mNaviHelper = WalkNavigateHelper.getInstance();
//获取诱导页面地图展示View
            View view = mNaviHelper.onCreate(WNaviGuideActivity.this);
            if (view != null) {
                setContentView(view);
            }
            mNaviHelper.startWalkNavi(WNaviGuideActivity.this);
      } catch (Exception ex) {

        }


        mNaviHelper.setWalkNaviStatusListener(new IWNaviStatusListener() {
            /**
             * 普通步行导航模式和步行AR导航模式的切换
             * 导航模式
             * @param
             * @param walkNaviModeSwitchListener 步行导航模式切换的监听器
             */
            @Override
            public void onWalkNaviModeChange(int mode, WalkNaviModeSwitchListener walkNaviModeSwitchListener) {
                mNaviHelper.switchWalkNaviMode(WNaviGuideActivity.this, mode, walkNaviModeSwitchListener);
            }

            @Override
            public void onNaviExit() {

            }
        });
        mNaviHelper.setRouteGuidanceListener(this, new IWRouteGuidanceListener() {
            //诱导图标更新
            @Override
            public void onRouteGuideIconUpdate(Drawable drawable) {

            }

            //诱导类型枚举
            @Override
            public void onRouteGuideKind(RouteGuideKind routeGuideKind) {

            }

            /**
             * 诱导信息
             * @param charSequence 第一行显示的信息，如“沿当前道路”
             * @param charSequence1  第二行显示的信息，比如“向东出发”，第二行信息也可能为空
             */
            @Override
            public void onRoadGuideTextUpdate(CharSequence charSequence, CharSequence charSequence1) {

            }

            //总的剩余距离
            @Override
            public void onRemainDistanceUpdate(CharSequence charSequence) {

            }

            //总的剩余时间
            @Override
            public void onRemainTimeUpdate(CharSequence charSequence) {

            }

            //GPS状态发生变化，来自诱导引擎的消息
            @Override
            public void onGpsStatusChange(CharSequence charSequence, Drawable drawable) {

            }

            //已经开始偏航
            @Override
            public void onRouteFarAway(CharSequence charSequence, Drawable drawable) {

            }

            //偏航规划中
            @Override
            public void onRoutePlanYawing(CharSequence charSequence, Drawable drawable) {

            }

            //重新算路成功
            @Override
            public void onReRouteComplete() {

            }

            //抵达目的地
            @Override
            public void onArriveDest() {

            }

            @Override
            public void onIndoorEnd(Message message) {

            }

            @Override
            public void onFinalEnd(Message message) {

            }

            //震动
            @Override
            public void onVibrate() {

            }

        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        mNaviHelper.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mNaviHelper.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mNaviHelper.quit();
    }
}
