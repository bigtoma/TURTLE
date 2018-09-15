package com.team9.admin.turtle001.article;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Article  {
    
    private Articledb articledb;
    private SQLiteDatabase dbWriter ;
    private Context context;

    public Article(Context context){
        this.context = context;
        articledb = new Articledb(context);
    }
    
    public void addDB(String content,String tag,String title,int id) {

        dbWriter = articledb.getWritableDatabase();
        Cursor cursor = searchId(id);
        if (cursor.moveToFirst()) {
            ;
        }else{
            dbWriter.execSQL("INSERT INTO ARTICLE (CONTENT,TAG,TITLE) VALUES(?,?,?)", new String[]{content,tag,title});
        }
    }

    public Cursor searchTAG(String tag) {

        dbWriter = articledb.getReadableDatabase();

        Cursor cursor = dbWriter.rawQuery("SELECT CONTENT,TITLE FROM ARTICLE WHERE TAG = ? order by _id desc", new String[]{tag});

        return cursor;
    }
    public Cursor searchId(int id) {

        dbWriter = articledb.getReadableDatabase();

        Cursor cursor = dbWriter.rawQuery("SELECT CONTENT FROM ARTICLE WHERE _id = ?", new String[]{id+""});

        return cursor;
    }


    public String getIndex(String tag){
        Cursor cursor = searchTAG(tag);
        if(cursor.moveToFirst()){
        return cursor.getString(cursor.getColumnIndex("content"));}else return "0";
    }

    public  String getTitle (String tag){
        Cursor cursor = searchTAG(tag);
        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex("title"));}else return "0";
    }

    public void addActicle(){
        addDB("https://mp.weixin.qq.com/s/Uy5A2mjJ-xdJV6wdo1J_Kg","1","你为什么总在恼火与自责之间循环？",1);
        addDB("https://mp.weixin.qq.com/s/SG1b-XffKdcRQmAhm36UcQ","2","“委屈的身体”70％以上的身体疾病来自情绪",2);
        addDB("https://mp.weixin.qq.com/s/SqIpn7cIRr0Jk0I3Q6MR7w","3","总是叮嘱自己要“专心”，可还老是“走神”，怎么破？",3);
        addDB("https://mp.weixin.qq.com/s/2hOs_Suu5m_gbxj6IXSHxw","4","让心变软，学习接纳",4);
        addDB("https://mp.weixin.qq.com/s/jYwuqy7HPrWC86qFkbsmTQ","5","感到被孤立了怎么办？",5);
        addDB("https://mp.weixin.qq.com/s/iBa_IkiYYZp8oxc4phj_sw ","6","人为什么要说谎",6);
        addDB("https://mp.weixin.qq.com/s/mYd8O78kdU-_wJOMnsGW9g","7","你要适应的不是社会，而是你自己",7);
        addDB("https://mp.weixin.qq.com/s/chM8XcIHF7zsdaocXgdgHA","8","如何正确地跟他人搭话？",8);
        addDB("https://mp.weixin.qq.com/s/9eBmJMNIAllEskOraD4Jqw","9","人不自信的根源是什么？",9);
        addDB("https://mp.weixin.qq.com/s/L3bW5PfeA46bZs58RDnpXg","10","当你不能改变游戏规则，那就好好遵守",10);
    }
}
