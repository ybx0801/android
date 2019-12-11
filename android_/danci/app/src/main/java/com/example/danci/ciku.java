package com.example.danci;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ciku extends AppCompatActivity {
    private List<Word> wordList=new ArrayList<>();
    private List<Word> wordList1=new ArrayList<>();
    private database database;
    int i=0;
    ListView listView;
    ListView listView1;
    TextView textView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciku);
        listView=(ListView) findViewById(R.id.ciku) ;
        textView=(TextView) findViewById(R.id.textView2);
        editText=(EditText) findViewById(R.id.shu);
        listView1=(ListView) findViewById(R.id.ciku1);
        database=new database(this,"database.db",null,1);
        final SQLiteDatabase db=database.getWritableDatabase();
        final Cursor cursor=db.query("word",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Word word=new Word(cursor.getString(cursor.getColumnIndex("word")));
                wordList.add(word);
            }while (cursor.moveToNext());
        }
        WordAdapter wordAdapter=new WordAdapter(ciku.this,R.layout.word_item,wordList);
        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=wordList.get(position);
                String w=word.getName();
                Cursor cursor1=db.query("word",null,"word=?",new String[]{w},null,null,null);
                if(cursor1.moveToFirst()){
                    textView.setText("单词释义："+cursor1.getString(cursor1.getColumnIndex("ex1"))+"\n"+cursor1.getString(cursor1.getColumnIndex("ex")));
                }
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    listView1.setVisibility(View.GONE);
                    listView1.setAdapter(null);
                    wordList1.clear();
                }
                else {
                    listView1.setVisibility(View.VISIBLE);
                    String str=editText.getText().toString().trim();
                    SQLiteDatabase db=database.getWritableDatabase();
                    final Cursor cursor=db.rawQuery("select * from word where word like '%"+str+"%'",null);
                    if(cursor.moveToFirst()){
                        do{
                            Word word=new Word(cursor.getString(cursor.getColumnIndex("word")));
                            wordList1.add(word);
                        } while(cursor.moveToNext());
                        WordAdapter wordAdapter=new WordAdapter(ciku.this,R.layout.word_item,wordList1);
                        listView1.setAdapter(wordAdapter);
                    }

                    listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Word word=wordList1.get(position);
                            String w=word.getName();
                            SQLiteDatabase db=database.getWritableDatabase();
                            Cursor cursor2=db.query("word",null,"word=?",new String[]{w},null,null,null);
                            if(cursor2.moveToFirst()){
                                textView.setText("单词释义："+cursor2.getString(cursor2.getColumnIndex("ex1"))+"\n"+cursor2.getString(cursor2.getColumnIndex("ex")));
                            }
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
