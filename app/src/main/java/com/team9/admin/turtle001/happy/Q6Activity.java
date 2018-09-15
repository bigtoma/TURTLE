package com.team9.admin.turtle001.happy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.team9.admin.turtle001.BaseActivity;
import com.team9.admin.turtle001.R;
import static com.team9.admin.turtle001.happy.PsActivity.score;

public class Q6Activity extends BaseActivity implements View.OnClickListener {
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q6);
        initView();
    }
    private void initView() {
        bt1 = (Button) findViewById(R.id.q6_a);
        bt2 = (Button) findViewById(R.id.q6_b);
        bt3 = (Button) findViewById(R.id.q6_c);
        bt4 = (Button) findViewById(R.id.q6_d);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        bt1.setBackgroundResource(R.drawable.unpressed);
        bt2.setBackgroundResource(R.drawable.unpressed);
        bt3.setBackgroundResource(R.drawable.unpressed);
        bt4.setBackgroundResource(R.drawable.unpressed);
        int point = 0;
        switch (v.getId()){
            case R.id.q6_a: {
                point = 4;
                break;
            }
            case R.id.q6_b: {
                point = 3;
                break;
            }
            case R.id.q6_c: {
                point = 2;
                break;
            }
            case R.id.q6_d: {
                point = 1;
                break;
            }
            default:break;

        }
        v.setBackgroundResource(R.drawable.pressdown);
        score[6] = point;
        Intent intent= new Intent(Q6Activity.this,Q7Activity.class);
        startActivity(intent);
    }

}
