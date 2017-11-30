package com.example.administrator.utils.myRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.utils.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private Context mContxt;
    private List<Meizi> datas;

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view);

        void onItemLongClick(View view);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public GridAdapter(Context context, List<Meizi> datas) {
        this.mContxt = context;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 0) {
            View view = LayoutInflater.from(mContxt).inflate(R.layout.grid_meizi_item, parent, false);
            MyViewHolder holder = new MyViewHolder(view);

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);

            return holder;
        } else {
            MyViewHolder1 holder1 = new MyViewHolder1(
                    LayoutInflater.from(mContxt).inflate(R.layout.page_item, parent, false));
            return holder1;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            Picasso.with(mContxt).load(datas.get(position).getUrl()).into(((MyViewHolder) holder).iv);
        } else if (holder instanceof MyViewHolder1) {
            ((MyViewHolder1) holder).tv.setText(datas.get(position).getPage() + "页");
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        //判断item是图还是显示页数(图片有URL)
        if (!TextUtils.isEmpty(datas.get(position).getUrl())) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v);
        }
    }

    @Override
    public boolean onLongClick(View v) {

        if (mOnItemClickListener != null){
            mOnItemClickListener.onItemLongClick(v);
        }
        return false;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageButton iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageButton) itemView.findViewById(R.id.iv);
        }
    }

    class MyViewHolder1 extends RecyclerView.ViewHolder {
        private TextView tv;

        public MyViewHolder1(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

}
