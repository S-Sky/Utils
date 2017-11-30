package com.example.administrator.utils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.administrator.utils.listView.QQListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class MyListViewActivity extends Activity {

    private QQListView mListView;
    private ArrayAdapter<String> mAdapter;
    private List<String> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mListView = (QQListView) findViewById(R.id.id_listview);
        // 不要直接Arrays.asList
        mDatas = new ArrayList<String>(Arrays.asList("HelloWorld", "Welcome", "Java", "Android", "Servlet", "Struts",
                "Hibernate", "Spring", "HTML5", "Javascript", "Lucene"));
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);
        mListView.setAdapter(mAdapter);

        mListView.setDelButtonClickListener(new QQListView.DelButtonClickListener() {
            @Override
            public void clickHappend(int position) {
                Toast.makeText(MyListViewActivity.this, position + " : " + mAdapter.getItem(position), Toast.LENGTH_LONG).show();
                mAdapter.remove(mAdapter.getItem(position));
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(MyListViewActivity.this, position + " : " + mAdapter.getItem(position), Toast.LENGTH_LONG).show();
            }
        });
    }
}
