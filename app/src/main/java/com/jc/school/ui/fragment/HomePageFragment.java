package com.jc.school.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jc.school.R;
import com.jc.school.base.App;
import com.jc.school.bean.BannerModel;
import com.jc.school.bean.NewsInfo;
import com.jc.school.interf.Engine;
import com.jc.school.ui.widget.MyImageView;
import com.jc.school.utils.CommonAdapter;
import com.jc.school.utils.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jaycee on 16/7/2.
 * E-mail：jayceeok@foxmail.com
 */

public class HomePageFragment extends Fragment {
    private static final String TAG = "HomePageFragment";
    //    @ViewInject(R.id.et_name)
    //    EditText mEtName;
    //    @ViewInject(R.id.et_feedback)
    //    EditText mEtFeedback;
    //    String name, feedback;

    MyImageView joke, news, meeting, recommend;
    private BGABanner mCubeBanner;
    private List<ImageView> mCubeViews;
    private Engine mEngine;


    public static final String[] NAMES = new String[] {"宋江", "卢俊义", "吴用", "公孙胜", "关胜", "林冲", "秦明", "呼延灼", "花荣", "柴进", "李应", "朱仝", "鲁智深", "武松", "董平", "张清", "杨志", "徐宁", "索超", "戴宗", "刘唐", "李逵", "史进", "穆弘", "雷横", "李俊", "阮小二", "张横", "阮小五", " 张顺", "阮小七", "杨雄", "石秀", "解珍", " 解宝", "燕青", "朱武", "黄信", "孙立", "宣赞", "郝思文", "韩滔", "彭玘", "单廷珪", "魏定国", "萧让", "裴宣", "欧鹏", "邓飞", " 燕顺", "杨林", "凌振", "蒋敬", "吕方", "郭 盛", "安道全", "皇甫端", "王英", "扈三娘", "鲍旭", "樊瑞", "孔明", "孔亮", "项充", "李衮", "金大坚", "马麟", "童威", "童猛", "孟康", "侯健", "陈达", "杨春", "郑天寿", "陶宗旺", "宋清", "乐和", "龚旺", "丁得孙", "穆春", "曹正", "宋万", "杜迁", "薛永", "施恩", "周通", "李忠", "杜兴", "汤隆", "邹渊", "邹润", "朱富", "朱贵", "蔡福", "蔡庆", "李立", "李云", "焦挺", "石勇", "孙新", "顾大嫂", "张青", "孙二娘", " 王定六", "郁保四", "白胜", "时迁", "段景柱"};

    public static final String[] otherDepartment = new String[] {"图书馆", "现代教育技术中心", "后勤服务中心", "学院领导", "学院办公室", "教务处", "学工处", "组织人事处", "科技处", "财务处", "后勤保卫处", "机械工程学院", "电子电气工程学院", "土木工程学院", "化工学院", "商学院", "计算机科学与技术", "外语系", "基础科学系"};

    public static final String[] position = new String[] {"主任", "副主任", "处长", "副处长", "处长助理", "经理", "核算员", "管理员", "讲师"};

