package com.example.administrator.utils.notification;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.utils.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16 0016.
 */

public class NotificationActivity extends Activity implements View.OnClickListener {

    private Button btnStart;
    private Button btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initView();
    }

    private void initView() {
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                // 启动Service
                //将隐式启动转换为显示启动的方法来启动service
                Intent intentStart = new Intent();
                intentStart.setAction("ymw.MY_SERVICE");
                Intent mStart = new Intent(getExplicitIntent(this,intentStart));
                startService(mStart);
                break;
            case R.id.btnStop:
                // 关闭Service
                Intent intentStop = new Intent();
                intentStop.setAction("ymw.MY_SERVICE"); //定义的service的action
                intentStop.setPackage(getPackageName()); //这里需要设置应用的包名
                stopService(intentStop);
                break;
        }
    }

//    @Override
//    public void onBackPressed() {
//        System.exit(0);
//        super.onBackPressed();
//    }


    /**
     * 将隐式启动转换为显示启动：--参考地址：http://stackoverflow.com/a/26318757/1446466
     * @param context
     * @param implicitIntent
     * @return
     */

    public static Intent getExplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);
        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }
        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);
        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);
        // Set the component to be explicit
        explicitIntent.setComponent(component);
        return explicitIntent;
    }
}
