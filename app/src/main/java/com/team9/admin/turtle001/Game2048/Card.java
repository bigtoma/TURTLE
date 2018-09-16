package com.team9.admin.turtle001.Game2048;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

// 2048的小卡片

public class Card extends FrameLayout{

    public Card(@NonNull Context context) {
        super(context);

        label = new TextView(getContext());
        label.setTextSize(30);

        label.setGravity(Gravity.CENTER);

        LayoutParams lp = new LayoutParams(-1,-1);
        lp.setMargins(7,7,7,7);
        addView(label,lp);

        setNum(0);
        setColor();
    }

    private int num = 0;

    public void setColor(){
        int mBgColor;
        switch (getNum())
        {
            case 0:
                mBgColor = 0xffCCC0B3;
                break;
            case 2:
                mBgColor = 0xffEEE4DA;
                break;
            case 4:
                mBgColor = 0xffEDE0C8;
                break;
            case 8:
                mBgColor = 0xffF2B179;// #F2B179
                break;
            case 16:
                mBgColor = 0xffF49563;
                break;
            case 32:
                mBgColor = 0xffF5794D;
                break;
            case 64:
                mBgColor = 0xffF55D37;
                break;
            case 128:
                mBgColor = 0xFFEEE863;
                break;
            case 256:
                mBgColor = 0xFFEDB04D;
                break;
            case 512:
                mBgColor = 0xFFECB04D;
                break;
            case 1024:
                mBgColor = 0xFFEB9437;
                break;
            case 2048:
                mBgColor = 0xFFEA7821;
                break;
            default:
                mBgColor = 0xFFEA7821;
                break;
        }
        int textColor;
        switch (getNum()){
            case 2:
            case 4:
                textColor = 0xff7B7168;
                break;
            default:
                textColor = 0xffFFFFFF;
        }
        label.setTextColor(textColor);
        label.setBackgroundColor(mBgColor);
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num){
        this.num = num;

        if (num<=0){
            label.setText("");
        }else{
            label.setText(num+"");
        }
    }

    public boolean equals(Card o){
        return getNum()==o.getNum();
    }

    private TextView label;
}
