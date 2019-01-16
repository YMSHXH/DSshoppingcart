package com.example.lib_core.base;

import android.app.Application;
import android.content.Context;

public class BaseApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    /**
     * 得到上下文
     * @return
     */
    public static Context getContext(){
        return context;
    }
}
