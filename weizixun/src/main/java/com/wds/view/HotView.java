package com.wds.view;

import com.wds.base.BaseCallBack;
import com.wds.base.BaseView;
import com.wds.bean.HotListBean;

public interface HotView extends BaseView<HotListBean> {
    @Override
    void onSuccess(HotListBean hotListBean);

    @Override
    void onFail(String error);
}