    public static final String[] header = new String[] {"http://ww2.sinaimg.cn/mw690/9fcdce0dgw1f5d9ik1ex9j20jg0jg777.jpg", "http://ww4.sinaimg.cn/mw690/9fcdce0dgw1f5d9ik9vgcj20jg0jggnc.jpg", "http://ww3.sinaimg.cn/mw690/9fcdce0dgw1f5d9ikgz82j20jg0jh0v0.jpg", "http://ww2.sinaimg.cn/mw690/9fcdce0dgw1f5d9ilbyr2j20jg0jg408.jpg", "http://ww2.sinaimg.cn/mw690/9fcdce0dgw1f5d9im7z0kj20jg0jgwhv.jpg", "http://ww1.sinaimg.cn/mw690/9fcdce0dgw1f5d9in8tkuj20jg0jgwgr.jpg", "http://ww3.sinaimg.cn/mw690/9fcdce0dgw1f5d9io8bbyj20jg0jgq5p.jpg", "http://ww2.sinaimg.cn/mw690/9fcdce0dgw1f5d9ip5niij20jg0jg40g.jpg", "http://ww4.sinaimg.cn/mw690/9fcdce0dgw1f5d9ipzojtj20jg0jg762.jpg", "http://ww3.sinaimg.cn/mw690/9fcdce0dgw1f5d09wyh2sj20jg0jhwil.jpg", "http://weibo.com/u/2681064973?is_search=0&visible=0&is_all=1&is_tag=0&profile_ftype=1&page=3#_rnd1467476536179", "http://ww1.sinaimg.cn/mw690/9fcdce0dgw1f5d09xg7syj20jg0jgwgd.jpg", "http://ww2.sinaimg.cn/mw690/9fcdce0dgw1f5d09xmwhij20jg0jpgn6.jpg", "http://ww2.sinaimg.cn/mw690/9fcdce0dgw1f5d09xrdvkj20jg0jggoc.jpg", "http://ww2.sinaimg.cn/mw690/9fcdce0dgw1f5d09xzw1wj20jg0jg76v.jpg", "http://ww1.sinaimg.cn/mw690/9fcdce0dgw1f5d09y9hycj20jg0jgq5l.jpg", "http://ww2.sinaimg.cn/mw690/9fcdce0dgw1f5d09yfozvj20jg0jl416.jpg", "http://ww2.sinaimg.cn/mw690/9fcdce0dgw1f5d09yo4s4j20jg0jgta9.jpg", ""};
    private String jsonTest = "[{\"ID\":2473,\"TypeID\":3,\"Title\":\"苏州科技学院：学科建设实现“十三五”开门红\",\"PostDate\":\"2016-6-24 9:46:36\"},{\"ID\":2468,\"TypeID\":3,\"Title\":\"学校隆重举行2016届毕业生学位授予仪式\",\"PostDate\":\"2016-6-21 17:57:49\"},{\"ID\":2469,\"TypeID\":3,\"Title\":\"学校参加江苏省第二届学生资助成效汇报演出\",\"PostDate\":\"2016-6-21 10:55:11\"},{\"ID\":2463,\"TypeID\":3,\"Title\":\"退还15万元善款 让爱心永续\",\"PostDate\":\"2016-6-20 9:02:58\"},{\"ID\":2454,\"TypeID\":3,\"Title\":\"我为什么要入党——记扬大马院学生党支部“传承‘红色基因’ 力行‘四讲四有’”主题党日活动\",\"PostDate\":\"2016-6-16 9:16:16\"},{\"ID\":2452,\"TypeID\":3,\"Title\":\"农业防灾：粮食丰产工程别开生面\",\"PostDate\":\"2016-6-15 9:16:25\"},{\"ID\":2450,\"TypeID\":3,\"Title\":\"一群在指尖上跳舞的90后大学生——苏大学子玩转地方传统漆艺侧记\",\"PostDate\":\"2016-6-14 11:11:41\"},{\"ID\":2449,\"TypeID\":3,\"Title\":\"苏大毕业生合伙创立档案公司 首单接到近20万订金\",\"PostDate\":\"2016-6-14 11:09:01\"},{\"ID\":2446,\"TypeID\":3,\"Title\":\"海参低聚肽：开启海参行业精深加工的新纪元\",\"PostDate\":\"2016-6-7 15:41:06\"},{\"ID\":2432,\"TypeID\":3,\"Title\":\"海外烹饪舞台上飘出“中国好味道”\",\"PostDate\":\"2016-5-31 9:18:47\"}]";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main, container, false);
        mEngine = App.getInstance().getEngine();
        mCubeBanner = (BGABanner) view.findViewById(R.id.banner_main_cube);
        mListView= (ListView) view.findViewById(R.id.lv_news);
        initCube();
//        joke = (MyImageView) view.findViewById(R.id.c_joke);
//        news = (MyImageView) view.findViewById(R.id.c_idea);
//        meeting = (MyImageView) view.findViewById(R.id.c_constellation);
//        recommend = (MyImageView) view.findViewById(R.id.c_recommend);
        initData();
//        joke.setOnClickIntent(
//                new MyImageView.OnViewClick() {
//                    @Override
//                    public void onClick() {
//                        // TODO Auto-generated method stub
//                        Toast.makeText(getActivity(), "事件触发", Toast.LENGTH_SHORT).show();
                        //往Bmob中加入模拟数据用【勿删】
                        //                        Random random = new Random();
                        //                        int index = NAMES.length;
                        //                        int index3 = otherDepartment.length;
                        //                        int index5 = position.length;
                        //                        int index2 = header.length;
                        //                        for (int i = 1; i <= 108; i++) {
                        //                            PersonalInfo personalInfo = new PersonalInfo();
                        //                            personalInfo.setName(NAMES[random.nextInt(index)]);
                        //                            personalInfo.setPosition(position[random.nextInt(index5)]);
                        //                            personalInfo.setOffice_tel("86150" + random.nextInt(9999));
                        //                            personalInfo.setOffice_short_tel("" + random.nextInt(9999));
                        //                            personalInfo.setMobile_phone("" + random.nextInt(9999));
                        //                            personalInfo.setMobile_short_phone("" + random.nextInt(9999));
                        //                            personalInfo.setHome_tel("1385255" + random.nextInt(9999));
                        //                            personalInfo.setEmail("16254" + random.nextInt(999) + "@163.com");
                        //                            personalInfo.setQq("16254" + random.nextInt(999));
                        //                            personalInfo.setDepartment(otherDepartment[random.nextInt(index3)]);
                        //                            personalInfo.setHead_url(header[random.nextInt(index2)]);
                        //                            personalInfo.insertObject(
                        //                                    getActivity(), new InsertListener() {
                        //                                        @Override
                        //                                        public void onSuccess() {
                        //                                            Toast.makeText(
                        //                                                    getActivity(), "添加成功", Toast.LENGTH_SHORT
                        //                                            ).show();
                        //
                        //                                        }
                        //
                        //                                        @Override
                        //                                        public void onFailure(String s) {
                        //                                            Toast.makeText(
                        //                                                    getActivity(), "添加失败", Toast.LENGTH_SHORT
                        //                                            ).show();
                        //
                        //                                        }
                        //                                    }
                        //                            );
                        //                        }

