package com.jc.school.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jc.school.R;
import com.jc.school.adapter.NewsAdapter;
import com.jc.school.bean.NewsInfo;
import com.jc.school.utils.GsonUtils;
import com.jc.school.utils.SpaceItemDecoration;
import com.jc.school.utils.WrapContentLinearLayoutManager;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;

/**
 * Created by Jaycee on 16/7/2.
 * E-mail：jayceeok@foxmail.com
 */

public class HomePageFragment extends Fragment {

    private static final String TAG = "HomePageFragment";

    public static final String[] NAMES = new String[]{"宋江", "卢俊义", "吴用", "公孙胜", "关胜", "林冲", "秦明", "呼延灼", "花荣", "柴进", "李应", "朱仝", "鲁智深", "武松", "董平", "张清", "杨志", "徐宁", "索超", "戴宗", "刘唐", "李逵", "史进", "穆弘", "雷横", "李俊", "阮小二", "张横", "阮小五", " 张顺", "阮小七", "杨雄", "石秀", "解珍", " 解宝", "燕青", "朱武", "黄信", "孙立", "宣赞", "郝思文", "韩滔", "彭玘", "单廷珪", "魏定国", "萧让", "裴宣", "欧鹏", "邓飞", " 燕顺", "杨林", "凌振", "蒋敬", "吕方", "郭 盛", "安道全", "皇甫端", "王英", "扈三娘", "鲍旭", "樊瑞", "孔明", "孔亮", "项充", "李衮", "金大坚", "马麟", "童威", "童猛", "孟康", "侯健", "陈达", "杨春", "郑天寿", "陶宗旺", "宋清", "乐和", "龚旺", "丁得孙", "穆春", "曹正", "宋万", "杜迁", "薛永", "施恩", "周通", "李忠", "杜兴", "汤隆", "邹渊", "邹润", "朱富", "朱贵", "蔡福", "蔡庆", "李立", "李云", "焦挺", "石勇", "孙新", "顾大嫂", "张青", "孙二娘", " 王定六", "郁保四", "白胜", "时迁", "段景柱"};

    public static final String[] otherDepartment = new String[]{"图书馆", "现代教育技术中心", "后勤服务中心", "学院领导", "学院办公室", "教务处", "学工处", "组织人事处", "科技处", "财务处", "后勤保卫处", "机械工程学院", "电子电气工程学院", "土木工程学院", "化工学院", "商学院", "计算机科学与技术", "外语系", "基础科学系"};

    public static final String[] position = new String[]{"主任", "副主任", "处长", "副处长", "处长助理", "经理", "核算员", "管理员", "讲师"};

    public static final String[] header = new String[]{"http://ww2.sinaimg.cn/mw690/9fcdce0dgw1f5d9ik1ex9j20jg0jg777.jpg", "http://ww4.sinaimg.cn/mw690/9fcdce0dgw1f5d9ik9vgcj20jg0jggnc.jpg", "http://ww3.sinaimg.cn/mw690/9fcdce0dgw1f5d9ikgz82j20jg0jh0v0.jpg", "http://ww2.sinaimg.cn/mw690/9fcdce0dgw1f5d9ilbyr2j20jg0jg408.jpg", "http://ww2.sinaimg.cn/mw690/9fcdce0dgw1f5d9im7z0kj20jg0jgwhv.jpg", "http://ww1.sinaimg.cn/mw690/9fcdce0dgw1f5d9in8tkuj20jg0jgwgr.jpg", "http://ww3.sinaimg.cn/mw690/9fcdce0dgw1f5d9io8bbyj20jg0jgq5p.jpg", "http://ww2.sinaimg.cn/mw690/9fcdce0dgw1f5d9ip5niij20jg0jg40g.jpg", "http://ww4.sinaimg.cn/mw690/9fcdce0dgw1f5d9ipzojtj20jg0jg762.jpg", "http://ww3.sinaimg.cn/mw690/9fcdce0dgw1f5d09wyh2sj20jg0jhwil.jpg", "http://weibo.com/u/2681064973?is_search=0&visible=0&is_all=1&is_tag=0&profile_ftype=1&page=3#_rnd1467476536179", "http://ww1.sinaimg.cn/mw690/9fcdce0dgw1f5d09xg7syj20jg0jgwgd.jpg", "http://ww2.sinaimg.cn/mw690/9fcdce0dgw1f5d09xmwhij20jg0jpgn6.jpg", "http://ww2.sinaimg.cn/mw690/9fcdce0dgw1f5d09xrdvkj20jg0jggoc.jpg", "http://ww2.sinaimg.cn/mw690/9fcdce0dgw1f5d09xzw1wj20jg0jg76v.jpg", "http://ww1.sinaimg.cn/mw690/9fcdce0dgw1f5d09y9hycj20jg0jgq5l.jpg", "http://ww2.sinaimg.cn/mw690/9fcdce0dgw1f5d09yfozvj20jg0jl416.jpg", "http://ww2.sinaimg.cn/mw690/9fcdce0dgw1f5d09yo4s4j20jg0jgta9.jpg", ""};
//    @BindView(R.id.rv_news)
    RecyclerView rvNews;
//    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;

