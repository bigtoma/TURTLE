package com.team9.admin.turtle001.happy;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.team9.admin.turtle001.ActivityCollector;
import com.team9.admin.turtle001.MainActivity;
import com.team9.admin.turtle001.R;

public class TitleLayout extends LinearLayout {
    private Button back;
    private Context context;

    public TitleLayout (final Context context, AttributeSet attrs){
        super (context, attrs);
        this.context=context;
        LayoutInflater.from(context).inflate(R.layout.title, this);

        back = (Button) findViewById(R.id.title_back);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                context.startActivity(new Intent(getContext(), MainActivity.class));
                ActivityCollector.finishAll();
            }
        });
    }
}
