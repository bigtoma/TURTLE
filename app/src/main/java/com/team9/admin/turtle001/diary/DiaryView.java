package com.team9.admin.turtle001.diary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.team9.admin.turtle001.R;

public class DiaryView extends AppCompatActivity {

    private ListView lv;
    private Diarydb diarydb;
    private SQLiteDatabase dbWriter;
    private DiaryAdapter adapter;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_view);
        intView();
    }
    public void intView(){
        lv = (ListView) findViewById(R.id.list);
        diarydb = new Diarydb(this);
        dbWriter = diarydb.getWritableDatabase();
        cursor = dbWriter.rawQuery("SELECT TITLE,TIME,CONTENT,COLOR,_ID FROM DIARY ORDER BY _ID DESC",null);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                Intent i = new Intent(DiaryView.this,SelectAct.class);
                i.putExtra(Diarydb.ID,cursor.getInt(cursor.getColumnIndex(Diarydb.ID)));
                i.putExtra(Diarydb.CONTENT,cursor.getString(cursor.getColumnIndex(Diarydb.CONTENT)));
                i.putExtra(Diarydb.TITLE,cursor.getString(cursor.getColumnIndex(Diarydb.TITLE)));
                i.putExtra(Diarydb.TIME,cursor.getString(cursor.getColumnIndex(Diarydb.TIME)));
                i.putExtra(Diarydb.COLOR,cursor.getInt(cursor.getColumnIndex(Diarydb.COLOR)));
                startActivity(i);
            }
        });
    }
    public void selectDB(){
        cursor = dbWriter.rawQuery("SELECT TITLE,TIME,CONTENT,COLOR,_ID FROM DIARY ORDER BY _ID DESC",null);
        adapter = new DiaryAdapter(this,cursor);
        lv.setAdapter(adapter);
    }

    @Override
    protected void onResume(){
        super.onResume();
        selectDB();
    }
}
