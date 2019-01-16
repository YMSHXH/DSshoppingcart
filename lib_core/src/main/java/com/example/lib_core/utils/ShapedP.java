package com.example.lib_core.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.lib_core.base.BaseApp;

public class ShapedP {

    private final String SP_NAMA = "wd_sp";
    private static ShapedP mInstance;

    private ShapedP() {

    }

    public static ShapedP getmInstance(){
        if (mInstance == null) {
            synchronized (ShapedP.class){
                if (mInstance == null) {
                    mInstance = new ShapedP();
                }
            }
        }
        return mInstance;
    }

    /**
     * sp实例
     * @return
     */
    private SharedPreferences getSP(){
        SharedPreferences sharedPreferences = BaseApp.getContext().getSharedPreferences(SP_NAMA,Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            return sharedPreferences;
        }
        return null;
    }

    /**
     * 添加数据
     * @param key
     * @param value
     */
    public void putSP(String key,String value){
        getSP().edit().putString(key,value).commit();
    }

    /**
     * 拿到数据
     */
    public String getSP(String key){
        return getSP().getString(key,"");
    }

}
