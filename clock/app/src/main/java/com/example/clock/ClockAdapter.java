package com.example.clock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import static android.content.Context.ALARM_SERVICE;

public class ClockAdapter extends ArrayAdapter<clock> {
    ContentValues values = new ContentValues();
    database database;
    boolean r;
    private int resourceId;
    Context context;
    Calendar calendar=Calendar.getInstance();
    public ClockAdapter(Context context, int textViewResourceId, List<clock> objects){
        super(context,textViewResourceId,objects);
        this.context=context;
        resourceId=textViewResourceId;
        database = new database(context, "database.db", null, 1);
    }
    public View getView(final int position, View convertView, ViewGroup parent){
        final clock clock=getItem(position);

        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        Switch s=(Switch) view.findViewById(R.id.clock_switch);
        TextView WordName=(TextView) view.findViewById(R.id.clock_get);
        WordName.setText(clock.getHour()+":"+clock.getMinute());
        if(clock.isClock_switch().equals("true")){
            r=true;
        }
        else{
            r=false;
        }
        s.setChecked(r);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    values.put("jurge","true");
                    final SQLiteDatabase db = database.getWritableDatabase();
                    db.update("clock",values,"hour=? and minute=?",new String[]{clock.getHour(),clock.getMinute()});
                    values.clear();
                    AlarmManager am;
                    am=(AlarmManager) context.getSystemService(ALARM_SERVICE);
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY,Integer.valueOf(clock.getHour()));
                    calendar.set(Calendar.MINUTE,Integer.valueOf(clock.getMinute()));
                    calendar.set(Calendar.MILLISECOND,0);
                    calendar.set(Calendar.SECOND,0);
                    Intent intent=new Intent(context,amReceiver.class);
                    String p=clock.getPath();
                    intent.putExtra("path",p);
                    PendingIntent pendingIntent=PendingIntent.getBroadcast(context,0,intent,0);
                    am.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                    Toast.makeText(context,"666",Toast.LENGTH_SHORT).show();
                }
                else if(isChecked==false){
                    values.put("jurge","false");
                    final SQLiteDatabase db = database.getWritableDatabase();
                    db.update("clock",values,"hour=? and minute=?",new String[]{clock.getHour(),clock.getMinute()});
                    values.clear();
                    Intent intent = new Intent(context, amReceiver.class);
                    PendingIntent sender=PendingIntent.getBroadcast(
                            context,0, intent, 0);
                    AlarmManager am;
                    am =(AlarmManager)context.getSystemService(ALARM_SERVICE);
                    am.cancel(sender);
                }
            }
        });
        return view;
    }
}
