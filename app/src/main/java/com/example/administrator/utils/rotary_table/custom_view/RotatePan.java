package com.example.administrator.utils.rotary_table.custom_view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.administrator.utils.rotary_table.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * 绘制大转盘
 * Created by Administrator on 2017/6/28 0028.
 */

public class RotatePan extends View {

    private Context context;

    private Paint dPaint = new Paint(Paint.ANTI_ALIAS_FLAG); //绘制双数扇形
    private Paint sPaint = new Paint(Paint.ANTI_ALIAS_FLAG); //绘制单数扇形
    private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG); //绘制字体
    private int InitAngle = 30; //初步理解为指针的初始位置的角度
    private int radius = 0; //半径

    public static final int FLING_VELOCITY_DOWNSCALE = 4;

    //  private int[] images = new int[]{R.mipmap.huawei, R.mipmap.image_one, R.mipmap.iphone, R.mipmap.macbook, R.mipmap.meizu, R.mipmap.xiaomi};
    private String[] images = new String[]{"30 天", "25 天", "15 天", "10 天", "7 天", "3 天"};
    private String[] strs = {"一等奖", "二等奖", "三等奖", "四等奖", "五等奖", "六等奖"};
    private List<Bitmap> bitmaps = new ArrayList<>();
    private GestureDetectorCompat mDetector;
    private ScrollerCompat scroller;

    public RotatePan(Context context) {
        this(context, null);
    }

    public RotatePan(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotatePan(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        mDetector = new GestureDetectorCompat(context, new RotatePanGestureListener());
        scroller = ScrollerCompat.create(context);

        dPaint.setColor(Color.rgb(255, 133, 132));
        sPaint.setColor(Color.rgb(254, 104, 105));
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(Util.dip2px(context, 16));
        setClickable(true);

       /* for (int i = 0; i < 6; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), images[i]);
            bitmaps.add(bitmap);
        }*/
    }

    /**
     * 测量控件的大小
     * 当前view放入父控件中的时候会调用这个方法,此时父控件会询问当前view需要多大的尺寸,并且分配相应的空间给当前自定义的view
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int mHeight = Util.dip2px(context, 300);
        int mWidth = Util.dip2px(context, 300);

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, mHeight);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, mHeight);
        }
    }

    //绘制大转盘
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();

        int width = getWidth() - paddingLeft - paddingRight;  //转盘view的宽度
        int height = getHeight() - paddingTop - paddingBottom; //转盘view的高度

        int MinValue = Math.min(width, height); // 返回较小的值

        radius = MinValue / 2; //返回的较小的值得一半就是大转盘的半径

        RectF rectF = new RectF(getPaddingLeft(), getPaddingTop(), width, height); //创建一个指定坐标的矩形

        int angle = InitAngle - 30;

        //第一个就是画弧的地方第一个角度是起始角度，第二个是弧的角度，并不是结束的角度，所以是固定值60。
        // 第二个地方就是计算具体的x,y的值的时候要根据弧度去计算，不能根据角度。
        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0) {
                canvas.drawArc(rectF, angle, 60, true, dPaint);
            } else {
                canvas.drawArc(rectF, angle, 60, true, sPaint);
            }
            angle += 60;
        }

        for (int i = 0; i < 6; i++) { //设置图片  改为字
            //  drawIcon(width / 2, height / 2, radius, InitAngle, i, canvas);
            drawText1(InitAngle + 30, images[i], 2 * radius, textPaint, canvas, rectF);
            InitAngle += 60;
        }

        for (int i = 0; i < 6; i++) { //设置显示的文字
            drawText(InitAngle + 30, strs[i], 2 * radius, textPaint, canvas, rectF);
            InitAngle += 60;
        }
    }

    private void drawText(float startAngle, String string, int mRadius, Paint mTextPaint, Canvas mCanvas, RectF mRange) {
        Path path = new Path();
        path.addArc(mRange, startAngle, 60);
        float textWidth = mTextPaint.measureText(string);

        float hOffset = (float) (mRadius * Math.PI / 6 / 2 - textWidth / 2);
        float vOffset = mRadius / 2 / 4;
        mCanvas.drawTextOnPath(string, path, hOffset, vOffset, mTextPaint);
    }

    //改字
    private void drawText1(float startAngle, String string, int mRadius, Paint mTextPaint, Canvas mCanvas, RectF mRange) {
        Path path = new Path();
        path.addArc(mRange, startAngle, 60);
        float textWidth = mTextPaint.measureText(string);

        float hOffset = (float) (mRadius * Math.PI / 6 / 2 - textWidth / 2);
        float vOffset = mRadius / 4;
        mCanvas.drawTextOnPath(string, path, hOffset, vOffset, mTextPaint);
    }


//    private void drawIcon(int xx, int yy, int mRadius, float startAngle, int i, Canvas mCanvas) {
//
//        int imgWidth = mRadius / 4;
//
//        float angle = (float) Math.toRadians(60 + startAngle);
//
//        float x = (float) (xx + mRadius / 2 * Math.cos(angle));
//        float y = (float) (yy + mRadius / 2 * Math.sin(angle));
//
//        // 确定绘制图片的位置
//        RectF rect = new RectF(x - imgWidth * 3 / 4, y - imgWidth * 3 / 4, x + imgWidth
//                * 3 / 4, y + imgWidth * 3 / 4);
//
//        Bitmap bitmap = bitmaps.get(i);
//
//        mCanvas.drawBitmap(bitmap, null, rect, null);
//        mCanvas.drawText();
//    }


    public void setImages(List<Bitmap> bitmaps) {
        this.bitmaps = bitmaps;
        this.invalidate();
    }

    public void setStr(String... strs) {
        this.strs = strs;
        this.invalidate();
    }

    //旋转一圈所需要的时间
    private static final long ONE_WHEEL_TIME = 500;

    /**
     * 开始转动
     *
     * @param pos 如果 pos = -1 则随机，如果指定某个值，则转到某个指定区域
     */
    public void startRotate(int pos) {

        int lap = (int) (Math.random() * 12) + 4; //初步理解为旋转的圈数
        System.out.println("lap==" + lap);
        int angle = 0; //最后一圈随机停止的位置的度数
        if (pos < 0) {
            angle = (int) (Math.random() * 360);
            System.out.println("pos是什么玩意");
        } else {
            int initPos = queryPosition();
            System.out.println("initPos" + initPos);
            if (pos > initPos) {
                angle = (pos - initPos) * 60;
                lap -= 1;
                angle = 360 - angle;
                System.out.println("pos > initPos");
            } else if (pos < initPos) {
                angle = (initPos - pos) * 60;
                System.out.println("pos < initPos");
            } else {
                //nothing to do.
            }
        }


        int increaseDegree = lap * 360 + angle;
        long time = (lap + angle / 360) * ONE_WHEEL_TIME;
        int DesRotate = increaseDegree + InitAngle; //指针起始位置到最后停止旋转的度数,因为刚开始指针在第一格的中间位置,也就是InitAngle度的位置
        System.out.println("time==" + time);
        //TODO 为了每次都能旋转到转盘的中间位置
        int offRotate = DesRotate % 360 % 60;
        DesRotate -= offRotate;
        DesRotate += 30; //指针旋转的角度(从起始位置开始就算,旋转DesRotate度后停止)
        //使用属性动画让其自动旋转
        System.out.println("InitAngle==" + InitAngle);
        ValueAnimator animtor = ValueAnimator.ofInt(InitAngle, DesRotate); //设置开始旋转的初始位置和停止位置
        animtor.setInterpolator(new AccelerateDecelerateInterpolator());
        animtor.setDuration(time); //设置旋转的时间
        animtor.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int updateValue = (int) animation.getAnimatedValue();
                InitAngle = (updateValue % 360 + 360) % 360;
                ViewCompat.postInvalidateOnAnimation(RotatePan.this);
            }
        });

        animtor.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

