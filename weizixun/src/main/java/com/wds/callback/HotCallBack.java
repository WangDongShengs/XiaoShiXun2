package com.wds.callback;

import com.wds.base.BaseCallBack;
import com.wds.bean.HotListBean;
import com.wds.bean.SectionListBean;

public interface HotCallBack extends BaseCallBack<HotListBean> {
    @Override
    void onSuccess(HotListBean hotListBean);

    @Override
    void onFail(String error);
}
