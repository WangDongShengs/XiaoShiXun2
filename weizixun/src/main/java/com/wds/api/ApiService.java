package com.wds.api;

import com.wds.bean.LoginBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    String loginUrl="https://www.wanandroid.com/";
    @POST("user/login")
    @FormUrlEncoded
    Flowable<LoginBean> getUrl(@Field("username") String username, @Field("password") String password);
}
