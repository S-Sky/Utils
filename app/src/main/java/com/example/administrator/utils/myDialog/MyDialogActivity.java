package com.example.administrator.utils.myDialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.utils.R;

/**
 * Created by Administrator on 2017/6/7 0007.
 */

public class MyDialogActivity extends Activity {

    private Button btn_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_activity);
        btn_1 = (Button) findViewById(R.id.btn_1);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadingDialog(MyDialogActivity.this).show();
            }
        });

    }
}
