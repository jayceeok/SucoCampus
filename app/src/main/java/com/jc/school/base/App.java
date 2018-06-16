package com.jc.school.base;


import android.app.Application;

import com.jc.school.BuildConfig;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.simple.spiderman.CrashModel;
import com.simple.spiderman.SpiderMan;
import com.tencent.bugly.crashreport.CrashReport;

import org.xutils.x;

public class App extends Application {
    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.

        //初始化日志
        Logger.init(getPackageName()).hideThreadInfo();
        Logger.init("AppPlusLog").setMethodCount(2).setLogLevel(LogLevel.FULL);

        SpiderMan.getInstance()
                .init(this)
                //设置是否捕获异常，不弹出崩溃框
                .setEnable(true)
                //设置是否显示崩溃信息展示页面
                .showCrashMessage(true)
                //是否回调异常信息，友盟等第三方崩溃信息收集平台会用到,
                .setOnCrashListener(new SpiderMan.OnCrashListener() {
                    @Override
                    public void onCrash(Thread t, Throwable ex, CrashModel model) {
                        //CrashModel 崩溃信息记录，包含设备信息
                    }
                });

//        UMConfigure.init(this,"5a12384aa40fa3551f0001d1"
//                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
   /* Bugly SDK初始化
        * 参数1：上下文对象
        * 参数2：APPID，平台注册时得到,注意替换成你的appId
        * 参数3：是否开启调试模式，调试模式下会输出'CrashReport'tag的日志
        */
        CrashReport.initCrashReport(getApplicationContext(), "e1120af31b", true);


    }


    public static App getInstance() {
        return sInstance;
    }

}