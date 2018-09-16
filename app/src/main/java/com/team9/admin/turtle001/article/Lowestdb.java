package com.team9.admin.turtle001.article;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


//最低分数据库

public class Lowestdb extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "lowest";
    public static final String CONTENT = "content";
    public static final String ID ="_id";

    public Lowestdb  (Context context) {
        super(context, "lowest", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +CONTENT+" TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

