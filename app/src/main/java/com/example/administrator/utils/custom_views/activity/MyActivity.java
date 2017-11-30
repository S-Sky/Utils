package com.example.administrator.utils.custom_views.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.utils.R;

/**
 * 自定义圆形进度条
 * Created by Administrator on 2017/7/4 0004.
 */

public class MyActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myview);
    }
}
