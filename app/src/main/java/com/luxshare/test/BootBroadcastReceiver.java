package com.luxshare.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Administrator on 2016/10/6.
 */
public class BootBroadcastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context,TimeService.class);
        context.startService(service);
        Log.i("TAG","---onReceive---");
        Intent intent1 = context.getPackageManager().getLaunchIntentForPackage("com.luxshare.test");
        context.startActivity(intent1);
    }
}
