package com.example.lib_core.base.mvp;

import com.example.lib_core.base.BaseAcivity;

public abstract class BaseMvpActivity<M extends IBaseModel,P extends BasePresenter> extends BaseAcivity implements IBaseView {

    public M modle;
    public P presenter;


    @Override
    protected void initData() {
        presenter = (P) initPresenter();

        if (presenter != null) {
            modle = (M) presenter.getModule();
            if (modle != null) {
                //绑定
                presenter.attach(modle,this);
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        if (presenter != null) {
            presenter.dettach();
        }
    }
}
