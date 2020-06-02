package com.wds.view;

import com.wds.base.BaseView;
import com.wds.bean.LoginBean;

public interface LoginView extends BaseView<LoginBean> {
    void onSuccess(LoginBean loginBean);
    void onFail(String error);
}
