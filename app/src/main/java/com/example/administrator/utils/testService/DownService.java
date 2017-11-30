package com.example.administrator.utils.testService;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 四大组件都属于UI线程 --> UI线程都不能直接进行耗时的操作
 * Created by Administrator on 2017/7/13 0013.
 */

public class DownService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //处理具体的业务逻辑
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //工作线程,进行耗时操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://imgsrc.baidu.com/imgad/pic/item/b64543a98226cffc8ae0e896b3014a90f703eac8.jpg");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(5000);
                    if (connection.getResponseCode() == 200) {
                        //下载结果
                        InputStream stream = connection.getInputStream();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        int len = 0;
                        byte[] buffer = new byte[1024];
                        while ((len = stream.read(buffer)) != -1) {
                            //将InoutStream里的内容都写到了输出流中
                            baos.write(buffer, 0, len);
                        }
                        //利用广播接收器把下载的结果传递给activity
                        //发送广播
                        final byte[] bytes = baos.toByteArray();
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent();
                                intent.setAction("downService"); //这句一定不能忘 设置广播意图
                                Log.i("TAG", "进来吗" + bytes.length);
                                intent.putExtra("img", bytes);
                                sendBroadcast(intent);
                                stopSelf(); //到服务做完了自己的任务就结束自身.这个方法在service自身中使用
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    private Handler mHandler = new Handler();
}
