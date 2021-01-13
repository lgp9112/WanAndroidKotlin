package com.lgp.wanandroid.kotlin.repository.remote.api

import com.lgp.wanandroid.kotlin.repository.remote.bean.Article
import com.lgp.wanandroid.kotlin.repository.remote.bean.ArticleList
import com.lgp.wanandroid.kotlin.repository.remote.bean.Banner
import com.lgp.wanandroid.kotlin.repository.remote.bean.WanResponse
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * 创建者     罗国鹏
 * 创建时间   2020/12/30 17:44
 * 描述
 * 更新者     $
 * 更新时间   $
 * 更新描述
 */
interface WanService {

    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }

    /**
     * 获取轮播图
     */
    @GET("/banner/json")
    suspend fun getBanner(): WanResponse<MutableList<Banner>>

    /**
     * 获取首页置顶文章列表
     */
    @GET("article/top/json")
    suspend fun getTopArticles(): WanResponse<MutableList<Article>>

    /**
     * 获取文章列表
     */
    @GET("/article/list/{page}/json")
    suspend fun getHomeArticles(@Path("page") page: Int): WanResponse<ArticleList>
}