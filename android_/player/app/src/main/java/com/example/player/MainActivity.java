package com.example.player;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    database database;
    Button play;
    Button stop;
    Button previous;
    Button next;
    Cursor cursor;
    Button delete;
    Button xun;
    SeekBar seekBar;
    boolean isSeekBarChanging;
    int currentPosition;
    Timer timer;
    private MediaPlayer mediaPlayer=new MediaPlayer();
    private List<music> musicList=new ArrayList<>();
    ListView listView;
    TextView textView;
    static public int musicNum;
    static public  int i=0;
    private List<music> musicList1=new ArrayList<>();
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.xiu:
                Intent intent=new Intent(MainActivity.this,xiu.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play=(Button) findViewById(R.id.start);
        stop=(Button) findViewById(R.id.stop);
        previous=(Button) findViewById(R.id.previous);
        next=(Button) findViewById(R.id.next);
        delete=(Button) findViewById(R.id.delete);
        textView=(TextView) findViewById(R.id.test);
        xun=(Button) findViewById(R.id.moshi);
        seekBar=(SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isSeekBarChanging=true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isSeekBarChanging=false;
                mediaPlayer.seekTo(seekBar.getProgress());

            }
        });
        play.setOnClickListener(this);
        stop.setOnClickListener(this);
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        else{
            database=new database(this,"database.db",null,1);
            final SQLiteDatabase db=database.getWritableDatabase();
            cursor=db.query("music",null,null,null,null,null,null);
            if(cursor.moveToFirst()){
                do{
                    music music=new music(cursor.getString(cursor.getColumnIndex("path")),cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("album")),cursor.getString(cursor.getColumnIndex("artist")),cursor.getInt(cursor.getColumnIndex("musicId")),cursor.getInt(cursor.getColumnIndex("duration")));
                    musicList.add(music);
                }while (cursor.moveToNext());
            }
            MusicAdapter musicAdapter=new MusicAdapter(MainActivity.this,R.layout.music_item,musicList);
            listView=(ListView) findViewById(R.id.list);
            listView.setAdapter(musicAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.reset();
                        }
                        music music=musicList.get(position);
                        String path=music.getPath();
                        textView.setText("正在播放："+music.getName());
                        musicNum=position;
                        initMedisPlayer(path);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==0) {
                    musicNum = musicNum == musicList.size() - 1 ? 0 : musicNum + 1;
                }
                else if(i==1){
                    int j=musicNum;
                    musicNum=(int)(Math.random()*musicList.size());
                    while(j==musicNum){
                        musicNum=(int)(Math.random()*musicList.size());
                    }
                }
                music music = musicList.get(musicNum);
                String path = music.getPath();
                initMedisPlayer(path);
                textView.setText("正在播放：" + music.getName());
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==0) {
                    musicNum = musicNum - 1 < 0 ? musicList.size() - 1 : musicNum - 1;
                }
                else if(i==1){
                    int j=musicNum;
                    musicNum=(int)(Math.random()*musicList.size());
                   while(j==musicNum){
                        musicNum=(int)(Math.random()*musicList.size());
                    }
                }
                music music = musicList.get(musicNum);
                String path = music.getPath();
                initMedisPlayer(path);
                textView.setText("正在播放：" + music.getName());
            }
        });
        xun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(xun.getText().toString().equals("循环")){
                    xun.setText("随机");
                   i=1;
                }
                else if(xun.getText().toString().equals("随机")){
                    xun.setText("循环");
                    i=0;
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music music=musicList.get(musicNum);
                SQLiteDatabase db=database.getWritableDatabase();
                db.delete("music","name=?",new String[]{music.getName()});
                mediaPlayer.reset();
                play.setText("开始");
                textView.setText("正在播放：无");
                Toast.makeText(MainActivity.this,"删除成功！",Toast.LENGTH_SHORT).show();
                cursor=db.query("music",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        music music1=new music(cursor.getString(cursor.getColumnIndex("path")),cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("album")),cursor.getString(cursor.getColumnIndex("artist")),cursor.getInt(cursor.getColumnIndex("musicId")),cursor.getInt(cursor.getColumnIndex("duration")));
                        musicList1.add(music1);
                    }while (cursor.moveToNext());
                }
                MusicAdapter musicAdapter=new MusicAdapter(MainActivity.this,R.layout.music_item,musicList1);
                listView=(ListView) findViewById(R.id.list);
                listView.setAdapter(musicAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        try {
                            if(mediaPlayer.isPlaying()){
                                mediaPlayer.reset();
                            }
                            music music=musicList1.get(position);
                            String path=music.getPath();
                            textView.setText("正在播放："+music.getName());
                            musicNum=position;
                            initMedisPlayer(path);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
    private void initMedisPlayer(String path){
        try{
            mediaPlayer.reset();
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    mp.seekTo(currentPosition);
                    seekBar.setMax(mediaPlayer.getDuration());
                }
            });
            timer=new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if(!isSeekBarChanging){
                        seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    }
                }
            },0,50);
            play.setText("暂停");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void onRequestPermissionsResult(int requestCode,String [] permissions,int[] grantResults){
        switch(requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    getPath();
                }
                else{
                    Toast.makeText(this,"拒绝访问权限",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                    play.setText("暂停");
                }
                else if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    currentPosition=mediaPlayer.getCurrentPosition();
                    play.setText("开始");
                }
                break;
            case R.id.stop:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.reset();
                    textView.setText("正在播放：无");
                    play.setText("开始");
                }
                break;
        }
    }
    protected void onDestory(){
        super.onDestroy();
        if(mediaPlayer!=null){
           mediaPlayer.stop();
           mediaPlayer.release();
        }
    }
    public void getPath(){
        cursor=getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if(cursor.moveToFirst()){
            do{
                music music=new music(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)),cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)),cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)),cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)),cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)),cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
               musicList.add(music);
            }
            while (cursor.moveToNext());
        }
    }
}
