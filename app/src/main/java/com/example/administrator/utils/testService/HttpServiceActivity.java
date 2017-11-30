package com.example.administrator.utils.testService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.utils.R;
import com.example.administrator.utils.utils.StatusBarCompat;

/**
 * 动态注册
 * Started Service
 * Created by Administrator on 2017/7/13 0013.
 */

public class HttpServiceActivity extends AppCompatActivity {

    private ImageView imageView;
    private Myreceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.compat(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.test_service_http);

        imageView = (ImageView) findViewById(R.id.iv);

        //注册
        receiver = new Myreceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("downService");
        registerReceiver(receiver, filter);

    }

    //开启一个服务进行图片下载
    public void downImg(View view) {
        Intent intent = new Intent(this, DownService.class);
        startService(intent);
    }

    //在广播接收器中进行图片的展示
    class Myreceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("进来没", "进来了。。");
            byte[] bytes = intent.getByteArrayExtra("img");
            Log.i("TAG", "==》" + bytes.length);
            //根据字节数组转换成图片
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            //取消广播接收器
            unregisterReceiver(receiver);
        }
    }
}
