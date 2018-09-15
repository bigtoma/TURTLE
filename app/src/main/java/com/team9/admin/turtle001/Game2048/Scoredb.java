package com.team9.admin.turtle001.Game2048;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Scoredb extends SQLiteOpenHelper{

    public static final String TABLE_NAME = "score";
    public static final String CONTENT = "content";
    public static final String ID = "_id";

    public Scoredb(Context context){
        super(context, "score", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +CONTENT+" INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
