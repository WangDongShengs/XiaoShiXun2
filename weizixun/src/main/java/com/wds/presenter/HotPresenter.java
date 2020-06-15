package com.wds.presenter;

import com.wds.base.BasePresenter;
import com.wds.bean.HotListBean;
import com.wds.callback.HotCallBack;
import com.wds.model.HotModel;

public class HotPresenter extends BasePresenter implements HotCallBack {

    private HotModel hotModel;
    public void hotPresenter(){
        hotModel.hotModel(this);
    }
    @Override
    protected void initModel() {
        hotModel = new HotModel();
        addModel(hotModel);
    }

    @Override
    public void onSuccess(HotListBean hotListBean) {
        view.onSuccess(hotListBean);
    }

    @Override
    public void onFail(String error) {
        view.onFail(error);
    }
}
