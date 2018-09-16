package com.team9.admin.turtle001.happy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.team9.admin.turtle001.BaseActivity;
import com.team9.admin.turtle001.R;

// 测试须知

public class PsActivity extends BaseActivity implements View.OnClickListener {
    private Button bt1;
    public static int[] score = new int[11];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ps);
        bt1 = (Button) findViewById(R.id.start);
        bt1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
                v.setBackgroundResource(R.drawable.pressdown);
                Intent intent = new Intent(this, Q1Activity.class);
                startActivity(intent);
    }

}

