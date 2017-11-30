package com.example.administrator.utils.testService;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.utils.R;
import com.example.administrator.utils.utils.StatusBarCompat;

/**
 * 静态注册
 * Started Service
 * Created by Administrator on 2017/7/13 0013.
 */

public class ServiceActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.compat(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.test_service);

    }

    //开启服务
    public void start(View view) {
        //开启服务 --> startActivity(intent)
        intent = new Intent(this, FirstService.class);
        startService(intent);
    }

    //关闭服务
    public void stop(View view) {
        stopService(intent);
    }
}
