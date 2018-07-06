package com.jc.school.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.jc.school.R;
import com.jc.school.bean.FocusNews;
import com.jc.school.utils.CommonAdapter;
import com.jc.school.utils.MyCallBack;
import com.jc.school.utils.ViewHolder;
import com.orhanobut.logger.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jaycee on 16/7/2.
 * E-mail：jayceeok@foxmail.com
 */
public class LocalNewsFragment extends Fragment {

    private static final String TAG = "LocalNewsFragment";
    Button btnQuery;
    private String titleName;
    ListView listView;
//    Realm realm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_local_news, container, false);
//        btnQuery = (Button) view.findViewById(R.id.btn_query);
//        btnQuery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                RealmResults<Dog> realmResults = realm.where(Dog.class).findAll();
//                Toast.makeText(getActivity(), realmResults.get(0).getName(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
        // Get a Realm instance for this thread
//        realm = Realm.getDefaultInstance();
        listView = (ListView) view.findViewById(R.id.lv);

        initData();


        return view;
    }

    private void initData() {
//        realm.beginTransaction();
//        Dog dog = realm.createObject(Dog.class);
//        dog.setAge(32);
//        dog.setName("钢铁侠");
//        realm.commitTransaction();
        RequestParams params = new RequestParams("http://news.usts.edu.cn/news/news_more.asp?lm=1");
//        params.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
//        params.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.75 Safari/537.36");
        params.addHeader("", "");
        params.setCharset("gb2312");
        x.http().get(params, new MyCallBack<String>(getContext()) {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
//                Logger.i(TAG,"成功 ： "+result);
                initHtml(result);
                listView.setAdapter(new CommonAdapter<FocusNews>(getActivity(), focusNewsList, R.layout.item_news) {
                    @Override
                    public void convert(ViewHolder holder, FocusNews focusNews) {
                        holder.setText(R.id.tv_title, focusNews.getTitle());
                    }
                });
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                Logger.i(TAG, "报错 ： " + "   --- " + ex);
            }

            @Override
            public void onFinished() {
                super.onFinished();
            }
        });
    }

    List<FocusNews> focusNewsList = new ArrayList<>();

    /**
     * 处理获取的html数据
     */
    private void initHtml(String html) {
        Document doc = Jsoup.parse(html);
        Elements title = doc.getElementsByClass("list_lb");
        String s = title.toString();
//        Logger.i(TAG,s);
        Document doc1 = Jsoup.parse(s);
        Elements img = doc1.getElementsByTag("a");
//        Logger.i(TAG, String.valueOf(img));
//        MyLog.log("pic ： " +img.size()+" ---- "+img);
//        if ((img.size()!=0&&isRefresh)||meiZiModels.size()>=200){
//            meiZiModels.clear();
//            adapter.notifyDataSetChanged();
//            isRefresh = false;
//        }
        for (Element e : img) {
            String titleName = e.attr("title");
//            Logger.i(TAG, "title : " + titleName);
            if (!TextUtils.isEmpty(titleName)) {
                String name = e.attr("title");
                Logger.i(TAG, name);
                FocusNews focusNews = new FocusNews();
                focusNews.setTitle(name);
                // 截取地址
//                if (imgUrl.length()>0){
//                    int dian = imgUrl.lastIndexOf("."); // 截取到最后一个点
//                    int gang = imgUrl.lastIndexOf("/");
//                    String num = imgUrl.substring((gang+1), dian); // 妹子号
//                    String substring1 = imgUrl.substring(0, gang); // 截取 妹子号后剩余下的
//                    int gang2 = substring1.lastIndexOf("/");
//                    String year = substring1.substring((gang2+1)); // 年份
////                    MyLog.log(imgUrl +" ==year== "+year+ " --num--" +num);
//                    meiZiModel.setNum(num);
//                    meiZiModel.setYare(year);
//                    meiZiModels.add(meiZiModel);
//                }
                focusNewsList.add(focusNews);
            }
        }
    }
}
