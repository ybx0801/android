package com.example.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class test extends AppCompatActivity {
    TextView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Intent intent=getIntent();
        String s=intent.getStringExtra("s");
        view=(TextView) findViewById(R.id.textView2) ;
        view.setText(s);
        Log.d("test",s);
    }
}
