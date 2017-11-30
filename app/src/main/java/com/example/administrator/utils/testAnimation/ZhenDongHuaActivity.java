package com.example.administrator.utils.testAnimation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.administrator.utils.R;
import com.example.administrator.utils.utils.StatusBarCompat;

/**
 * Created by Administrator on 2017/7/10 0010.
 */

public class ZhenDongHuaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.compat(this, getResources().getColor(R.color.colorPrimary));

        setContentView(R.layout.zhendonghuan);

        ImageView iv = (ImageView) findViewById(R.id.image);
        //AnimationDrawable: 用来创建帧动画的对象
        AnimationDrawable ad = (AnimationDrawable) iv.getBackground();
        //开始帧动画
        ad.start();
        //ad.stop(); //结束帧动画

    }
}
