package com.team9.admin.turtle001.happy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.team9.admin.turtle001.ActivityCollector;
import com.team9.admin.turtle001.BaseActivity;
import com.team9.admin.turtle001.MainActivity;
import com.team9.admin.turtle001.R;

import com.team9.admin.turtle001.article.Article;

public class Q0Activity extends BaseActivity implements View.OnClickListener{
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q0);
        Article article =new Article(this);
        article.addActicle();
        initView();

    }
    private void initView() {

        bt2 = (Button) findViewById(R.id.how);
        bt3 = (Button) findViewById(R.id.start);

        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {

        bt2.setBackgroundResource(R.drawable.unpressed);
        bt3.setBackgroundResource(R.drawable.unpressed);
        Intent intent;
        switch (v.getId()) {
            case R.id.how: {
                 intent = new Intent(this, PsActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.start: {
                intent = new Intent(this, Q1Activity.class);
                startActivity(intent);
                break;
            }

            default:

                break;
        }
        v.setBackgroundResource(R.drawable.pressdown);
    }

    @Override
    public void onBackPressed(){
        ActivityCollector.finishAll();
    }

}
