package com.team9.admin.turtle001.diary;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.team9.admin.turtle001.R;

import java.text.SimpleDateFormat;
import java.util.Date;

// 写入日记

public class MainActivity_diary extends AppCompatActivity implements View.OnClickListener{

    private Button savebtn, backbtn;
    private Button colorbtn1,colorbtn2,colorbtn3,colorbtn4;
    private EditText ettext,ettitle;
    private Diary diary;
    private Intent intent1;
    private int color ;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        savebtn = (Button) findViewById(R.id.diarysave);
        backbtn = (Button) findViewById(R.id.back);


        ettext = (EditText) findViewById(R.id.ettext);
        ettitle = (EditText) findViewById(R.id.ettitle);

        layout = (LinearLayout) findViewById(R.id.maindiary);

        colorbtn1 = (Button) findViewById(R.id.bt_color1);
        colorbtn2 = (Button) findViewById(R.id.bt_color2);
        colorbtn3 = (Button) findViewById(R.id.bt_color3);
        colorbtn4 = (Button) findViewById(R.id.bt_color4);


        color = ContextCompat.getColor(MainActivity_diary.this,R.color.colorDiary0);

        intent1 = new Intent(MainActivity_diary.this,DiaryView.class);
         diary = new Diary(MainActivity_diary.this);

         savebtn.setOnClickListener(this);
         backbtn.setOnClickListener(this);
         colorbtn1.setOnClickListener(this);
         colorbtn2.setOnClickListener(this);
         colorbtn3.setOnClickListener(this);
         colorbtn4.setOnClickListener(this);
    }

    @SuppressLint("ResourceAsColor")
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.diarysave:{
                diary.addDB(ettitle.getText().toString(),ettext.getText().toString(),getTime(),color);
                Toast toast = Toast.makeText(MainActivity_diary.this,"保存成功",Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
            case R.id.back:{
                finish();
                break;
            }
            case R.id.bt_color1:{
                color = ContextCompat.getColor(MainActivity_diary.this,R.color.colorDiary1);
                break;
            }
            case R.id.bt_color2:{
                color = ContextCompat.getColor(MainActivity_diary.this,R.color.colorDiary2);
                break;
            }
            case R.id.bt_color3:{
                color = ContextCompat.getColor(MainActivity_diary.this, R.color.colorDiary3);
                break;
            }
            case R.id.bt_color4:{
                color = ContextCompat.getColor(MainActivity_diary.this,R.color.colorDiary4);
                break;
            }
            default:
                break;
        }

        layout.setBackgroundColor(color);

    }
    private String getTime () {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH：mm：ss");
        Date date = new Date();
        String str = format.format(date);
        return str;
    }
}
