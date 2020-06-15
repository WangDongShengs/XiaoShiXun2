package com.wds.callback;

import com.wds.base.BaseCallBack;
import com.wds.bean.DailyListBean;
import com.wds.bean.SectionListBean;

public interface SectionCallBack extends BaseCallBack<SectionListBean> {
    @Override
    void onSuccess(SectionListBean sectionListBean);

    @Override
    void onFail(String error);
}
