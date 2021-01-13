package com.lgp.wanandroid.kotlin.repository.remote

import com.blankj.utilcode.util.LogUtils
import com.lgp.wanandroid.kotlin.repository.remote.api.RetrofitHelper
import com.lgp.wanandroid.kotlin.repository.remote.bean.Article
import com.lgp.wanandroid.kotlin.repository.remote.bean.ArticleList
import com.lgp.wanandroid.kotlin.repository.remote.bean.Banner
import com.lgp.wanandroid.kotlin.repository.remote.bean.WanResponse
import kotlin.contracts.Returns


/**
 * 创建者     罗国鹏
 * 创建时间   2020/12/30 16:45
 * 描述
 * 更新者     $
 * 更新时间   $
 * 更新描述
 */
class HomeNetwork {

    suspend fun fetchBanners(): WanResponse<List<Banner>>{
        return RetrofitHelper.service.getBanner()
    }

    suspend fun fetchTopArticles():WanResponse<MutableList<Article>>{
        return RetrofitHelper.service.getTopArticles()
    }


    suspend fun fetchArticleList(page: Int): WanResponse<ArticleList> {
        return RetrofitHelper.service.getHomeArticles(page)
    }
}