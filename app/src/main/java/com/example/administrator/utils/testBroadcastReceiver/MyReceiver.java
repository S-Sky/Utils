package com.example.administrator.utils.testBroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class MyReceiver extends BroadcastReceiver {

    //BroadcastReceiver: 四大组件之一
    //BroadcastReceiver: 也属于UI线程,onReceive()方法中不能进行耗时操作
    //否则可能会导致ANR异常
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra("key"), Toast.LENGTH_SHORT).show();
     //   Toast.makeText(context, "接收到了广播", Toast.LENGTH_SHORT).show();
    }
}
