package com.example.king.dsshoppingcart.presenter;

import com.example.king.dsshoppingcart.contact.CartContact;
import com.example.lib_core.net.OkhttpCallback;

import java.util.HashMap;

public class CartPresenter extends CartContact.ICartPresenter {
    @Override
    public void setCartData(HashMap<String, String> parmas) {
        modle.setCartData(parmas, new OkhttpCallback() {
            @Override
            public void failure(String msg) {
                view.failure(msg);
            }

            @Override
            public void success(String result) {
                view.success(result);
            }
        });
    }
}
