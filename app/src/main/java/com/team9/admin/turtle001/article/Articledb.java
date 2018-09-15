package com.team9.admin.turtle001.article;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Articledb  extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "article";
    public static final String CONTENT = "content";
    public static final String ID ="_id";
    public static final String TAG = "tag";
    public static final String TITLE = "title";

    public Articledb(Context context) {
        super(context, "article", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +CONTENT+" TEXT NOT NULL,"
                +TITLE+" TEXT NOT NULL,"
                +TAG+" TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
