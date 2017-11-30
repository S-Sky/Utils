package com.example.administrator.utils.testAnimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.utils.R;
import com.example.administrator.utils.utils.StatusBarCompat;

/**
 * Created by Administrator on 2017/7/10 0010.
 * 动画的分类
 * 1、帧动画
 * 2、补间动画:透明、旋转、缩放、平移
 * 3、属性动画
 * 动画的用法
 */

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.compat(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_test_animation);

        findViewById(R.id.tv_zhen).setOnClickListener(this);
        findViewById(R.id.tv_bujian).setOnClickListener(this);
        findViewById(R.id.tv_shuxing).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_zhen: //帧动画
                startActivity(new Intent(this, ZhenDongHuaActivity.class));
                break;
            case R.id.tv_bujian: //补间动画
                startActivity(new Intent(this, BuJianDongHuaActivity.class));
                break;
            case R.id.tv_shuxing: //属性动画
                startActivity(new Intent(this, ShuXingActivity.class));
                break;
        }
    }
}
