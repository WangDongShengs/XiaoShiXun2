package com.wds.presenter;



import com.wds.base.BasePresenter;
import com.wds.bean.DailyListBean;
import com.wds.callback.DailyCallBack;
import com.wds.model.DailyModel;


public class DailyPresenter extends BasePresenter implements DailyCallBack {


    private DailyModel dailyModel;

    public void dailyPresenter() {
        dailyModel.dailyModel(this);
    }
    @Override
    protected void initModel() {
        dailyModel = new DailyModel();
        addModel(dailyModel);
    }


    @Override
    public void onSuccess(DailyListBean dailyListBean) {
        view.onSuccess(dailyListBean);
    }

    @Override
    public void onFail(String error) {
        view.onFail(error);
    }
}
