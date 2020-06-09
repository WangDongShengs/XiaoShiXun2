package com.wds.weizixun;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.wds.base.BaseMVPActivity;
import com.wds.bean.RegisterBean;
import com.wds.presenter.RegisterPresenter;
import com.wds.view.RegisterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseMVPActivity<RegisterPresenter, RegisterView> implements RegisterView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_pass)
    EditText edPass;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.btn_register_im)
    Button btnRegisterIm;

    @Override
    protected RegisterView initMVPView() {
        return this;
    }

    @Override
    protected RegisterPresenter initMVPPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        super.initView();
        toolbar.setTitle("注册");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.video_webview_bar_back_d);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onSuccess(RegisterBean RegisterBean) {
        if (RegisterBean.getCode().equals("200")) {
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.btn_register, R.id.btn_register_im})
    public void onViewClicked(View view) {
        String name = edName.getText().toString().trim();
        String pwd = edPass.getText().toString().trim();
        switch (view.getId()) {
            case R.id.btn_register:
                presenter.register(name, pwd, "", "");
                break;
            case R.id.btn_register_im:
                register(name,pwd);
                break;
        }
    }

    private void register(final String name, final String pwd) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //注册失败会抛出HyphenateException
                try {
                    EMClient.getInstance().createAccount(name, pwd);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (final HyphenateException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity.this, "注册失败："+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
