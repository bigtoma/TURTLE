package com.team9.admin.turtle001.diary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Diarydb extends SQLiteOpenHelper{

    public static final String TABLE_NAME = "diary";
    public static final String CONTENT = "content";
    public static final String TITLE = "title";
    public static final String ID ="_id";
    public static final String TIME = "time";
    public static final String COLOR = "color";

    public Diarydb(Context context) {
        super(context, "diary", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +TITLE+" TEXT NOT NULL,"
                +CONTENT+" TEXT NOT NULL,"
                +COLOR+" INTEGER NOT NULL,"
                +TIME+" TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
