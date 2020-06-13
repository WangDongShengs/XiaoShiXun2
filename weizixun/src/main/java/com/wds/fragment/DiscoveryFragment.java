package com.wds.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.wds.weizixun.LoginActivity;
import com.wds.weizixun.R;
import com.wds.weizixun.ZhiHuActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoveryFragment extends Fragment {


    @BindView(R.id.iv_zhihu)
    ImageView ivZhihu;
    @BindView(R.id.tv_zhihu)
    TextView tvZhihu;
    @BindView(R.id.ll_zhihu)
    LinearLayout llZhihu;
    @BindView(R.id.iv_it)
    ImageView ivIt;
    @BindView(R.id.tv_it)
    TextView tvIt;
    @BindView(R.id.ll_it)
    LinearLayout llIt;
    @BindView(R.id.iv_tencent)
    ImageView ivTencent;
    @BindView(R.id.tv_tencent)
    TextView tvTencent;
    @BindView(R.id.ll_tencent)
    LinearLayout llTencent;
    @BindView(R.id.login)
    Button login;
    Unbinder unbinder;

    public DiscoveryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_discovery, container, false);
        initView(inflate);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    private void initView(View inflate) {
        View viewById = inflate.findViewById(R.id.login);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginOut();
            }
        });
    }

    private void loginOut() {
        EMClient.getInstance().logout(true, new EMCallBack() {
            @Override
            public void onSuccess() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "登出成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });
            }

            @Override
            public void onError(int i, String s) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "登出失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_zhihu, R.id.ll_it, R.id.ll_tencent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_zhihu:
                Intent intent = new Intent(getActivity(), ZhiHuActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_it:
                break;
            case R.id.ll_tencent:
                break;
        }
    }
}
