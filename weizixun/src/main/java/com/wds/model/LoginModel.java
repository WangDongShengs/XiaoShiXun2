package com.wds.model;

import com.wds.api.ApiService;
import com.wds.base.BaseModel;
import com.wds.base.BaseObserver;
import com.wds.bean.LoginBean;
import com.wds.callback.LoginCallBack;
import com.wds.utils.HttpManager;
import com.wds.utils.RxUtil;

import org.reactivestreams.Subscriber;

import io.reactivex.FlowableSubscriber;


public class LoginModel extends BaseModel {

    public void loginModel(String name, String pass, final LoginCallBack loginCallBack) {
        HttpManager.getHttpManager()
                .getService(ApiService.baseUrl, ApiService.class)
                .login(name, pass)
                .compose(RxUtil.<LoginBean>rxFlowableTransformer())
                .subscribe((FlowableSubscriber<? super LoginBean>) new BaseObserver<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean loginBean) {
                        String errorCode = loginBean.getCode();
                        if (errorCode.equals("200")) {
                            loginCallBack.onSuccess(loginBean);
                        } else {
                            loginCallBack.onFail(loginBean.getMessage());
                        }
                    }
                });
    }
}
