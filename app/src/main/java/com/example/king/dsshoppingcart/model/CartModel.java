package com.example.king.dsshoppingcart.model;

import com.example.king.dsshoppingcart.api.CartApi;
import com.example.king.dsshoppingcart.contact.CartContact;
import com.example.lib_core.net.OkhttpCallback;
import com.example.lib_core.net.OkhttpUtils;

import java.util.HashMap;

public class CartModel implements CartContact.ICartModel {
    @Override
    public void setCartData(HashMap<String, String> parmas, OkhttpCallback okhttpCallback) {
        OkhttpUtils.getInstance().doPost(CartApi.CART_API,parmas,okhttpCallback);
    }
}
