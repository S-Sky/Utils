package com.example.administrator.utils.rotary_table.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.utils.rotary_table.Util;

/**
 * 自定义视图 绘制大转盘需要的视图
 * Created by Administrator on 2017/6/28 0028.
 */

public class LuckPanLayout extends View {

    private Context context;
    private Paint backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG); //背景
    private Paint whitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint yellowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int radius;
    private int CircleX, CircleY;
    private Canvas canvas; //画布
    private boolean isYellow = false;
    private int delayTime = 500;

    public LuckPanLayout(Context context) {
        this(context, null);
    }

    public LuckPanLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LuckPanLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        backgroundPaint.setColor(Color.rgb(255, 92, 93));
        whitePaint.setColor(Color.WHITE);
        yellowPaint.setColor(Color.YELLOW);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec); //获取宽的模式
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec); //获取宽的尺寸
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec); //获取高的模式
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec); //获取高的尺寸

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, 200);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, 200); //保存测量宽度和测量高度
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();

        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;

        int MinValue = Math.min(width, height);

        radius = MinValue / 2;
        CircleX = getWidth() / 2;
        CircleY = getHeight() / 2;

        canvas.drawCircle(CircleX, CircleY, radius, backgroundPaint);

        drawSmallCircle(isYellow);
    }

    private void drawSmallCircle(boolean FirstYellow) {
        int pointDistance = radius - Util.dip2px(context, 10);
        for (int i = 0; i <= 360; i += 20) {
            int x = (int) (pointDistance * Math.sin(Util.change(i))) + CircleX;
            int y = (int) (pointDistance * Math.cos(Util.change(i))) + CircleY;

            if (FirstYellow)
                canvas.drawCircle(x, y, Util.dip2px(context, 4), yellowPaint);
            else
                canvas.drawCircle(x, y, Util.dip2px(context, 4), whitePaint);
            FirstYellow = !FirstYellow;
        }
    }

    public void startLuckLight() { //暂时理解为转盘边上的跑马灯设置
        postDelayed(new Runnable() {
            @Override
            public void run() {
                isYellow = !isYellow;
                invalidate();
                postDelayed(this, delayTime);
            }
        }, delayTime);
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

}
