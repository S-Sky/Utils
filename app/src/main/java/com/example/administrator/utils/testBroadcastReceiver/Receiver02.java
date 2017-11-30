package com.example.administrator.utils.testBroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class Receiver02 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "我这里是第二个广播接收器2222222222", Toast.LENGTH_SHORT).show();
    }
}
