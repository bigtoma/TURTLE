package com.team9.admin.turtle001.Fragment_main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;


import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.team9.admin.turtle001.BaseActivity;
import com.team9.admin.turtle001.MainActivity;

import com.team9.admin.turtle001.R;
import com.team9.admin.turtle001.happy.Q1Activity;


//冥想引导

public class Mx_introduction extends BaseActivity implements View.OnClickListener{
    private Button bt1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mx_introduction);
        bt1 = (Button) findViewById(R.id.startmx);


        bt1.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("fragid",2);
        startActivity(intent);
    }

    @Override
    public void onStop(){
        super.onStop();
        finish();
    }
}

