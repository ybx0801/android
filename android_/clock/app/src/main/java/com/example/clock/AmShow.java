package com.example.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.Calendar;

public class AmShow extends AppCompatActivity {
    MediaPlayer mediaPlayer=new MediaPlayer();
    TextView textView;
    database database;
    Cursor cursor;
    clock clock;
    Calendar calendar=Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_am_show);
        database = new database(this, "database.db", null, 1);
        Intent intent=getIntent();
        final String path=intent.getStringExtra("path");
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AlertDialog.Builder builder=new AlertDialog.Builder(AmShow.this);
        LayoutInflater inflater=getLayoutInflater();
        View a=inflater.inflate(R.layout.show_dialog,null);
        builder.setView(a).setTitle("闹钟提示");
        builder.setPositiveButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final SQLiteDatabase db = database.getWritableDatabase();
                cursor = db.query("clock", null, "path=?", new String[]{path}, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        clock = new clock(cursor.getString(cursor.getColumnIndex("hour")), cursor.getString(cursor.getColumnIndex("minute")),cursor.getString(cursor.getColumnIndex("path")),cursor.getString(cursor.getColumnIndex("jurge")));

                    } while (cursor.moveToNext());
                }
                ContentValues values = new ContentValues();
                values.put("jurge","false");
                db.update("clock",values,"hour=? and minute=?",new String[]{clock.getHour(),clock.getMinute()});
                AmShow.this.finish();
                mediaPlayer.reset();
            }
        });
        builder.setNegativeButton("3min后再次提醒", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final SQLiteDatabase db = database.getWritableDatabase();
                cursor = db.query("clock", null, "path=?", new String[]{path}, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        clock = new clock(cursor.getString(cursor.getColumnIndex("hour")), cursor.getString(cursor.getColumnIndex("minute")),cursor.getString(cursor.getColumnIndex("path")),cursor.getString(cursor.getColumnIndex("jurge")));

                    } while (cursor.moveToNext());
                }
                calendar.setTimeInMillis(System.currentTimeMillis());
                AlarmManager am ;
                am=(AlarmManager) AmShow.this.getSystemService(ALARM_SERVICE);
                Intent intent=new Intent(AmShow.this,amReceiver.class);
                intent.putExtra("path",clock.getPath());
                PendingIntent pendingIntent=PendingIntent.getBroadcast(AmShow.this,0,intent,0);
                am.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+3000,pendingIntent);
                mediaPlayer.reset();
                AmShow.this.finish();
            }
        });
        builder.show();
    }
}
