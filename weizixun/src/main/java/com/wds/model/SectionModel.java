package com.wds.model;

import com.wds.api.ApiService;
import com.wds.base.BaseModel;
import com.wds.base.BaseObserver;
import com.wds.bean.SectionListBean;
import com.wds.callback.SectionCallBack;
import com.wds.utils.HttpManager;
import com.wds.utils.RxUtil;

import io.reactivex.FlowableSubscriber;

public class SectionModel extends BaseModel {
    public void sectionModel(SectionCallBack sectionCallBack){
        HttpManager.getHttpManager().getService(ApiService.zhihuUrl,ApiService.class).getSectionList()
                .compose(RxUtil.rxFlowableTransformer())
                .subscribe((FlowableSubscriber<? super SectionListBean>) new BaseObserver<SectionListBean>() {
                    @Override
                    public void onSuccess(SectionListBean sectionListBean) {
                        sectionCallBack.onSuccess(sectionListBean);
                    }
                });
    }
}
