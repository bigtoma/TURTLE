package com.team9.admin.turtle001.diary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Diary  {

    private Diarydb diarydb;
    private SQLiteDatabase dbWriter;
    private Context context;

    public Diary(Context context){
        this.context = context;
        diarydb = new Diarydb(context);
        dbWriter = diarydb.getWritableDatabase();
    }

    public void addDB(String title,String content,String time,int color){

        dbWriter.execSQL("INSERT INTO DIARY(CONTENT,TIME,TITLE,COLOR) VALUES(?,?,?,?)",new String[]{content,time,title,color+""} );
    }

    public void delectDB(int id){
        dbWriter.delete("diary","_id = ?",new String[]{id+""});
    }

    public void resetDB(int id ,String title,String content,String time,int color){
        dbWriter.execSQL("UPDATE DIARY SET CONTENT = ?,TIME = ?,TITLE = ?,COLOR = ? WHERE _ID = ?",new String[]{content,time,title,color+"",id+""});
    }

}
