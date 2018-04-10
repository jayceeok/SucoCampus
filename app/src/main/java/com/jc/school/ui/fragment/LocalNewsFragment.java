package com.jc.school.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jc.pulltorefresh.library.PullToRefreshListView;
import com.jc.school.R;
import com.jc.school.bean.LocalNews;
import com.jc.school.bean.TestModel;
import com.jc.school.config.Constants;
import com.jc.school.utils.CommonAdapter;
import com.jc.school.utils.GsonUtils;
import com.jc.school.utils.ScreenUtil;
import com.jc.school.utils.ViewHolder;
import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jaycee on 16/7/2.
 * E-mail：jayceeok@foxmail.com
 */
public class LocalNewsFragment extends Fragment {

    private static final String TAG = "LocalNewsFragment";

    private ListView mListView;

    private int count = 15;

    public List<LocalNews.ListEntity> mLocalNewsArrayList = new ArrayList<LocalNews.ListEntity>();

    public List<TestModel.RowsEntity> mTestModelArrayList = new ArrayList<TestModel.RowsEntity>();

    private String url = Constants.API + "GetNewsListByChannelId&appVersion=3.4&numPerPage=30&adNum=50&orderType=3&channelId=21&requiredPage=1";
    private String urlTest = "http://139.196.240.94:8080/hanjue/AppProduct/findProduct.do?page=1&rows=" + count;
    private PullToRefreshListView mPullRefreshListView;
    private LinkedList<String> mListItems;

