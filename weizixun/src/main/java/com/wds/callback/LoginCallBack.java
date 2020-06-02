package com.wds.callback;

import com.wds.base.BaseCallBack;
import com.wds.bean.LoginBean;

public interface LoginCallBack extends BaseCallBack<LoginBean> {
    void onSuccess(LoginBean loginBean);
    void onFail(String error);
}
