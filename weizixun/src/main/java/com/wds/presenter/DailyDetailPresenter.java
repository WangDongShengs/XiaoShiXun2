package com.wds.presenter;

import com.wds.base.BasePresenter;
import com.wds.bean.DailyDetailBean;
import com.wds.callback.DailyDetailCallBack;
import com.wds.model.DailyDetailModel;

public class DailyDetailPresenter extends BasePresenter implements DailyDetailCallBack {

    private DailyDetailModel dailyDetailModel;
    public void dailyDetailPresenter(int id){
        dailyDetailModel.dailyDetailModel(id,this);
    }
    @Override
    protected void initModel() {
        dailyDetailModel = new DailyDetailModel();
        addModel(dailyDetailModel);
    }

    @Override
    public void onSuccess(DailyDetailBean dailyDetailBean) {
        view.onSuccess(dailyDetailBean);
    }

    @Override
    public void onFail(String error) {
        view.onFail(error);
    }
}
