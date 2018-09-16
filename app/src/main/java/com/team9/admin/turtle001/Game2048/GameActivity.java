package com.team9.admin.turtle001.Game2048;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.team9.admin.turtle001.R;

//游戏主页

public class GameActivity extends AppCompatActivity {

    private int maxscore = 0;
    private Scoredb scoredb;
    private SQLiteDatabase dbWriter;

    public GameActivity(){
        mainActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        scoredb = new Scoredb(this);
        dbWriter = scoredb.getWritableDatabase();

        tvScore = (TextView) findViewById(R.id.tvScore);
        maxScore = (TextView) findViewById(R.id.maxScore);

        Cursor cursor = dbWriter.rawQuery("SELECT CONTENT FROM SCORE",null);
        if (cursor.moveToFirst()) {
            maxscore = cursor.getInt(cursor.getColumnIndex("content"));
        }
        cursor.close();

        showScore();
    }

    public void clearScore(){
        score = 0;
        showScore();
    }

    public void showScore(){
        tvScore.setText(score+"");
        maxScore.setText(maxscore+"");
    }

    public void addScore(int s){
        score+=s;

        addDB(score);

        showScore();
    }
    public void addDB(int mscore){
        Cursor cursor = dbWriter.rawQuery("SELECT CONTENT FROM SCORE",null);
        ContentValues values = new ContentValues();
        if (cursor.moveToFirst()){
            maxscore = cursor.getInt(cursor.getColumnIndex("content"));
            if (mscore >maxscore){
                maxscore = mscore;
                values.put("content",maxscore);
                dbWriter.update("score",values,null,null);
            }
        }else{
            maxscore = score;
            values.put("content",mscore);
            dbWriter.insert("score",null,values);
        }
    }

    public int score = 0;
    private TextView tvScore,maxScore;

    private  static GameActivity mainActivity = null;

    public  static GameActivity getMainActivity() {
        return mainActivity;
    }
}
