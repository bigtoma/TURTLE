package com.team9.admin.turtle001.article;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Lowest{

    private Lowestdb lowestdb;
    private SQLiteDatabase dbWriter ;
    private Context context;
    public  Lowest(Context context) {
        this.context = context;
        lowestdb = new Lowestdb(context);
    }

    public void addDB(String content) {


        dbWriter = lowestdb.getWritableDatabase();
        dbWriter.execSQL("INSERT INTO lowest (CONTENT) VALUES(?)", new String[]{content});
    }

    public Cursor searchDB() {

        dbWriter = lowestdb.getReadableDatabase();

        Cursor cursor = dbWriter.rawQuery("SELECT CONTENT FROM LOWEST",null);

        return cursor;
    }


    public void resetDB(){
        dbWriter = lowestdb.getWritableDatabase();
        Cursor cursor = searchDB();
        if (cursor.moveToFirst()){
            do{
                dbWriter.execSQL("DELETE FROM LOWEST");
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
}
