package com.example.administrator.utils.rotary_table;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.utils.R;
import com.example.administrator.utils.rotary_table.custom_view.LuckPanLayout;
import com.example.administrator.utils.rotary_table.custom_view.RotatePan;

import java.util.Random;

/**
 * Created by Administrator on 2017/6/26 0026.
 */

public class RotaryTableActivity extends Activity implements RotatePan.AnimationEndListener {
    private RotatePan rotatePan;
    private LuckPanLayout luckPanLayout;
    private ImageView goBtn;

    private String[] strs = {"一等奖", "二等奖", "三等奖", "四等奖", "五等奖", "六等奖"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rotary_table_activity);

        luckPanLayout = (LuckPanLayout) findViewById(R.id.luckpan_layout); //大转盘
        luckPanLayout.startLuckLight();
        rotatePan = (RotatePan) findViewById(R.id.rotatePan); //
        rotatePan.setAnimationEndListener(this);
        goBtn = (ImageView) findViewById(R.id.go);
        //yunIv = (ImageView)findViewById(R.id.yun);

        luckPanLayout.post(new Runnable() {
            @Override
            public void run() {
                int height = getWindow().getDecorView().getHeight();
                int width = getWindow().getDecorView().getWidth();

                int backHeight = 0;

                int MinValue = Math.min(width, height);
                MinValue -= Util.dip2px(RotaryTableActivity.this, 10) * 2;
                backHeight = MinValue / 2;

                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) luckPanLayout.getLayoutParams();
                lp.width = MinValue;
                lp.height = MinValue;

                luckPanLayout.setLayoutParams(lp);

                MinValue -= Util.dip2px(RotaryTableActivity.this, 28) * 2;
                lp = (RelativeLayout.LayoutParams) rotatePan.getLayoutParams();
                lp.height = MinValue;
                lp.width = MinValue;

                rotatePan.setLayoutParams(lp);


                lp = (RelativeLayout.LayoutParams) goBtn.getLayoutParams();
                lp.topMargin += backHeight;
                lp.topMargin -= (goBtn.getHeight() / 2);
                goBtn.setLayoutParams(lp);

                getWindow().getDecorView().requestLayout();
            }
        });

    }

    public void rotation(View view) {

        Random r = new Random();
        int sj = r.nextInt(100);
        System.out.println("==sj" + sj);
        int lucknum = 5;
        if (sj > 98) {//一等奖
            lucknum = 6;
        } else if (sj > 88) {//二等奖
            lucknum = 1;
        } else if (sj > 78) {//三等奖
            lucknum = 2;
        } else if (sj > 68) {//四等
            lucknum = 3;
        } else if (sj > 58) {//五等奖
            lucknum = 4;
        } else {
            lucknum = 5; //六等奖
        }

        /**
         *  private String[] strs = {"一等奖", "二等奖", "三等奖", "四等奖", "五等奖", "六等奖"};
         */
        System.out.println("==" + lucknum);
        rotatePan.startRotate(lucknum);
        luckPanLayout.setDelayTime(100);
        goBtn.setEnabled(false);
    }

    @Override
    public void endAnimation(int position) {
        goBtn.setEnabled(true);
        luckPanLayout.setDelayTime(500);
        Toast.makeText(this, "Position = " + position + "," + strs[position], Toast.LENGTH_SHORT).show();
    }
}
