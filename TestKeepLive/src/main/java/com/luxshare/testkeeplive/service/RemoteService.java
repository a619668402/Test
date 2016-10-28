package com.luxshare.testkeeplive.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.luxshare.testkeeplive.aidl.IMyAidlInterface;

public class RemoteService extends Service {

    private MyConn mConn;
    private MyBinder mBinder;


    public RemoteService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mConn = new MyConn();
        mBinder = new MyBinder();
    }

    @Override
    public IBinder onBind(Intent intent) {

        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this,"远程服务活了",Toast.LENGTH_SHORT).show();
        this.bindService(new Intent(RemoteService.this,LocalService.class),mConn, Context.BIND_IMPORTANT);
        return START_STICKY;
    }


    class MyBinder extends IMyAidlInterface.Stub {

        @Override
        public String getServiceName() throws RemoteException {
            return RemoteService.class.getSimpleName();
        }
    }

    class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("TAG","绑定本地服务成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            Log.i("TAG","本地服务被杀死--remote");

            // 开启本地服务
            RemoteService.this.startService(new Intent(RemoteService.this,LocalService.class));
            // 绑定本地服务
            RemoteService.this.bindService(new Intent(RemoteService.this,LocalService.class),mConn, Context.BIND_IMPORTANT);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 开启本地服务
        RemoteService.this.startService(new Intent(RemoteService.this,LocalService.class));
        // 绑定本地服务
        RemoteService.this.bindService(new Intent(RemoteService.this,LocalService.class),mConn, Context.BIND_IMPORTANT);

    }
}
