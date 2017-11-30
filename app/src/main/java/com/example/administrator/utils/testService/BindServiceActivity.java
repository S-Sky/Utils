package com.example.administrator.utils.testService;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.utils.R;
import com.example.administrator.utils.utils.StatusBarCompat;

/**
 * 绑定的服务
 * Created by Administrator on 2017/7/13 0013.
 */

public class BindServiceActivity extends AppCompatActivity {

    //started service: 如果在activity中开启了service,那么这个activity和这个service之间没有关系
    //如果该activity退出程序,service仍然在后台继续运行;
    //bound service:如果在activity中绑定了service,那么这个activity和这个service之间是有关系的
    //此时如果该activity退出程序,service不会在后台继续运行

    private MyConn conn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.compat(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.test_bound_service);
    }

    //绑定服务
    public void bindService(View view) {

        Intent intent = new Intent(this, BindService.class);
        //第二个参数conn: 当服务被开启或者停止的时候用来接受一些信息
        conn = new MyConn();
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    //解绑服务
    public void unBindService(View view) {

        if (conn != null) {
            unbindService(conn);
            conn = null;
        }
    }

    //自定义的服务接口方法
    class MyConn implements ServiceConnection {

        //当服务建立连接的时候调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        //当服务失去连接的时候调用,当服务因为一些意外的原因:比如内存严重不足等原因导致了程序崩溃而造成的服务失去连接才会调用该方法
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

}
