package com.wds.view;

import com.wds.base.BaseView;
import com.wds.bean.RegisterBean;

public interface RegisterView extends BaseView<RegisterBean> {
        void onSuccess(RegisterBean RegisterBean);
        void onFail(String error);
}
