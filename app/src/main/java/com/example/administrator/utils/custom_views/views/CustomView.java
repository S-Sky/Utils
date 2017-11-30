package com.example.administrator.utils.custom_views.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/7/5 0005.
 */

public class CustomView extends View {

    private Paint mPaint; //画笔
    private Paint mLoadPaint; //进度条画笔
    private Paint mTextPaint; //显示进度的画笔
    //圆环宽度
    private float width = 50;
    //圆的中心点
    private float mX = 200 + width / 2;
    private float mY = 200 + width / 2;

    //半径
    private float mRadius = 200;
    //矩形
    private RectF rectF;

    private int min = 0; //最小
    private int max = 100; //最大

    //宽高
    private int Width;
    private int Height;

    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        initRectf();

        //当前角度
        float angle = (float) min / max * 360;

        /**
         * float cx,
         * float cy 两个中心点
         * float radius 半径
         * Paint paint 画笔
         *
         * 设置居中 mX -> Width/2
         *          mY -> Height/2
         */
        canvas.drawCircle(Width / 2, Height / 2, mRadius, mPaint); //画圆
        /**
         *RectF oval  矩形
         * float startAngle 开始起点位置
         * float sweepAngle 开始的方向
         * boolean useCenter 是否中间有一条线连接
         * Paint paint 画笔
         */
        canvas.drawArc(rectF, -90, angle, false, mLoadPaint);
        /**
         * String text 显示的内容
         * float x, float y  坐标
         * Paint paint 画笔
         */
        canvas.drawText(min + "%", Width / 2, Height / 2, mTextPaint);

        if (min < max) {
            min++;
            invalidate();//强制调用onDraw()方法
        } else {
            min = 0;
            invalidate();//强制调用onDraw()方法
        }
    }

    private void init() {

        //第一个画笔
        mPaint = new Paint();
        mPaint.setColor(Color.RED); //设置画笔颜色
        mPaint.setAntiAlias(true); //消除锯齿
        mPaint.setStyle(Paint.Style.STROKE); //设置样式 圆
        mPaint.setStrokeWidth(width);//设置圆环的宽度

        mLoadPaint = new Paint();
        mLoadPaint.setColor(Color.WHITE);
        mLoadPaint.setAntiAlias(true);
        mLoadPaint.setStyle(Paint.Style.STROKE);
        mLoadPaint.setStrokeWidth(width);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.RED);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextAlign(Paint.Align.CENTER); //文字居中
        mTextPaint.setTextSize(50);

        // rectF = new RectF(width / 2, width / 2, mRadius * 2 + width / 2, mRadius * 2 + width / 2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Width = getSize(widthMeasureSpec);
        Height = getSize(heightMeasureSpec);

        setMeasuredDimension(Width, Height);

    }

    /**
     * 获取宽高的时候 xml中宽高必须设置为match_parent
     *
     * @param spec
     * @return
     */
    private int getSize(int spec) {

        int result = -1;
        int mode = MeasureSpec.getMode(spec);
        int size = MeasureSpec.getSize(spec);

        if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.UNSPECIFIED) { //自己测量宽高
            result = (int) (mRadius * 2 + width);
        } else {
            //默认大小
            return size;
        }
        return result;
    }

    //居中
    private void initRectf() {
        if (rectF == null) {
            rectF = new RectF();
            int viewSize = (int) (mRadius * 2);

            int left = (Width - viewSize) / 2;
            int top = (Height - viewSize) / 2;
            int right = left + viewSize;
            int bottom = top + viewSize;

            rectF.set(left, top, right, bottom);
        }
    }

}
