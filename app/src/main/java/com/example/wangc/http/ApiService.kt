package com.example.wangc.http

import com.example.wangc.bean.BannerBean
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by wangc on 2018/7/6
 * E-MAIL:274281610@QQ.COM
 */
interface  ApiService{
    //获取首页列表
    @GET("banner/json")
    fun getMainList() : Call<BannerBean>


}