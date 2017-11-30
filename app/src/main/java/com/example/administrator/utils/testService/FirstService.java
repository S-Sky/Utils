package com.example.administrator.utils.testService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2017/7/13 0013.
 */

public class FirstService extends Service {

    //是绑定的服务(bound service)的生命周期方法
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("TAG", "onBind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("TAG", "onUnbind");
        return super.onUnbind(intent);
    }

    //开启服务(Started Service)需要执行的生命周期方法
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("TAG", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("TAG", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("TAG", "onDestroy");
    }
}
