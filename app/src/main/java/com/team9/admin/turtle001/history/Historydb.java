package com.team9.admin.turtle001.history;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Historydb extends SQLiteOpenHelper{

    public static final String TABLE_NAME = "history";
    public static final String CONTENT = "content";
    public static final String ID ="_id";
    public static final String DAY = "day";
    public static final String TIME = "time";
    public static final String YEAR = "year";
    public static final String MONTH = "month";

    public Historydb(Context context) {
        super(context, "history", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +CONTENT+" TEXT NOT NULL,"
                +YEAR+" TEXT NOT NULL,"
                +MONTH+" TEXT NOT NULL,"
                +DAY+" TEXT NOT NULL,"
                +TIME+" TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
