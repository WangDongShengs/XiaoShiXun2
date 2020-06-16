package com.wds.callback;

import com.wds.base.BaseCallBack;
import com.wds.bean.DailyDetailBean;


public interface DailyDetailCallBack extends BaseCallBack<DailyDetailBean> {
    @Override
    void onSuccess(DailyDetailBean dailyDetailBean);

    @Override
    void onFail(String error);
}
