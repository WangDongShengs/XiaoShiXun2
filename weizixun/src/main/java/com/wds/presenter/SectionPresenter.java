package com.wds.presenter;

import com.wds.base.BasePresenter;
import com.wds.bean.SectionListBean;
import com.wds.callback.SectionCallBack;
import com.wds.model.SectionModel;

public class SectionPresenter extends BasePresenter implements SectionCallBack {

    private SectionModel sectionModel;
    public void sectionPresenter(){
        sectionModel.sectionModel(this);
    }
    @Override
    protected void initModel() {
        sectionModel = new SectionModel();
        addModel(sectionModel);
    }

    @Override
    public void onSuccess(SectionListBean sectionListBean) {
        view.onSuccess(sectionListBean);
    }

    @Override
    public void onFail(String error) {
        view.onFail(error);
    }
}
