package com.example.administrator.utils.testAnimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.administrator.utils.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 属性动画  移动的是imageView的位置
 * <p>
 * Created by Administrator on 2017/7/10 0010.
 */

public class ShuXingActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shuxingdonghau);

        imageView = (ImageView) findViewById(R.id.iv);
    }

    //透明度变化
    public void alpha(View view) {
        //target:  属性动画作用子啊谁身上
        //propertyName: 属性名称
        //value: 属性的变化范围值
        ObjectAnimator anim = ObjectAnimator.ofFloat(imageView, "alpha", 0.0f, 0.8f);
        anim.setDuration(2000);
        anim.setRepeatCount(2);
//        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.start();
    }

    //平移
    public void translate(View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "translationX", 0, 200f);
        objectAnimator.setDuration(2000);
        objectAnimator.setRepeatCount(2);
//        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();
    }

    //缩放
    public void scale(View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "scaleX", 0, 1);
        objectAnimator.setDuration(2000);
        objectAnimator.start();
    }

    //旋转  旋转之后空白  不知道原因
    public void rotate(View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "rotationX", 0, 90);
        objectAnimator.setDuration(2000);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.start();
    }

    //组合
    public void setAnimation(View view) {

        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(imageView, "scaleX", 0, 1);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(imageView, "translationX", 0, 200f);
        ObjectAnimator anim = ObjectAnimator.ofFloat(imageView, "alpha", 0.0f, 0.8f);
        List<Animator> list = new ArrayList<Animator>();
        list.add(anim);
        list.add(anim1);
        list.add(anim2);
        animatorSet.playSequentially(list); //播放一系列的动画,播放完一个之后播放下一个
        animatorSet.setDuration(2000);
        animatorSet.start();

    }
}
