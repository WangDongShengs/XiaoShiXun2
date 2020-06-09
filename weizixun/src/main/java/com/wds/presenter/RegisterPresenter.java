package com.wds.presenter;

import android.text.TextUtils;

import com.wds.base.BasePresenter;
import com.wds.bean.RegisterBean;
import com.wds.callback.RegisterCallBack;
import com.wds.model.RegisterModel;

public class RegisterPresenter extends BasePresenter implements RegisterCallBack {

    private RegisterModel registerModel;
    public void register(String userid,
                         String psd,
                         String accessToken,
                         String typeid) {

        if (TextUtils.isEmpty(userid)) {
            view.onFail("账号不能为空");
            return;
        }

        if (TextUtils.isEmpty(psd)) {
            view.onFail("密码不能为空");
            return;
        }

        registerModel.register(userid, psd, accessToken, typeid, this);
    }
    @Override
    protected void initModel() {
        registerModel = new RegisterModel();
        addModel(registerModel);
    }

    @Override
    public void onSuccess(RegisterBean registerBean) {
        view.onSuccess(registerBean);
    }

    @Override
    public void onFail(String error) {
            view.onFail(error);
    }
}
