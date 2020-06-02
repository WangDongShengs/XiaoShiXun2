package com.wds.model;

import com.wds.callback.LoginCallBack;

public interface ILoginModel {
    void loginModel(String name, String pass, LoginCallBack loginCallBack);
}
