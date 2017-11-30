package com.example.administrator.utils.jpush;

import android.app.Application;
import android.util.Log;

import org.xutils.x;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public class MyApplication extends Application {

   /* Set<String> label = new HashSet<>(); //添加标签
    String s1 = "label1";
    String s2 = "label2";
*/

    @Override
    public void onCreate() {
        super.onCreate();

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        //初始化xutils
        x.Ext.init(this);
        // 设置是否输出debug
        x.Ext.setDebug(true);

        /* label.add(s1);
        label.add(s2);*/
        /* JPushInterface.setAliasAndTags(getApplicationContext(), "王青", label, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                Log.i("JPush","JPush status:" + i); //状态  为0时表示成功
            }
        });*/

        JPushInterface.setAlias(getApplicationContext(), "", new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                Log.i("JPush", "JPush status:" + i); //状态  为0时表示成功
            }
        });

    }


}