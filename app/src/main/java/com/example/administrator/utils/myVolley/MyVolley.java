package com.example.administrator.utils.myVolley;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.utils.R;

/**
 * Created by Administrator on 2017/5/31 0031.
 */

public class MyVolley extends Activity {

    RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_string);

        mQueue = Volley.newRequestQueue(this);
        findViewById(R.id.btn_http).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestData();
            }
        });
    }

    /**
     * RequestQueue是一个请求队列对象,它可以缓存所有的HTTP请求,然后按照一定的算法并发地发出这些请求。
     * RequestQueue内部的设计就是非常合适高并发的，因此我们不必为每一次HTTP请求都创建一个RequestQueue对象，
     * 这是非常浪费资源的，基本上在每一个需要和网络交互的Activity中创建一个RequestQueue对象就足够了。
     */
    public void requestData() {
        /**
         * StringRequest
         */
        StringRequest stringRequest = new StringRequest("https://www.baidu.com",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        mQueue.add(stringRequest);
    }
}
