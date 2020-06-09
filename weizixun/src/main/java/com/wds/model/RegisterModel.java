package com.wds.model;

import com.wds.api.ApiService;
import com.wds.base.BaseModel;
import com.wds.base.BaseObserver;
import com.wds.bean.RegisterBean;
import com.wds.callback.RegisterCallBack;
import com.wds.utils.HttpManager;
import com.wds.utils.RxUtil;

import io.reactivex.FlowableSubscriber;

public class RegisterModel extends BaseModel {
    public void register(String userid,
                         String psd,
                         String accessToken,
                         String typeid,
                         final RegisterCallBack callback) {
        HttpManager.getHttpManager().getService(ApiService.baseUrl, ApiService.class)
                .register(userid, psd, accessToken, typeid)
                .compose(RxUtil.<RegisterBean>rxFlowableTransformer())
                .subscribe((FlowableSubscriber<? super RegisterBean>) new BaseObserver<RegisterBean>() {
                    @Override
                    public void onSuccess(RegisterBean registerBean) {
                        callback.onSuccess(registerBean);
                    }
                });
    }
}
