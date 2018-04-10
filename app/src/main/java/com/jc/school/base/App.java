package com.jc.school.base;


import android.app.Application;

import com.jc.school.interf.Engine;

import org.xutils.x;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static App sInstance;
    private Engine mEngine;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mEngine = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/bingoogolapple/BGABanner-Android/server/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Engine.class);
        x.Ext.init(this);

    }


    public static App getInstance() {
        return sInstance;
    }

    public Engine getEngine() {
        return mEngine;
    }
}