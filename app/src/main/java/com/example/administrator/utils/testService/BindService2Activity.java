package com.example.administrator.utils.testService;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.utils.R;
import com.example.administrator.utils.utils.StatusBarCompat;

/**
 * Created by Administrator on 2017/7/13 0013.
 */

public class BindService2Activity extends AppCompatActivity {

    private MyConn conn;
    private BindService2.MyBinder binder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.compat(this, getResources().getColor(R.color.colorPrimary));

        setContentView(R.layout.test_bound_service);

        ((Button) findViewById(R.id.btn)).setText("获取随机数的值");
    }

    //1、绑定服务
    public void bindService(View view) {

        Intent intent = new Intent(this, BindService2.class);
        conn = new MyConn();
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    //4、服务连接
    class MyConn implements ServiceConnection {

        @Override //接收来自onBind的返回值
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("onServiceConnected", "服务建立了连接");

            binder = (BindService2.MyBinder) service;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    //5、获取随机数的值
    public void unBindService(View view) {
        BindService2 bindService2 = binder.getInstance();
        int value = bindService2.getRandomValue();
        Toast.makeText(this, "随机数是" + value, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (conn != null) {
            unbindService(conn);
            conn = null;
        }
    }
}
