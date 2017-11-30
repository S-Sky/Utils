package com.example.administrator.utils.notification;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.administrator.utils.R;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/5/16 0016.
 */

public class NotificationService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate() {
        super.onCreate();
        Notification noti = new Notification.Builder(getApplicationContext())
                .setContentTitle("New mail from ")
                .setContentText("这是通知的标题")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
//                notificationIntent, 0);
        startForeground(1, noti);
        Log.i(TAG, "onCreate() executed");
    }
}
