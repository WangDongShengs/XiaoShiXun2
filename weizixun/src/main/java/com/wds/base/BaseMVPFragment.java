package com.wds.base;

public abstract class BaseMVPFragment<P extends BasePresenter , V extends BaseView> extends BaseFragment {
    protected P presenter;
    @Override
    protected void initMVP() {
        super.initMVP();
        presenter = initMVPPresenter();
        if (presenter!=null){
            presenter.setView(initMVPView());
        }
    }

    protected abstract V initMVPView();

    protected abstract P initMVPPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
    }
}
