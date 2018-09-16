package com.team9.admin.turtle001.diary;




import android.app.AlertDialog;
import android.content.DialogInterface;
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

// 日记的修改

public class SelectAct extends AppCompatActivity implements View.OnClickListener {

    private Button s_delect,s_save;
    private Button colorbtn1,colorbtn2,colorbtn3,colorbtn4;
    private EditText s_tv,s_title;
    private LinearLayout layout;
    private Diary diary;
    private int color,id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        s_delect = (Button)findViewById(R.id.sdelete);
        s_save = (Button)findViewById(R.id.ssave);
        s_tv = (EditText) findViewById(R.id.stv);
        s_title = (EditText) findViewById(R.id.stitle);

        colorbtn1 = (Button) findViewById(R.id.sbt_color1);
        colorbtn2 = (Button) findViewById(R.id.sbt_color2);
        colorbtn3 = (Button) findViewById(R.id.sbt_color3);
        colorbtn4 = (Button) findViewById(R.id.sbt_color4);

        layout = (LinearLayout) findViewById(R.id.selectdiary);

        diary  = new Diary(this);

        s_save.setOnClickListener(this);
        s_delect.setOnClickListener(this);
        colorbtn1.setOnClickListener(this);
        colorbtn2.setOnClickListener(this);
        colorbtn3.setOnClickListener(this);
        colorbtn4.setOnClickListener(this);

        s_tv.setText(getIntent().getStringExtra("content"));
        s_title.setText(getIntent().getStringExtra("title"));
        color = getIntent().getIntExtra("color",0);
        id = getIntent().getIntExtra("_id",0);
        layout.setBackgroundColor(color);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.sdelete:{
                deleteitem();
                break;
            }
            case R.id.ssave:{
                diary.resetDB(id,s_title.getText().toString(),s_tv.getText().toString(),getTime(),color);
                Toast toast = Toast.makeText(SelectAct.this,"修改成功",Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
            case R.id.sbt_color1:{
                color = ContextCompat.getColor(SelectAct.this,R.color.colorDiary1);
                break;
            }
            case R.id.sbt_color2:{
                color = ContextCompat.getColor(SelectAct.this,R.color.colorDiary2);
                break;
            }
            case R.id.sbt_color3:{
                color = ContextCompat.getColor(SelectAct.this,R.color.colorDiary3);
                break;
            }
            case R.id.sbt_color4:{
                color = ContextCompat.getColor(SelectAct.this,R.color.colorDiary4);
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

    private void deleteitem(){

        AlertDialog dialog = new AlertDialog.Builder(this)
//                .setIcon(R.mipmap.icon)//设置标题的图片
                .setTitle("删除 ")//设置对话框的标题
                .setMessage("确定要删除日记吗？")//设置对话框的内容
                //设置对话框的按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        diary.delectDB(id);
                        dialog.dismiss();
                        finish();
                    }
                }).create();
        dialog.show();
    }

}
