package com.wds.callback;

import com.wds.base.BaseCallBack;
import com.wds.bean.DailyListBean;
import com.wds.bean.RegisterBean;

public interface DailyCallBack extends BaseCallBack<DailyListBean> {
    @Override
    void onSuccess(DailyListBean dailyListBean);

    @Override
    void onFail(String error);
}
