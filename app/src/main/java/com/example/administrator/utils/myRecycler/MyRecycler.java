package com.example.administrator.utils.myRecycler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.utils.R;

/**
 * Created by Administrator on 2017/6/14 0014.
 */

public class MyRecycler extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_demo);

        findViewById(R.id.btn_line).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyRecycler.this, LineActivity.class));
            }
        });
        findViewById(R.id.btn_line2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyRecycler.this, LineActivity2.class));
            }
        });
        findViewById(R.id.grid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyRecycler.this, GridActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.staggered).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyRecycler.this, StaggeredActivity.class);
                startActivity(intent);
            }
        });
    }
}
