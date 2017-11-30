package com.example.administrator.utils.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;

import com.example.administrator.utils.R;
import com.example.administrator.utils.utils.StatusBarCompat;

/**
 * Created by Administrator on 2017/7/13 0013.
 */

public class TestNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.compat(this,getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.test_notification);
    }

    //发送通知
    public void sendNotification(View view) {
        //通知管理器
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        //设置一系列的内容
        builder.setContentTitle("通知");
        builder.setContentText("通知的内容。。。");
        builder.setSmallIcon(R.mipmap.timg);//设置小图标,必须设置
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)); //可以选择设置,参数是bitmap类型
        builder.setContentInfo("额外信息"); //设置额外信息
        builder.setAutoCancel(true); //设置通知是否可以自动被取消掉

        //点击通知时跳转到新的页面
        PendingIntent intent = PendingIntent.getActivity(this, 1, new Intent(this, TestTargetActivity.class), PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(intent);

        //发送通知(通知的ID,)
        manager.notify(1, builder.build());

    }
}
