package com.example.administrator.utils.waitAnimation;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.example.administrator.utils.R;

/**
 * @Description: 奔跑小人的动画进度条对话框，可以用作加载数据界面
 * Created by Administrator on 2017/5/26 0026.
 */

public class RunningManActivity extends Activity {


    private MyProgressBar myProgressBar;
    private Dialog mDialog;
    private Dialog mWeiboDialog;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    DialogThridUtils.closeDialog(mDialog);
                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_man);
//        myProgressBar = new MyProgressBar(this); 此方法不易隐藏提示加载,使用时有必要注意

    }

    @Override
    protected void onResume() {
        super.onResume();
     //   myProgressBar.hideProgressDialog();
    }

    /**
     * 显示美团进度对话框
     *
     * @param v
     */
    public void showmeidialog(View v) {
        CustomProgressDialog dialog = new CustomProgressDialog(this, "正在加载中", R.drawable.frame);
        dialog.show();
    }

    /**
     * 显示顺丰快递员进度对话框
     *
     * @param v
     */
    public void showsfdialog(View v) {
        CustomProgressDialog dialog = new CustomProgressDialog(this, "正在加载中", R.drawable.frame2);
        dialog.show();
    }

    /**
     * 显示仿微博进度对话框
     *
     * @param view
     */
    public void btn_WB(View view) {
        mWeiboDialog = WeiboDialogUtils.createLoadingDialog(RunningManActivity.this, "加载中...");
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }

    /**
     * 显示第三种效果Loading
     *
     * @param view
     */
    public void btnLoading(View view) {
        mDialog = DialogThridUtils.showWaitDialog(RunningManActivity.this, "加载中...", false, true);
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }

    /**
     * 第五种dialog
     *
     * @param view
     */
    public void btn_five(View view) {
        Intent intent = new Intent(RunningManActivity.this, FiveDialogActivity.class);
        startActivity(intent);
    }

    /**
     * 第六种dialog
     *
     * @param view
     */
    public void btn_six(View view) {
//        Intent intent = new Intent(RunningManActivity.this, MyTabLayout.class);
//        startActivity(intent);
    }

}
