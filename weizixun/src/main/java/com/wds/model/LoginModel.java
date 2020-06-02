package com.wds.model;

import com.wds.api.ApiService;
import com.wds.base.BaseModel;
import com.wds.base.BaseObserver;
import com.wds.bean.LoginBean;
import com.wds.callback.LoginCallBack;
import com.wds.utils.HttpManager;
import com.wds.utils.RxUtil;

import org.reactivestreams.Subscriber;


public class LoginModel extends BaseModel implements ILoginModel {
    @Override
    public void loginModel(String name, String pass, final LoginCallBack loginCallBack) {
        HttpManager.getHttpManager()
                .getService(ApiService.loginUrl,ApiService.class)
                .getUrl(name,pass)
                .compose(RxUtil.<LoginBean>rxFlowableTransformer())
                .subscribe((Subscriber<? super LoginBean>) new BaseObserver<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean loginBean) {
                        int errorCode = loginBean.getErrorCode();
                        if (errorCode==0){
                            loginCallBack.onSuccess(loginBean);
                        }else {
                            loginCallBack.onFail("账户密码错误");
                        }
                    }
                });
    }
}
