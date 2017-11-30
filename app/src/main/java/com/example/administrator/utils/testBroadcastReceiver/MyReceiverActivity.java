package com.example.administrator.utils.testBroadcastReceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.utils.R;
import com.example.administrator.utils.utils.StatusBarCompat;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class MyReceiverActivity extends AppCompatActivity {

    private MyReceiver myReceiver;

    //静态注册广播接收器: 1、在清单文件中注册 2、app退出之后,仍然可以接收到广播消息
    //动态注册广播接收器: 2、在代码中进行注册 2、在app退出之后,不能继续接收广播消息

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.compat(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.test_broad_cast);

        //动态注册广播接收器
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("receiver");
        registerReceiver(myReceiver, filter);
    }

    //发送广播
    public void send(View view) {
        Intent intent = new Intent();
        intent.setAction("receiver");
        intent.putExtra("key", "传递数据给广播接收器。。。");
        //发送普通广播 --> MyReceiver
        sendBroadcast(intent);
        //发送有序广播  第二个参数是可选的,一般填null
        //sendOrderedBroadcast(intent, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myReceiver != null) {
            //取消注册广播接收器的方法
            unregisterReceiver(myReceiver);
        }
    }
}
