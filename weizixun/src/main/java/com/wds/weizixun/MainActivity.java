package com.wds.weizixun;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wds.base.BaseActivity;
import com.wds.base.BaseMVPActivity;
import com.wds.bean.LoginBean;
import com.wds.presenter.LoginPresenter;
import com.wds.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseMVPActivity<LoginPresenter, LoginView> implements LoginView {
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_pass)
    EditText edPass;
    @BindView(R.id.login)
    Button login;

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
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        Toast.makeText(this, "登录成功" + loginBean.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.login)
    public void onViewClicked() {
        String name = edName.getText().toString().trim();
        String pass = edPass.getText().toString().trim();
        presenter.login(name,pass);
    }
}
