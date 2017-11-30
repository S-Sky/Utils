package com.example.administrator.utils.scrollViewAndGridView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 重写GridView中的onMeasure()方法,给GridView一个足够大的高度，解决ScrollView与GridView嵌套使用冲突问题
 * Created by Administrator on 2017/5/25 0025.
 */

public class MyGridView extends GridView {
    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
