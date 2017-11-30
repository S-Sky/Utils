package com.example.administrator.utils.myRecycler;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/14 0014.
 */

public class MyOkhttp {

    public static OkHttpClient client = new OkHttpClient();

    public static String get(String url) {

        try {
            client.newBuilder().connectTimeout(1000, TimeUnit.MILLISECONDS);
            Request request = new Request.Builder().url(url).build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) { //如果连接成功返回返回值
                return response.body().string();
            } else {
                throw new IOException("Unexpected code" + response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
