package com.wds.api;

import com.wds.bean.DailyListBean;
import com.wds.bean.LoginBean;
import com.wds.bean.RegisterBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    String baseUrl = "http://47.110.151.50/p6/";

    /**
     * 注册,
     *
     * @param userid
     * @param psd
     * @param accessToken 三方平台唯一标识,可选参数,三方注册使用
     * @param typeid      ,可选参数,三方注册使用,三方平台类型,1是qq,2是微信,3微博
     * @return
     */
    @POST("register.do")
    @FormUrlEncoded
    Flowable<RegisterBean> register(@Field("userid") String userid,
                                    @Field("password") String psd,
                                    @Field("accessToken") String accessToken,
                                    @Field("typeid") String typeid);

    /**
     * 账号密码登录
     *
     * @param userid
     * @param psd
     * @return
     */
    @POST("login.do")
    @FormUrlEncoded
    Flowable<LoginBean> login(@Field("userid") String userid,
                              @Field("password") String psd);

    /*String loginUrl="https://www.wanandroid.com/";
    @POST("user/login")
    @FormUrlEncoded
    Flowable<LoginBean> getUrl(@Field("username") String username, @Field("password") String password);*/




    String zhihuUrl = "http://news-at.zhihu.com/";

    @GET("api/4/news/latest")
    Flowable<DailyListBean> getLatestList();

    @GET("api/4/news/before/{date}")
    Flowable<DailyListBean> getBeForeLatestList(@Path("date") String date);
  /*  *//**
     * 专栏日报
     *//*
    @GET("api/4/sections")
    Observable<SectionListBean> getSectionList();

    *//**
     * 日报详情
     * http://news-at.zhihu.com/api/4/news/9713242
     *//*
    @GET("api/4/news/{id}")DailyListBean
    Observable<DailyDetailBean> getDetailInfo(@Path("id") String id);*/
}
