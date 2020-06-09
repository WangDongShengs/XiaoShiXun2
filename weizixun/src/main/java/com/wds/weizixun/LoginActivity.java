package com.wds.weizixun;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.wds.base.BaseMVPActivity;
import com.wds.bean.LoginBean;
import com.wds.presenter.LoginPresenter;
import com.wds.view.LoginView;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseMVPActivity<LoginPresenter, LoginView> implements LoginView {
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_pass)
    EditText edPass;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.iv_qq)
    ImageView ivQq;
    @BindView(R.id.iv_wx)
    ImageView ivWx;
    @BindView(R.id.iv_sina)
    ImageView ivSina;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_login_im)
    Button btnLoginIm;

    @Override
    protected LoginView initMVPView() {
        return this;
    }

    @Override
    protected LoginPresenter initMVPPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        Toast.makeText(this, "登录成功" + loginBean.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void initView() {
        super.initView();
        toolbar.setTitle("登录");
        setSupportActionBar(toolbar);
        initPermission();
        if (EMClient.getInstance().isLoggedInBefore()){
            goToActivity();
        }
    }

    //Android 6.0 权限适配
    private void initPermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            String[] mp = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, mp, 123);
        }
    }

    @OnClick({R.id.btn_login, R.id.btn_register, R.id.img, R.id.iv_qq, R.id.iv_wx, R.id.iv_sina,R.id.btn_login_im})
    public void onViewClicked(View view) {
        String name = edName.getText().toString().trim();
        String pass = edPass.getText().toString().trim();
        switch (view.getId()) {
            case R.id.btn_login:
                presenter.login(name, pass);
                break;
            case R.id.btn_login_im:
                loginIm(name,pass);
                break;
            case R.id.btn_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.img:
                share();
                break;
            case R.id.iv_qq:
                login(SHARE_MEDIA.QQ);
                break;
            case R.id.iv_wx:
                login(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.iv_sina:
                login(SHARE_MEDIA.SINA);
                break;
        }
    }
    private void goToActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void loginIm(String name, String pass) {
        EMClient.getInstance().login(name, pass, new EMCallBack() {
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        goToActivity();
                    }
                });
            }

            @Override
            public void onError(int i, String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });


    }

    private void share() {
        UMImage umImage = new UMImage(this, "http://ww1.sinaimg.cn/large/0065oQSqly1frjd77dt8zj30k80q2aga.jpg");
        umImage.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，适合普通很大的图
        new ShareAction(LoginActivity.this)
                .withText("小妹妹")//文本
                .withMedia(umImage)//分享的图片
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QZONE)//三方列表
                .setCallback(shareListener)//分享回调
                .open();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(LoginActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };


    public void login(SHARE_MEDIA media) {
        UMShareAPI umShareAPI = UMShareAPI.get(this);
        //media,三方平台
        //authListener,登录回调
        umShareAPI.getPlatformInfo(this, media, authListener);
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            Log.e("TAG", platform.getName() + ":LoginActivity onStart()");
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            logMap(data);
            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();
            Log.e("TAG", platform.getName() + "LoginActivity onComplete()");
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Log.e("TAG", platform.getName() + "LoginActivity onError()");
            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Log.e("TAG", platform.getName() + "LoginActivity onCancel()");
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    private void logMap(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            Log.e("TAG", "logMap: " + entry.getKey() + "," + entry.getValue());
        }
    }


}
