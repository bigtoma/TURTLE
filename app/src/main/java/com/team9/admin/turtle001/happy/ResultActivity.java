package com.team9.admin.turtle001.happy;

// 测试结果

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.team9.admin.turtle001.ActivityCollector;
import com.team9.admin.turtle001.BaseActivity;
import com.team9.admin.turtle001.MainActivity;
import com.team9.admin.turtle001.article.Lowest;
import com.team9.admin.turtle001.history.ChartActivity;
import com.team9.admin.turtle001.history.Historydb;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.itangqi.waveloadingview.WaveLoadingView;

import com.team9.admin.turtle001.R;
import static com.team9.admin.turtle001.happy.PsActivity.score;

public class ResultActivity extends BaseActivity {

    private WaveLoadingView mWaveLoadingView;
    private String suggest;
    private TextView sugg;
    private double res;
    private int rel;
    private int flag;
    private Historydb historydb;
    private SQLiteDatabase dbWriter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        calculate();
        display();

        Button back = (Button) findViewById(R.id.title_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

    private void display(){
        String rel1 = String.valueOf(rel);

        mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.CIRCLE);
        mWaveLoadingView.setCenterTitle(rel1);
        mWaveLoadingView.setProgressValue((int)rel);
        mWaveLoadingView.setAmplitudeRatio(70);
        mWaveLoadingView.setAnimDuration(2000);
        mWaveLoadingView.startAnimation();
        mWaveLoadingView.setCenterTitleColor(Color.WHITE);
        mWaveLoadingView.setCenterTitleStrokeColor(Color.GRAY);

        sugg = findViewById(R.id.sugg);
        sugg.setText(suggest);

        new Thread(new Runnable() {

            @Override
            public void run() {
                Message mes = new Message();
                mes.what=flag;
                handler.sendMessage(mes);
            }
        }).start();

    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    mWaveLoadingView.setWaveColor(0xFFCDE6C7);
                    mWaveLoadingView.setBorderColor(0xFFCDE6C7);
                    break;
                case 2:
                    mWaveLoadingView.setWaveColor(0xFF93CFA6 );
                    mWaveLoadingView.setBorderColor(0xFF93CFA6 );
                    break;
                case 3:
                    mWaveLoadingView.setWaveColor(0xFF49AD95);
                    mWaveLoadingView.setBorderColor(0xFF49AD95);
                    break;
                case 4:
                    mWaveLoadingView.setWaveColor(0xFF006C58);
                    mWaveLoadingView.setBorderColor(0xFF006C58);
                    break;
            }

        }
    };

    private void calculate(){

        int sum = 0;
        for (int i = 1;i <= 10;i++){
            sum+=score[i];
        }
        res = 0.1*((double) sum);
        rel = (int)(res*25);
        int[] order = new int[11];
        getorder(4,order);
        addDB(rel);
        Lowest lowest = new Lowest(this);
        lowest.resetDB();
        lowest.addDB(String.valueOf(order[1]));
        lowest.addDB(String.valueOf(order[2]));
        lowest.addDB(String.valueOf(order[3]));
        lowest.addDB(String.valueOf(order[4]));

        if(res>=3.6){
            suggest="您今天的状态很好！思维与灵感十分活跃，请加油并保持这种状态！";
            flag=1;
        }else if (res>=2.9){
            suggest="您今天的状态较为平静，思维方式略显保守，不过属于正常区域，请适当放松，调整自己.";
            flag=2;
        }else if (res>=2.0){
            suggest="您今天情绪状况略显压抑，尝试适合自己的放松项目，努力将“负面支出”降到最低吧！";
            flag=3;
        }else  {
            suggest="您今天的情绪状态比较糟糕，如不能尽快的自我调节，建议您寻求专业的心理咨询帮助。";
            flag=4;
        }

    }


    public void getorder(int num,int order[]){

        for (int i = 1;i <= 10;i++){
            order[i] = i;
        }
        int s;
        for (int i = 1;i <= num;i++){
            for (int j = i+1;j <=10;j++){
                if (score[order[i]] > score[order[j]]){
                    s = order[i];order[i] = order[j];order[j] = s;
                }
            }
        }
    }

    private void addDB(int score){
        historydb = new Historydb(this);
        dbWriter = historydb.getWritableDatabase();
        String string = String.valueOf(score);
        Cursor cursor = dbWriter.rawQuery("SELECT CONTENT FROM HISTORY WHERE YEAR = ? AND MONTH = ? AND DAY = ?",new String[]{getYear(),getMonth(),getDay()});
        if (cursor.moveToFirst()){
            dbWriter.execSQL("UPDATE HISTORY SET CONTENT = ?,TIME = ? WHERE YEAR = ? AND MONTH = ? AND DAY = ?",new String[]{string,getTime(),getYear(),getMonth(),getDay()});
        }else{
            dbWriter.execSQL("INSERT INTO HISTORY (CONTENT,TIME,YEAR,MONTH,DAY) VALUES(?,?,?,?,?)",new String[]{string,getTime(),getYear(),getMonth(),getDay()});
        }
    }

    private String getDay () {
        SimpleDateFormat format = new SimpleDateFormat("dd");
        Date date = new Date();
        String str = String.valueOf(Integer.parseInt(format.format(date)));
        return str;
    }

    private String getYear () {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String str = format.format(date);
        return str;
    }

    private String getMonth () {
        SimpleDateFormat format = new SimpleDateFormat("MM");
        Date date = new Date();
        String str = String.valueOf(Integer.parseInt(format.format(date)));
        return str;
    }


    private String getTime () {
        SimpleDateFormat format = new SimpleDateFormat("HH：mm：ss");
        Date date = new Date();
        String str = format.format(date);
        return str;
    }

    @Override
    public void onBackPressed(){

        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //开始
        mWaveLoadingView.resumeAnimation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束
        mWaveLoadingView.pauseAnimation();
        ActivityCollector.finishAll();
    }

}
