package com.wds.weizixun;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wds.base.BaseActivity;
import com.wds.base.BaseMVPActivity;
import com.wds.bean.LoginBean;
import com.wds.presenter.LoginPresenter;
import com.wds.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
}
