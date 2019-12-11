package com.example.player;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class xiu extends AppCompatActivity {
    database database;
    Cursor cursor;
    ListView listView;
    TextView textView;
    Button button;
    Button button1;
    int num;
    private List<music> musicList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiu);
        database=new database(this,"database.db",null,1);
        final SQLiteDatabase db=database.getWritableDatabase();
        getPath();
        MusicAdapter musicAdapter=new MusicAdapter(xiu.this,R.layout.music_item,musicList);
        listView=(ListView) findViewById(R.id.xiu_list);
        textView=(TextView) findViewById(R.id.xiu_text);
        button=(Button) findViewById(R.id.music_add);
        button1=(Button) findViewById(R.id.complete);
        listView.setAdapter(musicAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    music music=musicList.get(position);
                    num=position;
                    String path=music.getPath();
                    textView.setText("歌曲详情：\n"+"歌曲专辑："+music.getAlbum()+"\n歌曲名："+music.getName()+"\n歌曲时长："+music.getDuration());
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values=new ContentValues();
                music music=musicList.get(num);
                values.put("name",music.getName());
                values.put("path",music.getPath());
                values.put("album",music.getAlbum());
                values.put("duration",music.getDuration());
                values.put("artist",music.getArtist());
                values.put("musicId",music.getMusicId());
                db.insert("music",null,values);
                values.clear();
                Toast.makeText(xiu.this,"添加成功！",Toast.LENGTH_SHORT).show();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(xiu.this,MainActivity.class);
                startActivity(intent);
            }
        });
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
