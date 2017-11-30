package com.example.administrator.utils.myDialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialog;

import com.example.administrator.utils.R;


/**
 * Created by loioi on 2017/3/16.
 */
public class LoadingDialog extends AppCompatDialog {

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.TransDialogStyle);
        this.setContentView(R.layout.dialog_loading); //Activity样式文件,一定要写在中间
    }

    public static LoadingDialog show(Context context) {
        return show(context, false, null);
    }

    public static LoadingDialog show(Context context, boolean cancel, @Nullable OnCancelListener listener) {
        LoadingDialog loading = new LoadingDialog(context);

        loading.setCancelable(cancel);
        loading.setCanceledOnTouchOutside(cancel);
        loading.setOnCancelListener(listener);

        loading.show();
        return loading;
    }

}
