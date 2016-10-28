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

public class LocalService extends Service {

    private MyBinder mBinder;

    private MyConn mMyConn;

    public LocalService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBinder = new MyBinder();
        mMyConn = new MyConn();
    }

    @Override
    public IBinder onBind(Intent intent) {

        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this,"本地服务活了",Toast.LENGTH_SHORT).show();
        this.bindService(new Intent(LocalService.this,RemoteService.class),mMyConn,Context.BIND_IMPORTANT);

        return START_STICKY;
    }

    class MyBinder extends IMyAidlInterface.Stub {

        @Override
        public String getServiceName() throws RemoteException {
            return LocalService.class.getSimpleName();
        }
    }

    class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("TAG", "绑定上了远程服务");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("TAG", "远程服务被Kill");

            // 开启远程服务
            LocalService.this.startService(new Intent(LocalService.this, RemoteService.class));
            // 绑定远程服务
            LocalService.this.bindService(new Intent(LocalService.this, RemoteService.class), mMyConn, Context.BIND_IMPORTANT);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 开启远程服务
        LocalService.this.startService(new Intent(LocalService.this, RemoteService.class));
        // 绑定远程服务
        LocalService.this.bindService(new Intent(LocalService.this, RemoteService.class), mMyConn, Context.BIND_IMPORTANT);
    }
}
