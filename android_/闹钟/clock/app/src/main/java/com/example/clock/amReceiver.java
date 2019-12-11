package com.example.clock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class amReceiver extends BroadcastReceiver {
    public amReceiver(){}
    public  void onReceive(Context context, Intent intent){
        String path=intent.getStringExtra("path");
        Intent intent1=new Intent(context,AmShow.class);
        intent1.putExtra("path",path);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }
}
