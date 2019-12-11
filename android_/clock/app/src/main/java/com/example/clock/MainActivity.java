package com.example.clock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Cursor cursor;
    database database;
    ListView listView;
    TextView textView;
    static int flag=0;
    private List<clock> clockList=new ArrayList<>();
    Calendar calendar=Calendar.getInstance();
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.clock_item:
                Intent intent=new Intent(MainActivity.this,addclock.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        else {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            listView = (ListView) findViewById(R.id.clock_list);
            database = new database(this, "database.db", null, 1);
            final SQLiteDatabase db = database.getWritableDatabase();
            cursor = db.query("clock", null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    clock clock = new clock(cursor.getString(cursor.getColumnIndex("hour")), cursor.getString(cursor.getColumnIndex("minute")),cursor.getString(cursor.getColumnIndex("path")),cursor.getString(cursor.getColumnIndex("jurge")));
                    clockList.add(clock);

                } while (cursor.moveToNext());
            }
            ClockAdapter clockAdapter = new ClockAdapter(MainActivity.this, R.layout.clock_item, clockList);
            listView.setAdapter(clockAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    clock clock=clockList.get(position);
                    int num=position;
                    Intent intent=new Intent(MainActivity.this,addclock.class);
                    intent.putExtra("num",String.valueOf(num));
                    flag=1;
                    startActivity(intent);
                }
            });
        }
    }
    public void onRequestPermissionsResult(int requestCode,String [] permissions,int[] grantResults){
        switch(requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                }
                else{
                    Toast.makeText(this,"拒绝访问权限",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }
    protected void onRestart() {
        super.onRestart();
        clockList.clear();
        database = new database(this, "database.db", null, 1);
        final SQLiteDatabase db = database.getWritableDatabase();
        cursor = db.query("clock", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                clock clock = new clock(cursor.getString(cursor.getColumnIndex("hour")), cursor.getString(cursor.getColumnIndex("minute")),cursor.getString(cursor.getColumnIndex("path")),cursor.getString(cursor.getColumnIndex("jurge")));
                clockList.add(clock);

            } while (cursor.moveToNext());
        }
        ClockAdapter clockAdapter = new ClockAdapter(MainActivity.this, R.layout.clock_item, clockList);
        listView.setAdapter(clockAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clock clock=clockList.get(position);
                int num=position;
                Intent intent=new Intent(MainActivity.this,addclock.class);
                intent.putExtra("num",String.valueOf(num));
                flag=1;
                startActivity(intent);
            }
        });
    }
}
