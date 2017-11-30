package com.example.administrator.utils.notification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.utils.R;
import com.example.administrator.utils.utils.StatusBarCompat;

/**
 * Created by Administrator on 2017/7/13 0013.
 */

public class TestTargetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.compat(this, getResources().getColor(R.color.colorPrimary));

        setContentView(R.layout.test_notification_target);

    }
}
