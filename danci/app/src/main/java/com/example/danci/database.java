package com.example.danci;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {
    public static final String Create_word="create table word("
        + "word text ,"
        +"voice text,"
        +"ex1 text,"
        +"ex text,"
        +"ju text )";
    private Context mContext;
    public database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext=context;
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL(Create_word);
    }
    public void onUpgrade(SQLiteDatabase db,int oldversion,int newVersion){}
}
