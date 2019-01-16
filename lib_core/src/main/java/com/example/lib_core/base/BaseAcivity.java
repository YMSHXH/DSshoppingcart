package com.example.lib_core.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseAcivity extends AppCompatActivity {

    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResLayoutById());

        //黄油刀
        bind = ButterKnife.bind(this);

        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getResLayoutById();

    /**
     * 吐司
     * @param text
     */
    public void toToast(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    /**
     * 无参信使跳转
     * @param aClass
     */
    public void toIntent(Class<?> aClass){
        Intent intent = new Intent(this,aClass);
        startActivity(intent);
    }

    /**
     * 有参数信使跳转
     * @param param
     * @param aClass
     */
    public void toIntentByParam(Bundle param,Class<?> aClass){
        Intent intent = new Intent(this,aClass);
        intent.putExtras(param);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        if (bind != null) {
            bind = null;
        }
    }
}
