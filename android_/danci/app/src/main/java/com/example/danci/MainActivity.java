package com.example.danci;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.danci.jiexi.query;
import static com.example.danci.jiexi.wang;
import static com.example.danci.jiexi.word;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private database database;
    EditText editText;
    EditText editText1;
    EditText editText2;
    String s;
    String e;
    String l;
    Button adddata;
    Button cha;
    Button xiu;
    Button ju;
    TextView textView;
    Button cikuCha;
    TextView show;
    TextView show2;
    TextView show3;
    TextView show4;
    ListView listView;
    Button delete;
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.add_item:
                Intent intent=new Intent(MainActivity.this,xiugai.class);
                startActivity(intent);
                break;
            case R.id.ciku_item:
                Intent intent1=new Intent(MainActivity.this,ciku.class);
                startActivity(intent1);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=new database(this,"database.db",null,1);
        Button createdatabase=(Button) findViewById(R.id.create_database);
        editText=(EditText) findViewById(R.id.word);
        editText1=(EditText) findViewById(R.id.ex);
        textView=(TextView) findViewById(R.id.show);
        cha=(Button) findViewById(R.id.cha);
        adddata=(Button) findViewById(R.id.add_data);
        cikuCha=(Button) findViewById(R.id.send);
        xiu=(Button) findViewById(R.id.xiugai);
        show=(TextView) findViewById(R.id.show2);
        show2=(TextView) findViewById(R.id.show3);
        show3=(TextView) findViewById(R.id.show4);
        show4=(TextView) findViewById(R.id.show5);
        listView=(ListView) findViewById(R.id.listview);
        delete=(Button) findViewById(R.id.delete);
        editText2=(EditText) findViewById(R.id.liju);
        ju=(Button) findViewById(R.id.xiugai2);
        cha.setOnClickListener(this);
        //模糊查询列表
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    listView.setVisibility(View.GONE);
                    listView.setAdapter(null);
                }
                else {
                    int i=0;
                    listView.setVisibility(View.VISIBLE);
                    String str=editText.getText().toString().trim();
                    SQLiteDatabase db=database.getWritableDatabase();
                    final Cursor cursor=db.rawQuery("select * from word where word like '%"+str+"%'",null);
                    if(cursor.moveToFirst()){
                        do{
                            i=i+1;
                        }while(cursor.moveToNext());
                    }
                   if(cursor.moveToFirst()){
                       String [] data=new String[i];
                       String[] data1=new String[i];
                       i=0;
                       do{
                           data[i]=cursor.getString(cursor.getColumnIndex("word"));
                           i++;
                       } while(cursor.moveToNext());
                       ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,data);
                       listView.setAdapter(arrayAdapter);
                   }

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                cursor.moveToPosition(position);
                                String word=cursor.getString(cursor.getColumnIndex("word"));
                                editText.setText(word);
                                listView.setVisibility(View.GONE);
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //修改
        ju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=database.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("ju",editText2.getText().toString());
                db.update("word",values,"word=?",new String[]{editText.getText().toString()});
                Toast.makeText(MainActivity.this,"修改例句成功",Toast.LENGTH_SHORT).show();
            }
        });
        xiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=database.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("ex1",editText1.getText().toString());
                db.update("word",values,"word = ?",new String[]{editText.getText().toString()});
                Toast.makeText(MainActivity.this,"修改单词释义成功！",Toast.LENGTH_SHORT).show();
            }
        });
        //添加
        adddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=database.getWritableDatabase();
                ContentValues values=new ContentValues();
                s=editText.getText().toString();
                e=editText1.getText().toString();
                l=editText2.getText().toString();
                values.put("word",s);
                values.put("ex1",e);
                values.put("ju",l);
                values.put("ex",wang);
                textView.setText(s);
                show2.setText(e);
                show4.setText(l);
                db.insert("word",null,values);
                values.clear();
                Toast.makeText(MainActivity.this,"添加成功！",Toast.LENGTH_SHORT).show();
            }
        });
        //查询词库
        cikuCha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=database.getWritableDatabase();
                Cursor cursor=db.query("word",null,"word=?",new String[]{editText.getText().toString()},null,null,null);
                textView.setText("");
                show2.setText("");
                if(cursor.moveToFirst()){
                    String word=cursor.getString(cursor.getColumnIndex("word"));
                    String ex=cursor.getString(cursor.getColumnIndex("ex1"));
                    String ju=cursor.getString(cursor.getColumnIndex("ju"));
                    System.out.println(word);
                    textView.setText(word+"\n");
                    show2.setText(ex+"\n");
                    show4.setText(ju+"\n");
                    if(show2==null){
                        show2.setText("还未储存单词释义");
                    }
                    if(show4==null){
                        show4.setText("还未储存例句");
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"词库中还未储存该单词",Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        });
        createdatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.getWritableDatabase();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=database.getWritableDatabase();
                db.delete("word","word=?",new String[]{editText.getText().toString()});
                Toast.makeText(MainActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
    //网络查询事项
    public void onClick(View view){
        if(view.getId()==R.id.cha){
           sendRequest();
        }
    }
    private void showResponse(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(editText.getText().toString());
                if(!query.equals(editText.getText().toString())){
                    show2.setText("");
                }
                show.setText(word);
                show3.setText(wang);
            }
        });
    }
    //网络查询方法
    public void sendRequest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                BufferedReader reader=null;
                try{
                    URL url=new URL("http://fanyi.youdao.com/openapi.do?keyfrom=youdanfanyi123&key=1357452033&type=data&doctype=json&version=1.1&q="+editText.getText().toString());
                    connection=(HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in=connection.getInputStream();
                    reader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder response=new StringBuilder();
                    String line;
                    while((line=reader.readLine())!=null){
                        response.append(line);
                    }
                    jiexi.parsexml(response.toString());
                    showResponse();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    if(reader!=null){
                        try{
                            reader.close();
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
