package com.example.administrator.utils.testAnimation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.administrator.utils.R;

/**
 * Created by Administrator on 2017/7/10 0010.
 */

public class BuJianDongHuaActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bujiandonghua);

        imageView = (ImageView) findViewById(R.id.iv);

    }

    public void alpha(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0.5f);//透明度动画  设置起始透明度和结束透明度
        alphaAnimation.setDuration(2000); //动画的执行时间
        alphaAnimation.setRepeatCount(5); //设置动画的重复执行的次数
        alphaAnimation.setRepeatMode(Animation.RESTART); //重复的模式 Animation.RESTART 重新开始  Animation.REVERSE 反转
        alphaAnimation.setFillAfter(true); //保持结束时的状态
        imageView.startAnimation(alphaAnimation);

    }

    //平移动画
    public void translate(View view) {
        //fromXDelta: x轴上的终点
        //toXDelta: x轴上的终点
        //TranslateAnimation translateAnimation = new TranslateAnimation(0, 300.0f, 0, 200.0f); //平移动画
        //fromXType: 在x轴上进行变化的类型.
        //Animation.RELATIVE_TO_SELF+0.0f:
        //控制现在所在的坐标+0*控制本身的宽度.
        //Animation.RELATIVE_TO_SELF+0.0f:
        //控件现在所在的坐标+0.5f*控件本身的宽度.
        //Animation.RELATIVE_TO_PARENT: 相对于父控件.
        //Animation.RELATIVE_TO_PARENT+0.5f:
        //控件现在所在的坐标+0.5f*控件的父控件的宽度
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0F, Animation.RELATIVE_TO_SELF,
                0.5F, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
        translateAnimation.setDuration(2000); //动画的执行时间  必须设置
        translateAnimation.setRepeatCount(2);
        translateAnimation.setRepeatMode(Animation.REVERSE); //重复的模式
        imageView.startAnimation(translateAnimation);
    }

    //缩放
    public void scale(View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 2, 1, 2,
                Animation.RELATIVE_TO_SELF, 0.5F,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setRepeatCount(2);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        scaleAnimation.setFillAfter(true); //保存动画结束时的状态
        imageView.startAnimation(scaleAnimation);
    }

    //旋转
    public void rotate(View view) {
        // RotateAnimation rotateAnimation = new RotateAnimation(0, 45);
        RotateAnimation rotateAnimation = new RotateAnimation(
                0, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotateAnimation.setDuration(2000);
        rotateAnimation.setRepeatCount(2);
        imageView.startAnimation(rotateAnimation);
    }

    //组合
    public void setAnimation(View view) {
        AnimationSet sets = new AnimationSet(true);
        RotateAnimation rotateAnimation = new RotateAnimation(
                0, 3600, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        );
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 2, 1, 2,
                Animation.RELATIVE_TO_SELF, 0.5F,
                Animation.RELATIVE_TO_SELF, 0.5f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0.5f);//透明度动画  设置起始透明度和结束透明度
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF,
                0.5F, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sets.addAnimation(rotateAnimation);
        sets.addAnimation(scaleAnimation);
        sets.addAnimation(alphaAnimation);
        sets.addAnimation(translateAnimation);
        sets.setDuration(2000);
        sets.setRepeatCount(5);
        sets.setRepeatMode(Animation.REVERSE);
        sets.setFillAfter(true);
        imageView.startAnimation(sets);
    }
}
