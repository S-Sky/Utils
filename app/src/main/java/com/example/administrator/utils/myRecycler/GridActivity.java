package com.example.administrator.utils.myRecycler;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;

import com.example.administrator.utils.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class GridActivity extends AppCompatActivity {

    private static RecyclerView recyclerview;
    private CoordinatorLayout coordinatorLayout;
    private GridAdapter mAdapter;
    private List<Meizi> meizis;
    private GridLayoutManager mLayoutManager;
    private int lastVisibleItem;
    private int page = 1;
    private ItemTouchHelper itemTouchHelper;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
        setListener();

        new GetData().execute("http://gank.io/api/data/福利/10/1");
    }

    private void setListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                new GetData().execute("http://gank.io/api/data/福利/10/1");
            }
        });

        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags = 0;
                if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager ||
                        recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN
                            | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                }
                return makeMovementFlags(dragFlags, 0);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Meizi moveToItem = meizis.get(from);
                meizis.remove(from);
                meizis.add(to, moveToItem);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }

            @Override
            public boolean isLongPressDragEnabled() {
                return false;
            }
        });
        //recyclerview滚动监听
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //0:当前屏幕停止滚动;1:屏幕在滚动,且用户手指还在屏幕上;2:随着用户的操作,屏幕上产生的惯性滑动
                //滑动状态停止并且剩余个数小于两个item时,自动加载下一页
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 2 >= mLayoutManager.getItemCount()) {
                    new GetData().execute("http://gank.io/api/data/福利/10/" + (++page));
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //获取加载的最后一刻可见视图在适配器的位置
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });

    }

    private void initView() {
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.grid_coordinatorLayout);

        recyclerview = (RecyclerView) findViewById(R.id.grid_recycler);
        mLayoutManager = new GridLayoutManager(GridActivity.this, 3, GridLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(mLayoutManager);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.grid_swipe_refresh);
        //调整SwipeRefreshLayout的位置
        swipeRefreshLayout.setProgressViewOffset(false, 0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));

    }

    private class GetData extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //设置swipeRefreshLayout为刷新状态
            swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected String doInBackground(String... params) {
            return MyOkhttp.get(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)) {

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
                    recyclerview.setAdapter(mAdapter = new GridAdapter(GridActivity.this, meizis));

                    mAdapter.setOnItemClickListener(new GridAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(View view) {
                            int position = recyclerview.getChildAdapterPosition(view);
                            SnackbarUtil.ShortSnackbar(coordinatorLayout, "点击了第" + position + "个", SnackbarUtil.Info).show();
                        }

                        @Override
                        public void onItemLongClick(View view) {
                            itemTouchHelper.startDrag(recyclerview.getChildViewHolder(view));
                        }
                    });
                    itemTouchHelper.attachToRecyclerView(recyclerview);
                } else {
                    mAdapter.notifyDataSetChanged();
                }
            }
            //停止swipeRefreshLayout加载动画
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
