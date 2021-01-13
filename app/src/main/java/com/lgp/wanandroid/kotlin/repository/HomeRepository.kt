package com.lgp.wanandroid.kotlin.repository

import com.lgp.wanandroid.kotlin.repository.local.HomeDao
import com.lgp.wanandroid.kotlin.repository.remote.HomeNetwork
import com.lgp.wanandroid.kotlin.repository.remote.api.BaseRepository
import com.lgp.wanandroid.kotlin.repository.remote.bean.Article
import com.lgp.wanandroid.kotlin.repository.remote.bean.ArticleList
import com.lgp.wanandroid.kotlin.repository.remote.bean.Banner
import com.lgp.wanandroid.kotlin.repository.remote.bean.Result


/**
 * 创建者     罗国鹏
 * 创建时间   2020/12/30 15:38
 * 描述
 * 更新者     $
 * 更新时间   $
 * 更新描述
 */
class HomeRepository private constructor(
    private val homeDao: HomeDao,
    private val homeNetwork: HomeNetwork
) : BaseRepository() {

    suspend fun getBanners(): Result<List<Banner>> {
        return safeApiCall(call = {requestBanners()},errorMessage = "")
    }

    private suspend fun requestBanners(): Result<List<Banner>> =
        executeResponse(homeNetwork.fetchBanners())

    suspend fun getTopArticleList(): Result<MutableList<Article>>  {
        return safeApiCall({requestTopArticleList()},"")
    }

    private suspend fun requestTopArticleList(): Result<MutableList<Article>> =
        executeResponse(homeNetwork.fetchTopArticles())


    suspend fun getArticleList(page: Int): Result<ArticleList>  {
        return safeApiCall({requestArticleList(page)},"")
    }

    private suspend fun requestArticleList(page: Int): Result<ArticleList> =
        executeResponse(homeNetwork.fetchArticleList(page))


    companion object {
        @Volatile
        private var instance: HomeRepository? = null

        fun getInstance(homeDao: HomeDao, homeNetwork: HomeNetwork) =
            instance ?: synchronized(HomeRepository::class.java) {
                instance ?: HomeRepository(homeDao, homeNetwork).also { instance = it }
            }
    }
}