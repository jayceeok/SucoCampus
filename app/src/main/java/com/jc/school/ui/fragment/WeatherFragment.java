package com.jc.school.ui.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jc.school.R;
import com.jc.school.bean.WeatherInfo;
import com.jc.school.utils.GsonUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jaycee on 16/7/2.
 * E-mail：jayceeok@foxmail.com
 */
public class WeatherFragment extends Fragment {

    private static final String TAG = "WeatherFragment";
    TextView mCity, mTemp, mTime;
//    ImageView mWeatherLogo;
    private String url = "http://v.juhe.cn/weather/index?format=2&cityname=苏州&key=ff1eeafe4559a9b194284deab0a856eb";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_weather, container, false);
        mCity = (TextView) view.findViewById(R.id.current_city);
        mTemp = (TextView) view.findViewById(R.id.temperature);
//        mWeatherLogo = (ImageView) view.findViewById(R.id.current_weather_logo);
        mTime = (TextView) view.findViewById(R.id.tv_pubTime);


        initData();

        return view;
    }


    private void initData() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("请稍候...");
        Toast.makeText(getActivity(), "get!", Toast.LENGTH_LONG).show();
        RequestParams requestParams = new RequestParams(url);
        final Callback.Cancelable cancelable = x.http().get(
                requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.i(TAG, result);
                        progressDialog.cancel();

                        WeatherInfo weatherInfo = GsonUtils.jsonToBean(result, WeatherInfo.class);
                        if ("200".equals(weatherInfo.getResultcode())) {

                            mCity.setText(weatherInfo.getResult().getToday().getCity());
                            mTemp.setText(weatherInfo.getResult().getToday().getTemperature());
                            mTime.setText(
                                    weatherInfo.getResult()
                                               .getToday()
                                               .getDate_y() + weatherInfo.getResult()
                                                                         .getSk()
                                                                         .getTime()
                            );
//                            //晴天
//                            if ("00".equals(
//                                    weatherInfo.getResult().getToday().getWeather_id().getFa()
//                            )) {
//                                mWeatherLogo.setImageResource(R.drawable.weather_sunny);
//                            }
//
//                            //中雨
//                            if ("08".equals(
//                                    weatherInfo.getResult().getToday().getWeather_id().getFa()
//                            )) {
//                                mWeatherLogo.setImageResource(R.drawable.weather_middle_rain);
//                            }

                            //                            //晴天
                            //                            if ("00".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFa()
                            //                            ) && "00".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFb()
                            //                            )) {
                            //
                            //                            }
                            //                            //中雨
                            //                            if ("08".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFa()
                            //                            ) && "08".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFb()
                            //                            )) {
                            //
                            //
                            //                            }
                            //                            //多云
                            //                            if ("01".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFa()
                            //                            ) && "01".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFb()
                            //                            )) {
                            //
                            //
                            //                            }
                            //                            //晴转多云
                            //                            if ("00".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFa()
                            //                            ) && "01".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFb()
                            //                            )) {
                            //
                            //
                            //                            }
                            //                            //晴转霾
                            //                            if ("00".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFa()
                            //                            ) && "53".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFb()
                            //                            )) {
                            //
                            //
                            //                            }
                            //                            //阵雨转暴雨
                            //                            if ("03".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFa()
                            //                            ) && "10".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFb()
                            //                            )) {
                            //
                            //
                            //                            }
                            //                            //大雨转中雨-大雨
                            //                            if ("09".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFa()
                            //                            ) && "22".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFb()
                            //                            )) {
                            //
                            //
                            //                            }
                            //                            //小雨-中雨转雷阵雨
                            //                            if ("21".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFa()
                            //                            ) && "04".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFb()
                            //                            )) {
                            //
                            //
                            //                            }
                            //                            //雷阵雨
                            //                            if ("04".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFa()
                            //                            ) && "04".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFb()
                            //                            )) {
                            //
                            //
                            //                            }
                            //                            //雷阵雨转阴
                            //                            if ("04".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFa()
                            //                            ) && "02".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFb()
                            //                            )) {
                            //
                            //
                            //                            }
                            //                            //晴转小雨
                            //                            if ("00".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFa()
                            //                            ) && "07".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFb()
                            //                            )) {
                            //
                            //
                            //                            }
                            //                            //雷阵雨转多云
                            //                            if ("04".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFa()
                            //                            ) && "01".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFb()
                            //                            )) {
                            //
                            //
                            //                            }
                            //                            //阵雨转晴
                            //                            if ("03".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFa()
                            //                            ) && "00".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFb()
                            //                            )) {
                            //
                            //
                            //                            }
                            //                            //阴转多云
                            //                            if ("02".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFa()
                            //                            ) && "01".equals(
                            //                                    weatherInfo.getResult().getToday().getWeather_id().getFb()
                            //                            )) {
                            //
                            //
                            //                            }
                        }
                    }

                    //异常的信息，异常的来源，如果isOnCallback为true，说明异常信息来源于回调方法
                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {
                        //成功失败都会执行
                        progressDialog.cancel();
                    }
                }
        );

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
