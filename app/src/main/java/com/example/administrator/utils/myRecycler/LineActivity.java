package com.example.administrator.utils.myRecycler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.utils.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/6/14 0014.
 */

public class LineActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CoordinatorLayout coordinatorLayout; //协调布局
    private MyAdapter mAdapter;
    private List<Meizi> meizis;
    private LinearLayoutManager mLayoutManager; //线行布局管理器
    private int lastVisibleItem;
    private int page = 1;
    private ItemTouchHelper itemTouchHelper;
    private int screenwidth;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
        setListener();

        new GetData().execute("http://gank.io/api/data/福利/10/1");

        //获取屏幕的宽度
        WindowManager wm = (WindowManager) LineActivity.this
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        screenwidth = outMetrics.widthPixels;


    }

    private void initView() {
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.line_coordinatorLayout);

        recyclerView = (RecyclerView) findViewById(R.id.line_recycler);
        mLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(mLayoutManager); //添加布局管理器

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.line_swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent); //设置替换显示的颜色
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));//设置偏移位置

    }

    //设置监听
    private void setListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                new GetData().execute("http://gank.io/api/data/福利/10/1");
            }
        });

        //触摸事件
        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags = 0, swipeFlags = 0;
                // instanceof 判断左边的对象是否是右边的类的实例
                if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    //设置侧滑方向为从左到右和从右到左都可以
                    swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                }
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(meizis, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mAdapter.removeItem(viewHolder.getAdapterPosition());
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(Color.WHITE);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                viewHolder.itemView.setAlpha(1 - Math.abs(dX / screenwidth));
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //0:当前屏幕停止滚动;1时:屏幕在滚动,且用户仍在触碰或者手指还在屏幕上;2时:随着用户的操作,屏幕上产生的惯性滑动
                //滑动状态停止并且剩余两个item时自动加载
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 2 >= mLayoutManager.getItemCount()) {
                    new GetData().execute("http://gank.io/api/data/福利/10/" + (++page));
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //获取加载的最后一个可见适配在适配器的位置
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });

    }

    //异步加载
    private class GetData extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected String doInBackground(String... params) {
            return MyOkhttp.get(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)) { //返回结果不为空时
                JSONObject jsonObject;
                Gson gson = new Gson();
                String jsonData = null;

                try {
                    jsonObject = new JSONObject(s);
                    jsonData = jsonObject.getString("results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (meizis == null || meizis.size() == 0) {
                    meizis = gson.fromJson(jsonData, new TypeToken<List<Meizi>>() {
                    }.getType());
                    Meizi pages = new Meizi();
                    pages.setPage(page);
                    meizis.add(pages);
                } else {
                    List<Meizi> more = gson.fromJson(jsonData, new TypeToken<List<Meizi>>() {
                    }.getType());
                    meizis.addAll(more);
                    Meizi pages = new Meizi();
                    pages.setPage(page);
                    meizis.add(pages);
                }

                if (mAdapter == null) {
                    recyclerView.setAdapter(mAdapter = new MyAdapter());
                    itemTouchHelper.attachToRecyclerView(recyclerView);
                } else {
                    mAdapter.notifyDataSetChanged();
                }
            }
            swipeRefreshLayout.setRefreshing(false);
        }
    }


    //适配器
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements View.OnClickListener {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(LineActivity.this)
                    .inflate(R.layout.line_meizi_item, parent, false);

            MyViewHolder holder = new MyViewHolder(view);

            view.setOnClickListener(this);

            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText("图" + position);
            Picasso.with(LineActivity.this).load(meizis.get(position).getUrl()).into(holder.iv);
        }

        @Override
        public int getItemCount() {
            return meizis.size();
        }

        @Override
        public void onClick(View v) {
            int position = recyclerView.getChildAdapterPosition(v); //获取点击的item的下标
            SnackbarUtil.ShortSnackbar(coordinatorLayout, "点击了第" + position + "个", SnackbarUtil.Info).show();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            private ImageView iv;
            private TextView tv;

            public MyViewHolder(View itemView) {
                super(itemView);
                iv = (ImageView) itemView.findViewById(R.id.line_item_iv);
                tv = (TextView) itemView.findViewById(R.id.line_item_tv);
            }
        }

        public void addItem(Meizi meizi, int position) {
            meizis.add(position, meizi);
            notifyItemInserted(position);
            recyclerView.scrollToPosition(position);
        }

        public void removeItem(final int position) {
            final Meizi removed = meizis.get(position);
            meizis.remove(position);
            notifyItemRemoved(position); //适配器移除第position个数据
            SnackbarUtil.ShortSnackbar(coordinatorLayout, "你删除了滴" + position + "个item", SnackbarUtil.Warning)
                    .setAction("撤销", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            addItem(removed, position);
                            SnackbarUtil.ShortSnackbar(coordinatorLayout, "撤销了删除第" + position + "个item", SnackbarUtil.Confirm).show();
                        }
                    }).setActionTextColor(Color.WHITE).show();
        }
    }
}
