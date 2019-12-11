package com.example.helloworld;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    TextView show;
    TextView show2;
    Button bn;
    Button bn2;
    Button bn3;
    Button bn4;
    Button bn5;
    Button bn6;
    Button bn7;
    Button bn8;
    Button bn9;
    Button bn10;
    Button eual;
    Button and;
    Button jian;
    Button cheng;
    Button bn11;
    Button bn12;
    Button pingfang;
    Button ce;
    Button dian;
    Button chu;
    Button sin;
    Button cos;
    Button zero;
    Button chuyu;
    EditText text1;
    EditText text2;
    String s;
    int i=1;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.qes:
                Intent intent=new Intent(MainActivity.this,qes.class);
                startActivity(intent);
                break;
            case R.id.exit:
                finish();
                break;
            case R.id.danwei:
                Intent intent1=new Intent(MainActivity.this,haunsuan.class);
                startActivity(intent1);
                break;
            case R.id.zhongliang:
                Intent intent2=new Intent(MainActivity.this,huansuan2.class);
                startActivity(intent2);
                break;
            case R.id.tiji:
                Intent intent3=new Intent(MainActivity.this,huansuan3.class);
                startActivity(intent3);
                break;
            case R.id.shijian:
                Intent intent4=new Intent(MainActivity.this,huansuan4.class);
                startActivity(intent4);
                break;
            case R.id.jinzhi:
                Intent intent5=new Intent(MainActivity.this,huansuan5.class);
                startActivity(intent5);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show = (TextView) findViewById(R.id.show);
        show2=(TextView) findViewById(R.id.textView4);
        bn = (Button) findViewById(R.id.button);
        bn2 = (Button) findViewById(R.id.button2);
        bn3 = (Button) findViewById(R.id.button3);
        bn4 = (Button) findViewById(R.id.button4);
        bn5 = (Button) findViewById(R.id.button5);
        bn6 = (Button) findViewById(R.id.button6);
        bn7 = (Button) findViewById(R.id.button7);
        bn8 = (Button) findViewById(R.id.button8);
        bn9 = (Button) findViewById(R.id.button9);
        bn10 = (Button) findViewById(R.id.button10);
        eual=(Button) findViewById(R.id.button13) ;
        and=(Button) findViewById(R.id.button12);
        jian=(Button) findViewById(R.id.button14);
        cheng=(Button) findViewById(R.id.button15);
        bn11=(Button) findViewById(R.id.button17);
        bn12=(Button) findViewById(R.id.button18);
        pingfang=(Button) findViewById(R.id.button19);
        ce=(Button) findViewById(R.id.button16);
        dian=(Button) findViewById(R.id.button21);
        chu=(Button) findViewById(R.id.button24);
        sin=(Button) findViewById(R.id.button23);
        cos=(Button) findViewById(R.id.button20);
        zero=(Button) findViewById(R.id.button11);
        chuyu=(Button) findViewById(R.id.button22);
        bn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final LayoutInflater inflater = getLayoutInflater();
                final View a = inflater.inflate(R.layout.login_dialog, null);
                builder.setView(a).setTitle("login");
                builder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        text1 = (EditText) a.findViewById(R.id.edit1);
                        text2 = (EditText) a.findViewById(R.id.edit2);
                        if (text1.getText().toString().equals("abc") && text2.getText().toString().equals("123")) {
                            Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
            bn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.setText(show.getText().toString() + bn.getText().toString());

                }
            });
            bn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.setText(show.getText().toString() + bn2.getText().toString());
                }
            });
            bn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.setText(show.getText().toString() + bn3.getText().toString());
                }
            });
            bn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.setText(show.getText().toString() + bn4.getText().toString());
                }
            });
            bn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.setText(show.getText().toString() + bn5.getText().toString());
                }
            });
            bn6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.setText(show.getText().toString() + bn6.getText().toString());
                }
            });
            bn7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.setText(show.getText().toString() + bn7.getText().toString());
                }
            });
            bn8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.setText(show.getText().toString() + bn8.getText().toString());
                }
            });
            bn9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.setText(show.getText().toString() + bn9.getText().toString());
                }
            });
            eual.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s=show.getText().toString();
                    String s1=(String) calculator.d(s);
                    String s2;
                    if(s1.equals("Infinity")){
                        s2="0不能为除数！";
                        show2.setText(s2);
                    }
                    else {
                        show2.setText(s1);
                    Intent intent=new Intent(MainActivity.this,test.class);
                    intent.putExtra("s",s1);
                    startActivity(intent);
                    }
                }
            });
            and.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.setText(show.getText().toString()+and.getText().toString());
                }
            });
            jian.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     show.setText(show.getText().toString()+jian.getText().toString());
                 }
            });
            cheng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.setText(show.getText().toString()+cheng.getText().toString());
                }
            });
            bn11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.setText(show.getText().toString()+bn11.getText().toString());
                }
            });
            bn12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.setText(show.getText().toString()+bn12.getText().toString());
                }
            });
            pingfang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.setText(show.getText().toString()+pingfang.getText().toString());
                }
            });
            ce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.setText("");
                }
            });
            dian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.setText(show.getText().toString()+dian.getText().toString());
                }
            });
           chu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.setText(show.getText().toString()+chu.getText().toString());
                }
            });
            sin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.setText(show.getText().toString()+"s");
                }
                });
           cos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.setText(show.getText().toString()+"c");
                }
            });
            zero.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.setText(show.getText().toString()+zero.getText().toString());
                }
            });
            chuyu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.setText(show.getText().toString()+chuyu.getText().toString());
                }
            });
    }

}
