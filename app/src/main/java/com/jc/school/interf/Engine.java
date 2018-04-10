package com.jc.school.interf;


import com.jc.school.bean.BannerModel;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Engine {

    @GET("6item.json")
    Call<BannerModel> sixItem();

}