    /**
     * 用于记录当前是何种状态，在请求完数据之后根据不同的状态进行不同的操作
     */
    private static final int STATE_INIT = 0;
    private static final int STATE_REFRESH = 1;
    private static final int STATE_LOAD_MORE = 2;
    /**
     * 用于记录当前的状态
     */
    private int curState = 0;
    /**
     * 用于记录总页数，在上拉的时候判断还有没有更多数据
     */
    private int totalPage = 1;
    /**
     * 用于记录当前是第几页
     */
    private int curPage = 1;
    /**
     * 用于记录一页应该请求多少条数据
     */
    private String pageSize = "10";
    private String startPos = "0";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main, container, false);
//        unbinder = ButterKnife.bind(this, view);
//        initRefreshLayout();
        rvNews= (RecyclerView) view.findViewById(R.id.rv_news);
        refreshLayout= (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
//        getData();

        return view;
    }

//    private void initRefreshLayout() {
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                //下拉刷新回调，更改当前状态为下拉刷新状态，把当前页置为第一页，
//                //向服务器请求数据
//                curState = STATE_REFRESH;
//                curPage = 1;
//                Logger.i(TAG, "curState---" + curState + "\ncurPage---" + curPage);
//
//                getData();
//
//
////                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
//            }
//        });
//        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(RefreshLayout refreshlayout) {
//                //上拉加载更多回调，更改当前状态为上拉加载更多状态，页数加1
//                //并且在判断还有更多的情况下向服务器请求数据
//                //否则提示用户没有更多数据，关闭上拉加载更多
//                curState = STATE_LOAD_MORE;
//                curPage = curPage + 1;
//                if (curPage <= totalPage) {
//                    Logger.i(TAG, "curState---" + curState + "\ncurPage---" + curPage);
//                    getData();
//                } else {
//                    Logger.i(TAG, "curState---" + curState + "\ncurPage---" + curPage);
//                    Toast.makeText(getActivity(), "没有更多啦O(∩_∩)O", Toast.LENGTH_SHORT).show();
//                    refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
//
//                }
//            }
//        });
//
//    }

    List<NewsInfo.ResultEntity.ListEntity> listEntityList = new ArrayList<>();

    private void getData() {
        RequestParams params = new RequestParams("http://api.jisuapi.com/news/get");
        params.addQueryStringParameter("channel", "头条");
        params.addQueryStringParameter("num", pageSize);
        params.addQueryStringParameter("start", startPos);
        params.addQueryStringParameter("appkey", "1d4a198e1a538541");
        // 添加到请求body体的参数, 只有POST, PUT, PATCH, DELETE请求支持.
        // params.addBodyParameter("wd", "xUtils");
        // 使用multipart表单上传文件
//        params.setMultipart(true);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Logger.i(TAG, result);
                NewsInfo newsInfo = GsonUtils.jsonToBean(result, NewsInfo.class);
                if ("0".equals(newsInfo.getStatus())) {
                    //每页的数量 默认10，最大40
//                    pageSize = newsInfo.getResult().getNum();
                    listEntityList = newsInfo.getResult().getList();
//                    showData();
                    rvNews.setLayoutManager(new WrapContentLinearLayoutManager(getActivity()));
                    mAdapter = new NewsAdapter(R.layout.item_news, listEntityList);
                    rvNews.addItemDecoration(new SpaceItemDecoration(30));
                    rvNews.setAdapter(mAdapter);

                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinished() {

            }
        });
    }

//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }

    private NewsAdapter mAdapter;

//    private void showData() {
//        switch (curState) {
//            case STATE_INIT:
//                rvNews.setLayoutManager(new WrapContentLinearLayoutManager(getActivity()));
////                rvNews.setItemAnimator(new SlideInLeftAnimator());
////                rvNews.getItemAnimator().setAddDuration(1000);
////                rvNews.getItemAnimator().setRemoveDuration(1000);
////                rvNews.getItemAnimator().setMoveDuration(1000);
////                rvNews.getItemAnimator().setChangeDuration(1000);
//                //初始化状态，初始化列表
//                mAdapter = new NewsAdapter(R.layout.item_news, listEntityList);
//                rvNews.addItemDecoration(new SpaceItemDecoration(30));
////                AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mAdapter);
////                alphaAdapter.setFirstOnly(false);
////                alphaAdapter.setDuration(1000);
////                alphaAdapter.setInterpolator(new OvershootInterpolator(.5f));
////                rvNews.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));
//
//                rvNews.setAdapter(mAdapter);
//                break;
//            case STATE_REFRESH:
//
//                //下拉刷新状态，刷新数据，列表回到最顶端，关闭下拉刷新
//                mAdapter.refreshData(listEntityList);
//                rvNews.scrollToPosition(0);
//                refreshLayout.finishRefresh();
//                break;
//            case STATE_LOAD_MORE:
//                //上拉加载更多状态，追加数据，关闭上拉加载更多
//                mAdapter.loadMoreData(listEntityList);
//                refreshLayout.finishLoadMore(2000);
//                break;
//            default:
//                break;
//        }
//    }

}
