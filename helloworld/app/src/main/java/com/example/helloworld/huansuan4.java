package com.example.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class huansuan4 extends AppCompatActivity {
    TextView textView;
    TextView textView2;
    Button button;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button10;
    Button button11;
    ArrayAdapter<String> arrayAdapter;
    List<String> list;
    Spinner spinner;
    Spinner spinner1;
    String s;
    String s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huansuan4);
        spinner=(Spinner) findViewById(R.id.spinner);
        spinner1=(Spinner) findViewById(R.id.spinner2);
        textView=(TextView) findViewById(R.id.textView3);
        textView2=(TextView) findViewById(R.id.textView5);
        button=(Button) findViewById(R.id.button25);
        button1=(Button) findViewById(R.id.button26);
        button2=(Button) findViewById(R.id.button27);
        button3=(Button) findViewById(R.id.button28);
        button4=(Button) findViewById(R.id.button29);
        button5=(Button) findViewById(R.id.button30);
        button6=(Button) findViewById(R.id.button31);
        button7=(Button) findViewById(R.id.button32);
        button8=(Button) findViewById(R.id.button33);
        button9=(Button) findViewById(R.id.button34);
        button10=(Button) findViewById(R.id.button35);
        button11=(Button) findViewById(R.id.button36);
        data();
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        spinner.setAdapter(arrayAdapter);
        spinner1.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s=list.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s1=list.get(position);
                String s3=huansuan.d(textView.getText().toString(),s,s1);
                textView2.setText(s3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+button.getText().toString());
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+button1.getText().toString());
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+button2.getText().toString());
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+button3.getText().toString());
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+button4.getText().toString());
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+button5.getText().toString());
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+button6.getText().toString());
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+button7.getText().toString());
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+button8.getText().toString());
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+button10.getText().toString());
            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText().toString()+button11.getText().toString());
            }
        });

    }
    public void data(){
        list=new ArrayList<String>();
        list.add("s");
        list.add("min");
        list.add("h");
    }
}
