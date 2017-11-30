package com.example.administrator.utils.waitAnimation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.utils.R;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public class FiveDialogActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    private int count = 5;
    private int[] imgIDs = {R.id.img01,
            R.id.img02,
            R.id.img03,
            R.id.img04,
            R.id.img05};
    // 选中
    private final int EDIT_TYPE_SELECTED = 1;
    // 未选中
    private final int EDIT_TYPE_NO_SELECTED = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.five_dialog);
        final IndexThread thread = new IndexThread();
        for (int id : imgIDs)
            ((ImageView) findViewById(id)).setBackgroundResource(R.drawable.progress_bg_small);
        thread.start();
        Button btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                thread.flag = false;

            }
        });
    }

    public Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case EDIT_TYPE_SELECTED:
                    ((ImageView) findViewById(msg.arg1)).setBackgroundResource(R.drawable.progress_go_small);
                    break;
                case EDIT_TYPE_NO_SELECTED:
                    ((ImageView) findViewById(msg.arg1)).setBackgroundResource(R.drawable.progress_bg_small);
                    break;
            }
        }
    };

    class IndexThread extends Thread {
        boolean flag = true;

        @Override
        public void run() {
            Message msg;
            while (flag) {
                for (int i = 0; i < count; i++) {
                    msg = new Message();
                    msg.what = EDIT_TYPE_SELECTED;
                    msg.arg1 = imgIDs[i];
                    myHandler.sendMessage(msg);
                    msg = new Message();
                    if (i == 0) {
                        msg.what = EDIT_TYPE_NO_SELECTED;
                        msg.arg1 = imgIDs[count - 1];
                        myHandler.sendMessage(msg);
                    } else {
                        msg.what = EDIT_TYPE_NO_SELECTED;
                        msg.arg1 = imgIDs[i - 1];
                        myHandler.sendMessage(msg);
                    }
                    SystemClock.sleep(500);
                }
            }
        }
    }
}
