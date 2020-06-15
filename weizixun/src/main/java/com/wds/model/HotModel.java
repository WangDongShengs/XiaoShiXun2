package com.wds.model;

import com.wds.api.ApiService;
import com.wds.base.BaseModel;
import com.wds.base.BaseObserver;
import com.wds.bean.HotListBean;
import com.wds.callback.HotCallBack;
import com.wds.utils.HttpManager;
import com.wds.utils.RxUtil;

import io.reactivex.FlowableSubscriber;

public class HotModel extends BaseModel {
    public void hotModel(HotCallBack hotCallBack) {
        HttpManager.getHttpManager().getService(ApiService.zhihuUrl,ApiService.class).getHotList()
                .compose(RxUtil.rxFlowableTransformer())
                .subscribe((FlowableSubscriber<? super HotListBean>) new BaseObserver<HotListBean>() {
                    @Override
                    public void onSuccess(HotListBean hotListBean) {
                        hotCallBack.onSuccess(hotListBean);
                    }
                });
    }
}
