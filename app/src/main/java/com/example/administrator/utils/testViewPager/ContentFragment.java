package com.example.administrator.utils.testViewPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.utils.R;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class ContentFragment extends Fragment {


    //创建fragment对象的方法
    public static ContentFragment newInstance(String msg) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("msg", msg);
        contentFragment.setArguments(bundle); //fragment间传值
        return contentFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_layout, null);
        TextView tvMsg = (TextView) view.findViewById(R.id.tv_msg);

        Bundle bundle = getArguments();
        if (bundle != null) {
            tvMsg.setText(bundle.getString("msg"));
        }
        return view;
    }
}
