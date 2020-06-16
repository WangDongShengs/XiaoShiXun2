package com.wds.model;

import com.wds.api.ApiService;
import com.wds.base.BaseModel;
import com.wds.base.BaseObserver;
import com.wds.bean.DailyDetailBean;
import com.wds.callback.DailyDetailCallBack;
import com.wds.utils.HttpManager;
import com.wds.utils.RxUtil;

import io.reactivex.FlowableSubscriber;

public class DailyDetailModel extends BaseModel {
    public void dailyDetailModel(int id, DailyDetailCallBack callBack) {
        HttpManager.getHttpManager().getService(ApiService.zhihuUrl,ApiService.class).getDetailList(id)
                .compose(RxUtil.rxFlowableTransformer())
                .subscribe((FlowableSubscriber<? super DailyDetailBean>) new BaseObserver<DailyDetailBean>() {
                    @Override
                    public void onSuccess(DailyDetailBean dailyDetailBean) {
                        callBack.onSuccess(dailyDetailBean);
                    }
                });
    }
}
