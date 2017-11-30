package com.example.administrator.utils.testHandle;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.administrator.utils.R;
import com.example.administrator.utils.utils.StatusBarCompat;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class TestHandle extends AppCompatActivity {

    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.compat(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.test_handle);
        //Activity 属于主线程,同时它内部,封装了一个Looper
        //Looper 轮询器 ---> "lopper线程"

        new LopperThread().start();
    }

    public void sendMsg(View view) {
        Message message = new Message();
        message.obj = "啦啦啦啦啦";
        mHandler.sendMessage(message);
    }
    //自定义Lopper线程,--> 不停的处理主线程发过来的消息

    class LopperThread extends Thread {

        @Override
        public void run() {
            //1、准备成为looper线程
            Looper.prepare();

            //2、处理消息
            mHandler = new Handler() {
                //处理消息
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if (msg.obj != null) {
                        String value = (String) msg.obj;
                        Log.i("TAG", "从主线程接受的消息=" + value);
                    }
                }
            };

            //3、循环的方法
            Looper.loop();
        }
    }
}
