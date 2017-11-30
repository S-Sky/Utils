package com.example.administrator.utils.testViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;

import com.example.administrator.utils.R;
import com.example.administrator.utils.utils.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class MyViewPager extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerTabStrip mTabStrip; //导航控件(系统自带的)

    private List<Fragment> mList;
    private String[] array;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusBarCompat.compat(this, getResources().getColor(R.color.colorPrimary));

        setContentView(R.layout.test_view_pager);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabStrip = (PagerTabStrip) findViewById(R.id.tab_navigation);

        mTabStrip.setTextColor(Color.RED); //改变字体颜色
        mTabStrip.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20); //改变字体大小
        mTabStrip.setTabIndicatorColor(Color.YELLOW); //设置指示线的颜色

        array = getResources().getStringArray(R.array.tabs); //获取字符串数组

        //list的泛型必须是v4包中的Fragment对象
        mList = new ArrayList<Fragment>();
        for (int i = 0; i < 5; i++) {
            ContentFragment fragment = ContentFragment.newInstance("第" + i + "个Fragment");
            mList.add(fragment);
        }

        //必须得到一个v4包中的FragmentManager
        FragmentManager manager = getSupportFragmentManager();
        MyAdapter myAdapter = new MyAdapter(manager);

        viewPager.setAdapter(myAdapter);

    }

    //结合fragment的适配器
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        //获取每个界面对应的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return array[position];
        }

        //返回一个fragment对象
        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList != null ? mList.size() : 0;
        }
    }
}