//                int pos = InitAngle / 60;
//                if(pos >= 0 && pos <= 3){
//                    pos = 3 - pos;
//                }else{
//                    pos = (6-pos) + 3;
//                }

                if (l != null)
                    l.endAnimation(queryPosition());
            }
        });
        animtor.start();
    }


    private int queryPosition() {
        InitAngle = (InitAngle % 360 + 360) % 360;
        int pos = InitAngle / 60;
        return calcumAngle(pos);
    }

    private int calcumAngle(int pos) {
        if (pos >= 0 && pos <= 3) {
            pos = 3 - pos;
        } else {
            pos = (6 - pos) + 3;
        }
        return pos;
    }


    public interface AnimationEndListener {
        void endAnimation(int position);
    }

    private AnimationEndListener l;

    public void setAnimationEndListener(AnimationEndListener l) {
        this.l = l;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clearAnimation();
    }

    // TODO ==================================== 手势处理 ===============================================================

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        boolean consume = mDetector.onTouchEvent(event);
        if (consume) {
            getParent().requestDisallowInterceptTouchEvent(true);
            return true;
        }

        return super.onTouchEvent(event);
    }


    public void setRotate(int rotation) {
        rotation = (rotation % 360 + 360) % 360;
        InitAngle = rotation;
        ViewCompat.postInvalidateOnAnimation(this);
    }


    @Override
    public void computeScroll() {

        if (scroller.computeScrollOffset()) {
            setRotate(scroller.getCurrY());
        }

        super.computeScroll();
    }

    private class RotatePanGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return super.onDown(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            float centerX = (RotatePan.this.getLeft() + RotatePan.this.getRight()) * 0.5f;
            float centerY = (RotatePan.this.getTop() + RotatePan.this.getBottom()) * 0.5f;

            float scrollTheta = vectorToScalarScroll(distanceX, distanceY, e2.getX() - centerX, e2.getY() -
                    centerY);
            int rotate = InitAngle -
                    (int) scrollTheta / FLING_VELOCITY_DOWNSCALE;

            setRotate(rotate);
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float centerX = (RotatePan.this.getLeft() + RotatePan.this.getRight()) * 0.5f;
            float centerY = (RotatePan.this.getTop() + RotatePan.this.getBottom()) * 0.5f;

            float scrollTheta = vectorToScalarScroll(velocityX, velocityY, e2.getX() - centerX, e2.getY() -
                    centerY);

            scroller.abortAnimation();
            scroller.fling(0, InitAngle, 0, (int) scrollTheta / FLING_VELOCITY_DOWNSCALE,
                    0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
            return true;
        }
    }

    //TODO 判断滑动的方向
    private float vectorToScalarScroll(float dx, float dy, float x, float y) {

        float l = (float) Math.sqrt(dx * dx + dy * dy);

        float crossX = -y;
        float crossY = x;

        float dot = (crossX * dx + crossY * dy);
        float sign = Math.signum(dot);

        return l * sign;
    }

}
