package com.example.administrator.utils.testService;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 静态注册
 * Bound Service
 * Created by Administrator on 2017/7/13 0013.
 */

public class BindService extends Service {

    @Override
    public void onCreate() {
        Log.i("TAG", "onCreate");
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("TAG", "onBind");
        return null;
    }

    @Override //启动服务的方法,绑定的时候不执行
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("TAG", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("TAG", "onUnbind");
        return super.onUnbind(intent);

    }

    @Override
    public void onDestroy() {
        Log.i("TAG", "onDestroy");
        super.onDestroy();
    }
}
