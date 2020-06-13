package com.wds.view;

import com.wds.base.BaseCallBack;
import com.wds.base.BaseView;
import com.wds.bean.DailyListBean;

public interface DailyView extends BaseView<DailyListBean> {
    @Override
    void onSuccess(DailyListBean dailyListBean);

    @Override
    void onFail(String error);
}
