package com.example.administrator.utils.scrollViewAndGridView;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.AdapterView;

import com.example.administrator.utils.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 顺便实现RadioGroup不能同时一行多个RadioButton,同时多行之间的互斥问题
 * Created by Administrator on 2017/5/25 0025.
 */

public class ScrollAndGridActivity extends Activity {

    private MyGridView my_gridview;
    GridViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview_gridview);

        my_gridview = (MyGridView) findViewById(R.id.my_gridview);
        adapter = new GridViewAdapter(this,setData());
        my_gridview.setAdapter(adapter);

        my_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelection(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private List<String> setData() {

        List<String> mList = new ArrayList<String>();
        for (int i = 'A'; i < 'Z'; i++) {
            mList.add("" + (char) i);
        }
        return mList;

    }
}
