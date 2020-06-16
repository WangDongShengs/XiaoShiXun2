package com.wds.view;

import com.wds.base.BaseCallBack;
import com.wds.base.BaseView;
import com.wds.bean.DailyDetailBean;


public interface DailyDetailView extends BaseView<DailyDetailBean> {
    @Override
    void onSuccess(DailyDetailBean dailyDetailBean);

    @Override
    void onFail(String error);
}
