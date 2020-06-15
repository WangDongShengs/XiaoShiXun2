package com.wds.view;

import com.wds.base.BaseCallBack;
import com.wds.base.BaseView;
import com.wds.bean.SectionListBean;

public interface SectionView extends BaseView<SectionListBean> {
    @Override
    void onSuccess(SectionListBean sectionListBean);

    @Override
    void onFail(String error);
}