    private ArrayAdapter<String> mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    private int lastVisibleItemPosition = 0;// 标记上次滑动位置
    private boolean scrollFlag = false;// 标记是否滑动

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_local_news, container, false);
        mListView = (ListView) view.findViewById(R.id.lv_local_news);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        initData(count);
        initSwipeRefreshLayout();
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:// 是当屏幕停止滚动时
                        scrollFlag = false;
                        // 判断滚动到底部
                        if (mListView.getLastVisiblePosition() == (mListView
                                .getCount() - 1)) {

                        }
                        // 判断滚动到顶部
                        if (mListView.getFirstVisiblePosition() == 0) {
                        }
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:// 滚动时
                        scrollFlag = true;
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:// 是当用户由于之前划动屏幕并抬起手指，屏幕产生惯性滑动时
                        scrollFlag = false;
                        break;
                }
            }

            /**
             * firstVisibleItem：当前能看见的第一个列表项ID（从0开始）
             * visibleItemCount：当前能看见的列表项个数（小半个也算） totalItemCount：列表项共数
             */
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                //可以放一些动画滑动效果
                if (scrollFlag && ScreenUtil.getScreenViewBottomHeight(mListView) >= ScreenUtil.getScreenHeight(
                        getActivity())) {
                    if (firstVisibleItem > lastVisibleItemPosition) {// 上滑
                    } else if (firstVisibleItem < lastVisibleItemPosition) {// 下滑
                    } else {
                        return;
                    }
                    lastVisibleItemPosition = firstVisibleItem;
                }
            }

        });
        //        mPullRefreshListView = (PullToRefreshListView) view.findViewById(R.id.lv_local_news);
        //Mode设置为Mode.BOTH后，下拉和上拉都会执行onRefresh()中的方法了
        /*
            * 设置PullToRefresh刷新模式
            * BOTH:上拉刷新和下拉刷新都支持
            * DISABLED：禁用上拉下拉刷新
            * PULL_FROM_START:仅支持下拉刷新（默认）
            * PULL_FROM_END：仅支持上拉刷新
            * MANUAL_REFRESH_ONLY：只允许手动触发
            * */
        //        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        //        //因为界面上边，我们要显示“下拉刷新”，下边我们要显示“上拉加载”，所以后三行就是改变下边部分的文字，getLoadingLayoutProxy(false, true)方法大家可以自己感受一下
        //        mPullRefreshListView.getLoadingLayoutProxy(false, true).setPullLabel("下拉加载...");
        //        mPullRefreshListView.getLoadingLayoutProxy(false, true).setRefreshingLabel("载入中...");
        //        mPullRefreshListView.getLoadingLayoutProxy(false, true).setReleaseLabel("上拉加载...");
        //        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
        //            @Override
        //            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
        //                String label = DateUtils.formatDateTime(getActivity(),
        //                                                        System.currentTimeMillis(),
        //                                                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL
        //                );
        //                // 下拉刷新 业务代码
        //                if (refreshView.isShownHeader()) {
        //                    mPullRefreshListView.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
        //                    mPullRefreshListView.getLoadingLayoutProxy().setPullLabel("下拉刷新");
        //                    mPullRefreshListView.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
        //                    refreshView.getLoadingLayoutProxy().setLastUpdatedLabel("最后更新时间:" + label);
        ////                    mListItems.addFirst("Added after refresh...");
        //
        //
        //                    new GetDataTask().execute();
        //                }
        //                // 上拉加载更多 业务代码
        //                if (refreshView.isShownFooter()) {
        //                    mPullRefreshListView.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
        //                    mPullRefreshListView.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
        //                    mPullRefreshListView.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
        //                    refreshView.getLoadingLayoutProxy().setLastUpdatedLabel("最后加载时间:" + label);
        //
        //
        //                    // Do work to refresh the list here.
        //                    new GetDataTask().execute();
        //                }
        //
        //
        //                // Update the LastUpdatedLabel
        //                //                        refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
        //
        //
        //            }
        //        });
        //        // Add an end-of-list listener
        //        mPullRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
        //            @Override
        //            public void onLastItemVisible() {
        //                Toast.makeText(getActivity(), "到底了!", Toast.LENGTH_SHORT).show();
        //            }
        //        });
        //        ListView actualListView = mPullRefreshListView.getRefreshableView();
        //        // Need to use the Actual ListView when registering for Context Menu
        //        registerForContextMenu(actualListView);
        //
        //        mListItems = new LinkedList();
        //        mListItems.addAll(Arrays.asList(mStrings));
        //
        //        mAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, mListItems);
        //
        //        /**
        //         * Add Sound Event Listener
        //         */
        //        com.jc.pulltorefresh.extras.SoundPullEventListener<ListView> soundListener = new SoundPullEventListener(
        //                getActivity());
        //        soundListener.addSoundEvent(PullToRefreshBase.State.PULL_TO_REFRESH, R.raw.pull_event);
        //        soundListener.addSoundEvent(PullToRefreshBase.State.RESET, R.raw.reset_sound);
        //        soundListener.addSoundEvent(PullToRefreshBase.State.REFRESHING, R.raw.refreshing_sound);
        //        mPullRefreshListView.setOnPullEventListener(soundListener);
        // You can also just use setListAdapter(mAdapter) or mPullRefreshListView.setAdapter(mAdapter)
        //         actualListView.setAdapter(mAdapter);
        return view;
    }

    private void initData(int count) {

        //        RequestParams requestParams = new RequestParams(url);
        //        x.http().get(requestParams, new Callback.CacheCallback<String>() {
        //            @Override
        //            public void onSuccess(String result) {
        //                Logger.i(TAG, result);
        //                LocalNews locaNews = GsonUtils.jsonToBean(result, LocalNews.class);
        //
        //                for (int i = 0; i < locaNews.getList().size(); i++) {
        //                    LocalNews.ListEntity one = new LocalNews.ListEntity(locaNews.getList()
        //                                                                                .get(i)
        //                                                                                .getTitle(),
        //                                                                        locaNews.getList()
        //                                                                                .get(i)
        //                                                                                .getContent(),
        //                                                                        locaNews.getList()
        //                                                                                .get(i)
        //                                                                                .getDate(),
        //                                                                        locaNews.getList()
        //                                                                                .get(i)
        //                                                                                .getImage(),
        //                                                                        locaNews.getList()
        //                                                                                .get(i)
        //                                                                                .getNewsId()
        //                    );
        //                    mLocalNewsArrayList.add(one);
        //
        //                }
        //                initView();
        //
        //
        //            }
        //
        //            @Override
        //            public void onError(Throwable ex, boolean isOnCallback) {
        //
        //            }
        //
        //            @Override
        //            public void onCancelled(CancelledException cex) {
        //
        //            }
        //
        //            @Override
        //            public void onFinished() {
        //
        //            }
        //
        //            @Override
        //            public boolean onCache(String result) {
        //                return false;
        //            }
        //        });

        RequestParams requestParams = new RequestParams(urlTest);
        x.http().get(requestParams, new Callback.CacheCallback<String>() {


            @Override
            public void onSuccess(String result) {

                TestModel testModel = GsonUtils.jsonToBean(result, TestModel.class);
                for (int i = 0; i < testModel.getRows().size(); i++) {
                    TestModel.RowsEntity one = new TestModel.RowsEntity(testModel.getRows()
                                                                                 .get(i)
                                                                                 .getProductname(),
                                                                        testModel.getRows()
                                                                                 .get(i)
                                                                                 .getProductid()
                    );
                    mTestModelArrayList.add(one);
                }
                initView();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

    private void initView() {
        //        mPullRefreshListView.setAdapter(new CommonAdapter<LocalNews.ListEntity>(getActivity(),
        //                                                                                mLocalNewsArrayList,
        //                                                                                R.layout.item_local_news
        //        ) {
        //
        //            @Override
        //            public void convert(ViewHolder holder, LocalNews.ListEntity listEntity) {
        //                holder.setText(R.id.tv_local_news_title, listEntity.getTitle())
        //                      .setText(R.id.tv_local_news_content, listEntity.getContent())
        //                      .setText(R.id.tv_post_date, listEntity.getDate())
        //                      .setImageURL(getActivity(),
        //                                   R.id.iv_local_news_image,
        //                                   listEntity.getImage().getSrc()
        //                      );
        //            }
        //        });


        mListView.setAdapter(new CommonAdapter<TestModel.RowsEntity>(getActivity(),
                                                                     mTestModelArrayList,
                                                                     R.layout.item_test
        ) {
            @Override
            public void convert(ViewHolder holder, TestModel.RowsEntity rowsEntity) {
                holder.setText(R.id.tv_name, rowsEntity.getProductname());
            }
        });


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private String[] mStrings = {"Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler"};

    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return mStrings;
        }

        @Override
        protected void onPostExecute(String[] result) {
            //            mListItems.addFirst("Added after refresh...");
            Logger.i(TAG, result.toString());
            mAdapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();
            super.onPostExecute(result);
        }
    }

    private void initSwipeRefreshLayout() {


        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_light,
                                                    android.R.color.holo_purple,
                                                    android.R.color.holo_blue_dark,
                                                    android.R.color.holo_green_light,
                                                    android.R.color.holo_red_dark
        );
        mSwipeRefreshLayout.setDistanceToTriggerSync(100);
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.ac_bg_side_nav_actions));
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (mListView != null) {
                            mTestModelArrayList.clear();
                        }
                        initData(count);
                        //                        mListView.smoothScrollToPosition(0);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }
}
