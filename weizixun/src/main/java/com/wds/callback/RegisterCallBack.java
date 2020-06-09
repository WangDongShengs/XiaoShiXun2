package com.wds.callback;

import com.wds.base.BaseCallBack;
import com.wds.bean.RegisterBean;

public interface RegisterCallBack extends BaseCallBack<RegisterBean> {
    @Override
    void onSuccess(RegisterBean registerBean);

    @Override
    void onFail(String error);
}
