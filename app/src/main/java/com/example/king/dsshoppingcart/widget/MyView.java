package com.example.king.dsshoppingcart.widget;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.king.dsshoppingcart.R;

public class MyView extends ConstraintLayout {

    private TextView addTV,minusTv;
    private EditText numTv;
    private int num = 1;
    private AddMinusCallback addMinusCallback;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_myview,this,true);
        addTV = view.findViewById(R.id.add);
        minusTv = view.findViewById(R.id.minus);
        numTv = view.findViewById(R.id.et_num);
        numTv.setText("1");

        addTV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num ++;
                numTv.setText(num + "");
                if (addMinusCallback != null) {
                    addMinusCallback.numCallback(num);
                }
            }
        });
        minusTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num--;
                if (num==0){
                    num=1;
                    Toast.makeText(getContext(),"不能再减了",Toast.LENGTH_SHORT).show();
                }
                numTv.setText(num+"");

                if (addMinusCallback!=null){
                    addMinusCallback.numCallback(num);
                }
            }
        });
    }



    public void setAddMinusCallback(AddMinusCallback addMinusCallback) {
        this.addMinusCallback = addMinusCallback;
    }

    public interface  AddMinusCallback{
        void numCallback(int num);
    }
}
