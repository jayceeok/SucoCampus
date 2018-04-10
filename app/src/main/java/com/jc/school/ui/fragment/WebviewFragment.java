package com.jc.school.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jc.school.R;
import com.orhanobut.logger.Logger;

/**
 * Created by Jaycee on 16/7/2.
 * E-mail：jayceeok@foxmail.com
 */
public class WebviewFragment extends Fragment {
    private static final String TAG = "WebviewFragment";
    WebView mWebView;
    private static String URL;
    private String url1 = "http://news.usts.edu.cn/news/news_more.asp?lm2=2";
    private String url2 = "http://news.usts.edu.cn/news/news_more.asp?lm2=1";
    private String url3 = "http://notify.usts.edu.cn/news/news_more.asp?lm2=2";
    private String url4 = "http://notify.usts.edu.cn/news/news_more.asp?lm2=1";
    private String moudle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_webview, container, false);
        mWebView = (WebView) view.findViewById(R.id.webView);
        Bundle bundle = getArguments();
        moudle = bundle.getString("moudle");
        Logger.i(TAG, moudle);
        if ("joke".equals(moudle)) {
            URL = url1;
        }
        if ("news".equals(moudle)) {
            URL = url2;
        }
        if ("meeting".equals(moudle)) {
            URL = url3;
        }
        if ("recommend".equals(moudle)) {
            URL = url4;
        }

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(
                new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                }
        );
            mWebView.loadUrl(URL);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
