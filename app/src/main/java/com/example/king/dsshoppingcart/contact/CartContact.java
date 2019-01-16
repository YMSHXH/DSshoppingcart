package com.example.king.dsshoppingcart.contact;

import com.example.king.dsshoppingcart.model.CartModel;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;
import com.example.lib_core.net.OkhttpCallback;

import java.util.HashMap;

public interface CartContact {

    abstract class ICartPresenter extends BasePresenter<ICartModel,ICartView> {

        @Override
        public ICartModel getModule() {
            return new CartModel();
        }
        public abstract void setCartData(HashMap<String, String> parmas);
    }

    interface ICartModel extends IBaseModel {
        void setCartData(HashMap<String, String> parmas, OkhttpCallback okhttpCallback);
    }

    interface ICartView extends IBaseView {
        void failure(String msg);//服务器请求失败：断网，服务器宕机等等因素
        void success(String result);//数据合法
    }

}
