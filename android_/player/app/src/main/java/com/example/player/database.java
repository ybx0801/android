package com.example.player;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {
    public static final String Create_music="create table music("
            + "name text ,"
            +"path text,"
            +"album text,"
            +"artist text,"
            +"musicId int,"
            +"duration int )";
    private Context mContext;
    public database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext=context;
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL(Create_music);
    }
    public void onUpgrade(SQLiteDatabase db,int oldversion,int newVersion){}
}
