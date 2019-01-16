package com.example.lib_core.base.mvp;

public interface IBaseView {

    //初始化presennter方法
    BasePresenter initPresenter();
    void  showLoding();//显示
    void  hideLoding();//隐藏
    void  failLoding(String msg);//失败

}
