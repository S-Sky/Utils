package com.example.administrator.utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.utils.custom_views.activity.MyView_listActivity;
import com.example.administrator.utils.demo_music.MActivity;
import com.example.administrator.utils.myDialog.MyDialogActivity;
import com.example.administrator.utils.myRecycler.MyRecycler;
import com.example.administrator.utils.mycar.MyCarActivity;
import com.example.administrator.utils.notification.TestNotificationActivity;
import com.example.administrator.utils.pingandand.ClientSDKActivity;
import com.example.administrator.utils.rotary_table.RotaryTableActivity;
import com.example.administrator.utils.scrollViewAndGridView.ScrollAndGridActivity;
import com.example.administrator.utils.socket_test.TcpActivity;
import com.example.administrator.utils.testAnimation.TestActivity;
import com.example.administrator.utils.testBroadcastReceiver.MyReceiverActivity;
import com.example.administrator.utils.testHandle.TestHandle;
import com.example.administrator.utils.testService.BindService2Activity;
import com.example.administrator.utils.testViewPager.MyViewPager;
import com.example.administrator.utils.translucentBar.MyTranslucentBarActivity;
import com.example.administrator.utils.utils.StatusBarCompat;
import com.example.administrator.utils.waitAnimation.RunningManActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView time_change;
    private TextView tv_camera;
    private TextView tv_list;
    private TextView tv_notification;
    private TextView tv_share;
    private TextView tv_ping;
    private TextView tv_buycar;
    private TextView tv_ScrollView_GridView;
    private TextView tv_animation;
    private TextView tv_dialog;
    private TextView tv_translucent;
    private TextView tv_recycler;
    private TextView tv_rotary_table;
    private TextView tv_qq_table;
    private TextView tv_demo;
    private TextView tv_donghua;
    private TextView tv_view_pager;
    private TextView tv_handler;
    private TextView tv_board_cast;
    private TextView tv_service;
    private TextView tv_socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarCompat.compat(this, this.getResources().getColor(R.color.colorPrimary));
        initView();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.time_change: //时间选择器
                Intent timeIntent = new Intent(MainActivity.this, TimeChangeActivity.class);
                startActivity(timeIntent);
                break;
            case R.id.tv_camera: //相机相册
                Intent cameraIntent = new Intent(MainActivity.this, CapturingPhotosActivity.class);
                startActivity(cameraIntent);
                break;
            case R.id.tv_list: //列表及其刷新
                Intent intent = new Intent(MainActivity.this, MyListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_notification:  //消息推送
                //  Intent notification = new Intent(MainActivity.this, NotificationActivity.class);
                Intent notification = new Intent(MainActivity.this, TestNotificationActivity.class);
                startActivity(notification);
                break;
            case R.id.tv_share:
                shareMsg("测试", "你好", null);
                break;
            case R.id.tv_ping: //ping++第三方支付
                Intent pingIntent = new Intent(MainActivity.this, ClientSDKActivity.class);
                startActivity(pingIntent);
                break;
            case R.id.tv_buycar: //ping++第三方支付购物车
                Intent tv_buycar = new Intent(MainActivity.this, MyCarActivity.class);
                startActivity(tv_buycar);
                break;
            case R.id.tv_ScrollView_GridView: //两个控件冲突问题
                Intent scroll_grid = new Intent(MainActivity.this, ScrollAndGridActivity.class);
                startActivity(scroll_grid);
                break;
            case R.id.tv_animation: //等待加载动画
                Intent runningMan = new Intent();
                runningMan.setClass(this, RunningManActivity.class);
                startActivity(runningMan);
                break;
            case R.id.tv_dialog: //等待加载
                startActivity(new Intent(MainActivity.this, MyDialogActivity.class));
                break;
            case R.id.tv_translucent: //沉浸式
                startActivity(new Intent(MainActivity.this, MyTranslucentBarActivity.class));
                break;
            case R.id.tv_recycler: //recyclerview的用法
                startActivity(new Intent(MainActivity.this, MyRecycler.class));
                break;
            case R.id.tv_rotary_table: //大转盘
                startActivity(new Intent(MainActivity.this, RotaryTableActivity.class));
                break;
            case R.id.tv_qq_table: //自定义视图
                startActivity(new Intent(MainActivity.this, MyView_listActivity.class));
                break;
            case R.id.tv_demo: //仿网易音乐播放页面转盘
                startActivity(new Intent(MainActivity.this, MActivity.class));
                break;
            case R.id.tv_donghua: //动画
                startActivity(new Intent(MainActivity.this, TestActivity.class));
                break;
            case R.id.tv_view_pager: //viewPager
                startActivity(new Intent(MainActivity.this, MyViewPager.class));
                break;
            case R.id.tv_handler: //handler
                startActivity(new Intent(MainActivity.this, TestHandle.class));
                break;
            /**
             * BroadcastReceiver
             * 创建:  继承BroadcastReceiver基类
             * 注册:
             *      1、静态注册    通过xml文件注册
             *      2、动态注册    通过Java代码注册
             *  广播的分类:
             *      1、普通广播
             *          异步执行:广播接收器接受广播没有先后顺序,效率较高,无法被拦截
             *      2、有序广播
             *          同步执行:同一时刻只会有一个广播接收器能够接受到消息,根据优先级有先后顺序,可以被拦截
             *  启动广播的方式:
             *      sendBroadcast
             *      sendOrderedBroadcast
             */
            case R.id.tv_board_cast: //广播 BroadcastReceiver
                startActivity(new Intent(MainActivity.this, MyReceiverActivity.class));
                break;
            case R.id.tv_service: // service
                startActivity(new Intent(MainActivity.this, BindService2Activity.class));
                break;
            case R.id.tv_socket: // socket
                startActivity(new Intent(MainActivity.this, TcpActivity.class));
                break;
        }
    }

    public void initView() {
        time_change = (TextView) findViewById(R.id.time_change);
        tv_camera = (TextView) findViewById(R.id.tv_camera);
        tv_list = (TextView) findViewById(R.id.tv_list);
        tv_notification = (TextView) findViewById(R.id.tv_notification);
        tv_share = (TextView) findViewById(R.id.tv_share);
        tv_ping = (TextView) findViewById(R.id.tv_ping);
        tv_buycar = (TextView) findViewById(R.id.tv_buycar);
        tv_ScrollView_GridView = (TextView) findViewById(R.id.tv_ScrollView_GridView);
        tv_animation = (TextView) findViewById(R.id.tv_animation);
        tv_dialog = (TextView) findViewById(R.id.tv_dialog);
        tv_translucent = (TextView) findViewById(R.id.tv_translucent);
        tv_recycler = (TextView) findViewById(R.id.tv_recycler);
        tv_rotary_table = (TextView) findViewById(R.id.tv_rotary_table);
        tv_qq_table = (TextView) findViewById(R.id.tv_qq_table);
        tv_demo = (TextView) findViewById(R.id.tv_demo);
        tv_donghua = (TextView) findViewById(R.id.tv_donghua);
        tv_view_pager = (TextView) findViewById(R.id.tv_view_pager);
        tv_handler = (TextView) findViewById(R.id.tv_handler);
        tv_board_cast = (TextView) findViewById(R.id.tv_board_cast);
        tv_service = (TextView) findViewById(R.id.tv_service);
        tv_socket = (TextView) findViewById(R.id.tv_socket);

        time_change.setOnClickListener(this);
        tv_camera.setOnClickListener(this);
        tv_list.setOnClickListener(this);
        tv_notification.setOnClickListener(this);
        tv_share.setOnClickListener(this);
        tv_ping.setOnClickListener(this);
        tv_buycar.setOnClickListener(this);
        tv_ScrollView_GridView.setOnClickListener(this);
        tv_animation.setOnClickListener(this);
        tv_dialog.setOnClickListener(this);
        tv_translucent.setOnClickListener(this);
        tv_recycler.setOnClickListener(this);
        tv_rotary_table.setOnClickListener(this);
        tv_qq_table.setOnClickListener(this);
        tv_demo.setOnClickListener(this);
        tv_donghua.setOnClickListener(this);
        tv_view_pager.setOnClickListener(this);
        tv_handler.setOnClickListener(this);
        tv_board_cast.setOnClickListener(this);
        tv_service.setOnClickListener(this);
        tv_socket.setOnClickListener(this);
    }

    public native String getStrFromJNI();


    /**
     * 分享功能,启动系统的分享功能
     *
     * @param msgTitle 消息标题
     * @param msgText  消息内容
     * @param imgPath  图片路径，不分享图片则传null
     */
    public void shareMsg(String msgTitle, String msgText,
                         String imgPath) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (imgPath == null || imgPath.equals("")) {
            intent.setType("text/plain"); // 纯文本
        } else {
            //imgPath = Environment.getExternalStorageDirectory() + File.separator + "test.jpg";
            File f = new File(imgPath);
            if (f != null && f.exists() && f.isFile()) {
                intent.setType("image/jpg");
                //由文件得到uri
                Uri u = Uri.fromFile(f);
                intent.putExtra(Intent.EXTRA_STREAM, u);
            }
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, "分享到"));
    }
}


/**
 * 状态栏透明,只显示时间、电量、数据等信息
 * getWindow().requestFeature(Window.FEATURE_NO_TITLE);
 * if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
 * Window window = getWindow();
 * window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
 * | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
 * window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
 * | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
 * | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
 * window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
 * window.setStatusBarColor(Color.TRANSPARENT);
 * window.setNavigationBarColor(Color.TRANSPARENT);
 * }
 */