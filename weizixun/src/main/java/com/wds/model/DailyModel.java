package com.wds.model;


import com.wds.api.ApiService;
import com.wds.base.BaseModel;
import com.wds.base.BaseObserver;
import com.wds.bean.DailyListBean;
import com.wds.callback.DailyCallBack;
import com.wds.utils.HttpManager;
import com.wds.utils.RxUtil;


import io.reactivex.FlowableSubscriber;

public class DailyModel extends BaseModel {
    public void dailyModel(DailyCallBack dailyCallBack) {
        HttpManager.getHttpManager().getService(ApiService.zhihuUrl,ApiService.class).getLatestList()
                .compose(RxUtil.rxFlowableTransformer())
                .subscribe((FlowableSubscriber<? super DailyListBean>) new BaseObserver<DailyListBean>() {
                    @Override
                    public void onSuccess(DailyListBean dailyListBean) {
                        dailyCallBack.onSuccess(dailyListBean);
                    }
                });
    }
}
