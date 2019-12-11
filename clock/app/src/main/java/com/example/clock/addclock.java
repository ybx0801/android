package com.example.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.clock.MainActivity.flag;

public class addclock extends AppCompatActivity implements View.OnClickListener {
    Cursor cursor;
    Context context;
    Button button;
    Button save;
    Button delete;
    TextView hour;
    TextView minute1;
    String hour_save;
    String minute_save;
    ListView listView;
    TextView textView;
    EditText editText;
    Calendar calendar=Calendar.getInstance();
    private List<music> musicList=new ArrayList<>();
    private List<clock> clockList=new ArrayList<>();
    database database;
    String path;
    clock clock_xiu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database=new database(this,"database.db",null,1);
        final SQLiteDatabase db=database.getWritableDatabase();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addclock);
        button=(Button) findViewById(R.id.add);
        hour=(TextView) findViewById(R.id.hour);
        minute1=(TextView) findViewById(R.id.minute);
        save=(Button) findViewById(R.id.save);
        delete=(Button) findViewById(R.id.delete);
        textView=(TextView) findViewById(R.id.show_music);
        listView=(ListView) findViewById(R.id.music_item1);
        editText=(EditText) findViewById(R.id.data);
        button.setOnClickListener(this);
        if(flag==1){
            Intent intent=getIntent();
            String num=intent.getStringExtra("num");
            int i=Integer.valueOf(num);
            cursor = db.query("clock", null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    clock clock = new clock(cursor.getString(cursor.getColumnIndex("hour")), cursor.getString(cursor.getColumnIndex("minute")),cursor.getString(cursor.getColumnIndex("path")),cursor.getString(cursor.getColumnIndex("jurge")));
                    clockList.add(clock);

                } while (cursor.moveToNext());
            }
            clock_xiu=clockList.get(i);
            hour.setText(clock_xiu.getHour());
            minute1.setText(clock_xiu.getMinute());
            textView.setText(clock_xiu.getPath());
        }
        getPath();
        MusicAdapter musicAdapter=new MusicAdapter(addclock.this,R.layout.music_item,musicList);
        listView.setAdapter(musicAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                music music=musicList.get(position);
                path=music.getPath();
                textView.setText(music.getName()+"\n"+music.getPath());
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                if (flag==1){
                    cursor = db.query("clock", null, "hour=? and minute=?", new String[]{clock_xiu.getHour(), clock_xiu.getMinute()}, null, null, null);
                if (cursor.moveToFirst()) {
                    values.put("hour", hour_save);
                    values.put("minute", minute_save);
                    values.put("path", path);
                    values.put("jurge","false");
                    db.update("clock", values, "hour=? and minute=?", new String[]{clock_xiu.getHour(), clock_xiu.getMinute()});
                    values.clear();
                    Toast.makeText(addclock.this, "修改成功", Toast.LENGTH_SHORT).show();
                    flag=0;
                    }
                }
                else{
                    values.put("hour", hour_save);
                    values.put("minute", minute_save);
                    values.put("path", path);
                    values.put("jurge","false");
                    db.insert("clock", null, values);
                    values.clear();
                    Toast.makeText(addclock.this, "保存成功", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete("clock","hour=? and minute=?",new String[]{clock_xiu.getHour(),clock_xiu.getMinute()});
                Intent intent1=new Intent(addclock.this,amReceiver.class);
                PendingIntent sender1=PendingIntent.getBroadcast(
                        addclock.this,0, intent1, 0);
                AlarmManager am;
                am =(AlarmManager)addclock.this.getSystemService(ALARM_SERVICE);
                am.cancel(sender1);
                flag=0;
                finish();
            }
        });
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.add:
                amON();
                break;

        }
    }
    public void amON(){
        calendar.setTimeInMillis(System.currentTimeMillis());
        int mhour = calendar.get(Calendar.HOUR_OF_DAY);
        int mminute = calendar.get(Calendar.MINUTE);
        TimePickerDialog dialog=new TimePickerDialog(addclock.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);
                hour_save=String.valueOf(hourOfDay);
                minute_save=String.valueOf(minute);
                hour.setText(hour_save);
                minute1.setText(minute_save);
                ContentValues values=new ContentValues();
                Toast.makeText(addclock.this,"666",Toast.LENGTH_SHORT).show();

            }
        },mhour,mminute,true);
        dialog.show();
    }
    public void getPath(){
        cursor=getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if(cursor.moveToFirst()){
            do{
                music music=new music(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)),cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));
                musicList.add(music);
            }
            while (cursor.moveToNext());
        }
    }
}
