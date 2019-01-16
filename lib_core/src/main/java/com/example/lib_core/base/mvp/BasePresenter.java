package com.example.lib_core.base.mvp;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<M,V> {

    public M modle;
    public V view;

    private WeakReference<V> vWeakReference;

    public abstract M getModule();

    //绑定
    public void attach(M modle, V view){
        this.modle = modle;

        vWeakReference = new WeakReference<>(view);
        this.view = vWeakReference.get();
    }

    //解绑
    public void dettach(){
        if (vWeakReference != null){
            //晴空对象
            vWeakReference.clear();
            vWeakReference = null;
            view = null;
        }
    }

}
