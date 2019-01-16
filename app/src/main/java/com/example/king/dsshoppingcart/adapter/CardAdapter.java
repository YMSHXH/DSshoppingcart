package com.example.king.dsshoppingcart.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.king.dsshoppingcart.R;
import com.example.king.dsshoppingcart.adapter.activitycallback.CardCallBack;
import com.example.king.dsshoppingcart.adapter.activitycallback.CardTwoCallBack;
import com.example.king.dsshoppingcart.bean.Case;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class CardAdapter extends XRecyclerView.Adapter<CardAdapter.CardVH>
        implements CardTwoCallBack {

    private Context context;
    private List<Case.Cart> list;
    private CardCallBack cardCallBack;

    public void setCardCallBack(CardCallBack cardCallBack) {
        this.cardCallBack = cardCallBack;
    }

    public CardAdapter(Context context, List<Case.Cart> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CardVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart,viewGroup,false);
        CardVH cardVH = new CardVH(view);
        return cardVH;
    }

    @Override
    public void onBindViewHolder(@NonNull final CardVH cardVH, int i) {
        final Case.Cart cart = list.get(i);
        //设置是否选中
        cardVH.ck_sj.setChecked(cart.isChecked());
        cardVH.sj_name.setText(cart.getSellerName());
        //对每件商品的pos赋值
        for (Case.Cart.Product product : cart.getList()) {
            product.setPos(i);
        }
        
        //设置子类商品适配器
        cardVH.xre_son.setLayoutManager(new LinearLayoutManager(context));
        final CardTwoAdapter cardTwoAdapter = new CardTwoAdapter(context,cart.getList());
        cardVH.xre_son.setAdapter(cardTwoAdapter);
        cardTwoAdapter.setCardTwoCallBack(this);

        cardVH.ck_sj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取是否选中的值
                boolean checked = cardVH.ck_sj.isChecked();
                //Log.e("check=====",""+checked);
                for (Case.Cart.Product product : cart.getList()) {
                    product.setProductChecked(checked);
                }
                cardTwoAdapter.notifyDataSetChanged();

                if (cardCallBack != null) {
                    cardCallBack.notifyData();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void natifyCartNum(boolean isChecked, int position) {
        Log.e("check=====",""+position);
        list.get(position).setChecked(isChecked);
        notifyDataSetChanged();
        if (cardCallBack != null) {
            cardCallBack.notifyData();
        }
    }

    @Override
    public void notifyDataTwo() {
        if (cardCallBack != null) {
            cardCallBack.notifyData();
        }
    }

    class CardVH extends XRecyclerView.ViewHolder{
        CheckBox ck_sj;
        TextView sj_name;
        XRecyclerView xre_son;

        public CardVH(@NonNull View itemView) {
            super(itemView);
            ck_sj = itemView.findViewById(R.id.ck_sj);
            sj_name = itemView.findViewById(R.id.sj_name);
            xre_son = itemView.findViewById(R.id.xre_son);
        }
    }


}
