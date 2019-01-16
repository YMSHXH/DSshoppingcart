package com.example.king.dsshoppingcart.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.king.dsshoppingcart.adapter.CardAdapter;
import com.example.king.dsshoppingcart.R;
import com.example.king.dsshoppingcart.adapter.activitycallback.CardCallBack;
import com.example.king.dsshoppingcart.bean.Case;
import com.example.king.dsshoppingcart.contact.CartContact;
import com.example.king.dsshoppingcart.presenter.CartPresenter;
import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends BaseMvpActivity<CartContact.ICartModel, CartContact.ICartPresenter>
        implements CartContact.ICartView,CardCallBack {

    private CardAdapter cardAdapter;
    private XRecyclerView xrecyView;
    private CheckBox ckb_quan;
    private TextView qutofill;
    private List<Case.Cart> list;

    @Override
    protected void initView() {
        xrecyView = findViewById(R.id.xrecyView);
        ckb_quan = findViewById(R.id.ckb_quan);
        qutofill = findViewById(R.id.qutofill);
        xrecyView.setLayoutManager(new LinearLayoutManager(this));
        //全选设置
        ckb_quan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (Case.Cart cart : list) {
                    cart.setChecked(isChecked);
                    for (Case.Cart.Product product : cart.getList()) {
                        product.setProductChecked(isChecked);
                    }
                }
                getTotalPrice();
                cardAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 获取总价
     */
    private void getTotalPrice() {
        double totalprice = 0;
        for (Case.Cart cart : list) {
            for (Case.Cart.Product product : cart.getList()) {
                if (product.isProductChecked()){
                    totalprice += product.getPrice() * product.getProductNum();
                }
            }
        }
        //设置总和
        ckb_quan.setText("￥：" + totalprice);
    }

    @Override
    protected void initData() {
        super.initData();
        list = new ArrayList<>();

        HashMap<String, String> param = new HashMap<>();
        presenter.setCartData(param);
    }

    @Override
    protected int getResLayoutById() {
        return R.layout.activity_main;
    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void success(String result) {
        Case aCase = new Gson().fromJson(result, Case.class);
        list = aCase.getData();
        for (Case.Cart cart : list) {
            for (Case.Cart.Product product : cart.getList()) {
                product.setProductNum(1);
            }
        }
        cardAdapter = new CardAdapter(MainActivity.this, list);
        cardAdapter.setCardCallBack(this);
        //Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        xrecyView.setAdapter(cardAdapter);
    }

    @Override
    public BasePresenter initPresenter() {
        return new CartPresenter();
    }

    @Override
    public void showLoding() {

    }

    @Override
    public void hideLoding() {

    }

    @Override
    public void failLoding(String msg) {

    }


    /**
     *  第一层回调
     */
    @Override
    public void notifyData() {
        getTotalPrice();
    }
}
