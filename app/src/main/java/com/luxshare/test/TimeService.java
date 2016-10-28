package com.luxshare.test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2016/10/6.
 */
public class TimeService extends Service {

    public static final String TAG = "TimeService";

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("Service onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("Service onStartCommand");
        flags = START_STICKY;
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("Service onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent service = new Intent(this,TimeService.class);
        startService(service);
    }
}
