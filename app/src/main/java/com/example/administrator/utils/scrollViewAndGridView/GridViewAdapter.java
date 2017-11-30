package com.example.administrator.utils.scrollViewAndGridView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.utils.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;
    private LayoutInflater inflater;

    public GridViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    private int clickTemp = -1;

    //标示选择的item
    public void setSelection(int position) {
        clickTemp = position;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.uset_time_grid_view_item, null);
            holder = new Holder();
            holder.tv_item = (TextView) convertView.findViewById(R.id.tv_item);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        String str = list.get(position);
        holder.tv_item.setText(str);

        if (clickTemp == position) {
            holder.tv_item.setBackgroundResource(R.drawable.user_time_radio_button_checked);
            holder.tv_item.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            holder.tv_item.setBackgroundResource(R.drawable.user_time_radio_button);
            holder.tv_item.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }
        return convertView;
    }

    class Holder {
        TextView tv_item;
    }
}
