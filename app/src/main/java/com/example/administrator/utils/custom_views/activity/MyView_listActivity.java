package com.example.administrator.utils.custom_views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.utils.R;
import com.example.administrator.utils.utils.StatusBarCompat;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

@ContentView(R.layout.custom_list)
public class MyView_listActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.compat(this, this.getResources().getColor(R.color.colorPrimary));
        x.view().inject(this);
    }
    @Event(value = {R.id.btn1,R.id.btn})
    private void click(View view){
        switch (view.getId()){
            case R.id.btn: //自定义圆形进度条
                startActivity(new Intent(MyView_listActivity.this,MyActivity.class));
                break;
            case R.id.btn1: //自定义圆形图片
                startActivity(new Intent(MyView_listActivity.this,MyActivity1.class));
                break;

        }
    }
}
