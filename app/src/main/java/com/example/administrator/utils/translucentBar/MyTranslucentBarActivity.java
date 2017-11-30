package com.example.administrator.utils.translucentBar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.utils.R;

/**
 * Created by Administrator on 2017/6/12 0012.
 */

public class MyTranslucentBarActivity  extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_translucent_bar);
    }

    public void onColor(View view){ //颜色
        startActivity(new Intent(this,ColorTranslucentBarActivity.class));
    }

    public void onImage(View view){
        startActivity(new Intent(this,ImageTranslucentBarActivity.class));
    }

}
