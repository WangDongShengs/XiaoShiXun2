package com.wds.presenter;

import android.text.TextUtils;

import com.wds.base.BasePresenter;
import com.wds.bean.LoginBean;
import com.wds.callback.LoginCallBack;
import com.wds.model.LoginModel;

public class LoginPresenter extends BasePresenter implements ILoginPresenter, LoginCallBack {

    private LoginModel loginModel;

    @Override
    public void login(String name, String pass) {
        if (TextUtils.isEmpty(name)) {
            view.onFail("name不能为空");
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            view.onFail("pwd不能为空");
            return;
        }
        loginModel.loginModel(name,pass,this);
    }

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
        addModel(loginModel);
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        view.onSuccess(loginBean);
    }

    @Override
    public void onFail(String error) {
        view.onFail(error);
    }
}