//                        Bundle bundle = new Bundle();
                        //                        WebviewFragment webViewFragment = new WebviewFragment();
                        //                        bundle.putString("moudle", "joke");
                        //                        webViewFragment.setArguments(bundle);
                        //                        getFragmentManager().beginTransaction().replace(
                        //                                R.id.container, webViewFragment
                        //                        ).commit();
//                    }
//                }

//        );

//        news.setOnClickIntent(
//                new MyImageView.OnViewClick() {
//                    @Override
//                    public void onClick() {
//                        Bundle bundle = new Bundle();
//                        WebviewFragment webViewFragment = new WebviewFragment();
//                        bundle.putString("moudle", "news");
//                        webViewFragment.setArguments(bundle);
//                        getFragmentManager().beginTransaction().replace(
//                                R.id.container, webViewFragment
//                        ).commit();
//                    }
//                }
//        );
//        meeting.setOnClickIntent(
//                new MyImageView.OnViewClick() {
//                    @Override
//                    public void onClick() {
//                        Bundle bundle = new Bundle();
//                        WebviewFragment webViewFragment = new WebviewFragment();
//                        bundle.putString("moudle", "meeting");
//                        webViewFragment.setArguments(bundle);
//                        getFragmentManager().beginTransaction().replace(
//                                R.id.container, webViewFragment
//                        ).commit();
//                    }
//                }
//        );
//        recommend.setOnClickIntent(
//                new MyImageView.OnViewClick() {
//                    @Override
//                    public void onClick() {
//                        Bundle bundle = new Bundle();
//                        WebviewFragment webViewFragment = new WebviewFragment();
//                        bundle.putString("moudle", "recommend");
//                        webViewFragment.setArguments(bundle);
//                        getFragmentManager().beginTransaction().replace(
//                                R.id.container, webViewFragment
//                        ).commit();
//                    }
//                }
//        );

        return view;
    }

    private void initCube() {


        mCubeViews = getViews(6);
        mCubeBanner.setViews(mCubeViews);

        mEngine.sixItem().enqueue(
                new Callback<BannerModel>() {
                    @Override
                    public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                        BannerModel bannerModel = response.body();
                        for (int i = 0; i < mCubeViews.size(); i++) {
                            Picasso.with(App.getInstance())
                                   .load(bannerModel.imgs.get(i))
                                   .placeholder(R.drawable.holder)
                                   .error(R.drawable.holder)
                                   .into(mCubeViews.get(i));
                            // 为每一页添加点击事件
                            final int finalPosition = i;
                            mCubeViews.get(i).setOnClickListener(
                                    new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Toast.makeText(
                                                    App.getInstance(),
                                                    "点击了第" + (finalPosition + 1) + "页",
                                                    Toast.LENGTH_SHORT
                                            ).show();
                                        }
                                    }
                            );
                        }
                        // 也可以不设置tips
                        mCubeBanner.setTips(bannerModel.tips);
                    }

                    @Override
                    public void onFailure(Call<BannerModel> call, Throwable t) {
                    }
                }
        );
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    /**
     * 获取图片
     * @param count
     * @return
     */
    private List<ImageView> getViews(int count) {
        List<ImageView> views = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            views.add(
                    (ImageView) getLayoutInflater(Bundle.EMPTY).inflate(
                            R.layout.view_image, null
                    )
            );
        }
        return views;
    }

    private ListView mListView;

    public List<NewsInfo> newsInfoList = new ArrayList<NewsInfo>();

    private String createTime[] = new String[200];
    private String content[] = new String[200];
    public List<NewsInfo> mList = new ArrayList<NewsInfo>();

    public void initData() {

        newsInfoList = parserStrToGson(jsonTest);
        for (int i = 0; i < newsInfoList.size(); i++) {
            createTime[i] = newsInfoList.get(i).getTitle();
            content[i] = newsInfoList.get(i).getPostDate();
        }
        for (int i = 0; i < newsInfoList.size(); i++) {
            mList.add(
                    new NewsInfo(
                            content[i], createTime[i]
                    )
            );
        }
        initView();

    }

    /**
     * 把json字符串解析到List
     *
     * @param msg
     *
     * @return
     */
    private List<NewsInfo> parserStrToGson(String msg) {
        // 创建一个Gson对象
        Gson gson = new Gson();
        // 记号：按List<NewsInfo>来进行字符串解析
        TypeToken<List<NewsInfo>> token = new TypeToken<List<NewsInfo>>() {};
        // 将GSON格式的字符串转为指定的格式
        System.out.println(msg);
        return gson.fromJson(msg, token.getType());
    }


    public void initView() {
        mListView.setAdapter(
                new CommonAdapter<NewsInfo>(
                        getActivity(), newsInfoList, R.layout.item_news
                ) {
                    @Override
                    public void convert(ViewHolder holder, NewsInfo newsInfo) {
                        holder.setText(
                                R.id.tv_news_title, newsInfo.getTitle()
                        ).setText(
                                R.id.tv_post_date, newsInfo.getPostDate()

                        );


                    }
                }
        );
    }
}
