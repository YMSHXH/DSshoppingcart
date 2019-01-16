package com.example.king.dsshoppingcart.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.king.dsshoppingcart.R;
import com.example.king.dsshoppingcart.adapter.activitycallback.CardTwoCallBack;
import com.example.king.dsshoppingcart.bean.Case;
import com.example.king.dsshoppingcart.widget.MyView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class CardTwoAdapter extends XRecyclerView.Adapter<CardTwoAdapter.CardTwoVH> {
    private Context context;
    private List<Case.Cart.Product> list;
    private CardTwoCallBack cardTwoCallBack;

    public void setCardTwoCallBack(CardTwoCallBack cardTwoCallBack) {
        this.cardTwoCallBack = cardTwoCallBack;
    }

    public CardTwoAdapter(Context context, List<Case.Cart.Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CardTwoVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart_two,viewGroup,false);
        CardTwoVH cardTwoVH = new CardTwoVH(view);
        return cardTwoVH;
    }

    @Override
    public void onBindViewHolder(@NonNull final CardTwoVH cardTwoVH, int i) {
        final Case.Cart.Product product = list.get(i);
        //设置商品选中
        cardTwoVH.ck_goods.setChecked(product.isProductChecked());
        cardTwoVH.title.setText(product.getTitle());
        cardTwoVH.price.setText(product.getPrice() + "");
        String[] imgs = product.getImages().split("\\|");
        if (imgs != null && imgs.length > 0) {

            Glide.with(context).load(imgs[0]).into(cardTwoVH.img_goods);
        } else {
            Glide.with(context).load(product.getImages()).into(cardTwoVH.img_goods);
        }

        cardTwoVH.myView.setAddMinusCallback(new MyView.AddMinusCallback() {
            @Override
            public void numCallback(int num) {
                product.setProductNum(num);
                if (cardTwoCallBack != null){
                    cardTwoCallBack.notifyDataTwo();
                }
            }
        });

        //设置商品选中情况
        cardTwoVH.ck_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean productChecked = cardTwoVH.ck_goods.isChecked();
                if (productChecked){//已选中
                    product.setProductChecked(true);
                    for (Case.Cart.Product product1 : list) {
                        if (!productChecked){
                            //一级未选中
                            product.setProductChecked(false);
                            if (cardTwoCallBack != null){
                                cardTwoCallBack.natifyCartNum(false,product1.getPos());
                                //Log.e("check=====",""+product1.getPos());
                            }
                            return;
                        }
                    }
                    if (productChecked){
                        //一级选中
                        product.setProductChecked(true);
                        if (cardTwoCallBack != null){
                            cardTwoCallBack.natifyCartNum(true,product.getPos());
                        }
                    }
                } else {
                    //未选中
                    product.setProductChecked(false);
                    //一级未选中
                    if (cardTwoCallBack != null){
                        cardTwoCallBack.natifyCartNum(false,product.getPos());
                    }
                }
//                product.setProductChecked(productChecked);
//                if (cardTwoCallBack != null){
//                    cardTwoCallBack.natifyCartNum(productChecked,product.getPos());
//                }
           }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CardTwoVH extends RecyclerView.ViewHolder {
        CheckBox ck_goods;
        TextView title,price;
        ImageView img_goods;
        MyView myView;
        public CardTwoVH(@NonNull View itemView) {
            super(itemView);
            ck_goods = itemView.findViewById(R.id.ck_goods);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            img_goods = itemView.findViewById(R.id.img_goods);
            myView = itemView.findViewById(R.id.myView);
        }
    }
}
