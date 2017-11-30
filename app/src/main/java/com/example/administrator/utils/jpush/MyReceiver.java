package com.example.administrator.utils.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.utils.MyListViewActivity;

import cn.jpush.android.api.JPushInterface;

import static android.content.ContentValues.TAG;

/**
 * 用来接受通知栏信息的接收器
 * Created by Administrator on 2017/5/18 0018.
 */

public class MyReceiver extends BroadcastReceiver {

    /*
     *  if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
     *  Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
     *
     *  打开自定义的Activity(我自己理解的意思是:点击通知栏消息,跳转到目标页面)
     *  Intent i = new Intent(context, MainActivity.class);
     *  i.putExtras(bundle);
     *  i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     *  i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
     *  context.startActivity(i);
     *  }
     *
     *  <!--如果要跳转到该Activity时，并清除掉之前的Activity，那么就可以将跳转到的Activity启动模式为-->
     *  <!--主页面-->
     *      <activity android:name=".activity.MainActivity"
     *               android:launchMode="singleTask"/>
     */

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "onReceive - " + intent.getAction());
        Log.i(TAG, "查看通知栏信息" + bundle);
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "收到了自定义消息。消息内容是：" + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "收到了通知");
            // 在这里可以做些统计，或者做些其他工作
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "用户点击打开了通知");
            // 在这里可以自己写代码去定义用户点击后的行为
            Intent i = new Intent(context, MyListViewActivity.class);  //自定义打开的界面
            i.putExtras(bundle);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }
}
