package com.example.administrator.utils.testService;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

/**
 * Created by Administrator on 2017/7/13 0013.
 */

public class BindService2 extends Service {

    //2、IBinder: 接口,用来在不用的组件或者进程中进行信息的传递
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("onBind", "onBind");
        return new MyBinder();
    }

    //3、自定义IBinder类
    class MyBinder extends Binder {

        //获取服务类的对象
        public BindService2 getInstance() {
            return BindService2.this;
        }
    }

    //写一个获取随机数的方法(100以内)
    public int getRandomValue() {
        return new Random().nextInt(100);
    }
}
