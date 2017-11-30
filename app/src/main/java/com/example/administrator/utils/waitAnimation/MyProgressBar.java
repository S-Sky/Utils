package com.example.administrator.utils.waitAnimation;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Administrator on 2017/3/28 0028.
 */

public class MyProgressBar extends ProgressDialog {
    Context context;
    ProgressDialog progressDialog;

    public MyProgressBar(Context context) {
        super(context);
        this.context = context;
        showProgressDialog("提示", "正在加载......第六种效果");
    }

    /*
     *提示加载
     */
    public void showProgressDialog(String title, String message) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(context, title,
                    message, true, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle(title);
            progressDialog.setMessage(message);
        }
        progressDialog.show();
    }

    /*
     * 隐藏提示加载
     */
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
