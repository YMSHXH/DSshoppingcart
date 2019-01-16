package com.example.lib_core.base.mvp;

import com.example.lib_core.base.BaseAcivity;
import com.example.lib_core.base.BaseFragment;

public abstract class BaseMvpFragment<M extends IBaseModel,P extends BasePresenter> extends BaseFragment implements IBaseView {

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
    public void onDestroy() {
        super.onDestroy();
        //解绑
        if (presenter != null) {
            presenter.dettach();
        }
    }
}
