package com.example.administrator.utils.testService;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 与service相比,不用开线程,直接使用即可
 * Created by Administrator on 2017/7/13 0013.
 */

public class IntentDownService extends IntentService {

    public IntentDownService() {
        super("");
    }

    //处理意图的方法
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //不用开线程,直接开始写
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
                Intent intent1 = new Intent();
                intent1.setAction("downService"); //这句一定不能忘 设置广播意图
                Log.i("TAG", "进来吗" + baos.toByteArray().length);
                intent1.putExtra("img", baos.toByteArray());
                sendBroadcast(intent1);
                stopSelf(); //到服务做完了自己的任务就结束自身.这个方法在service自身中使用
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
