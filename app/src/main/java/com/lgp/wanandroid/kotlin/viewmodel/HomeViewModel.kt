package com.lgp.wanandroid.kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.lgp.wanandroid.kotlin.repository.HomeRepository
import com.lgp.wanandroid.kotlin.repository.remote.bean.Article
import com.lgp.wanandroid.kotlin.repository.remote.bean.Banner
import com.lgp.wanandroid.kotlin.repository.remote.bean.Result
import kotlinx.coroutines.*


/**
 * 创建者     罗国鹏
 * 创建时间   2020/12/30 15:03
 * 描述
 * 更新者     $
 * 更新时间   $
 * 更新描述
 */
class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _uiState = MutableLiveData<ArticleUiModel>()
    val uiState: LiveData<ArticleUiModel>
        get() = _uiState

    private val _banner=MutableLiveData<List<Banner>>()
    val banner:LiveData<List<Banner>>
        get() =_banner

    val refreshHome: () -> Unit = {
        LogUtils.eTag("111", "HomeViewModel.refreshHome")
        //getHomeArticleList()
    }

    private var currentPage = 0

    fun requestBanner(){
        viewModelScope.launch(Dispatchers.Main) {
            val banners = homeRepository.getBanners()
            if (banners is Result.Success){
                val data = banners.data
                _banner.value=data
            }
        }
    }

    fun requestHomeData(){
        LogUtils.eTag("111", "HomeViewModel.requestHomeData")
        viewModelScope.launch(Dispatchers.Main) {
            emitArticleUiState(true)

            val topArticleListDeferred = async { homeRepository.getTopArticleList() }
            val articleListDeferred = async { homeRepository.getArticleList(currentPage) }

            val topArticleList = topArticleListDeferred.await()
            val articleList = articleListDeferred.await()

//            val withContext = withContext(Dispatchers.IO) {
//                delay(5000)
//                "haha"
//            }

            if (topArticleList is Result.Success && articleList is Result.Success) {
                val data = topArticleList.data.apply {
                    for (item in this) {
                        item.top = true
                    }
                }
                val datas = articleList.data.datas
                data.addAll(datas)

                currentPage++

                emitArticleUiState(showLoading = false, showSuccess = data, isRefresh = true)
            } else if (topArticleList is Result.Error) {
                emitArticleUiState(
                    showLoading = false,
                    showError = "获取置顶文章失败：${topArticleList.exception.message}"
                )
            } else if (articleList is Result.Error) {
                emitArticleUiState(
                    showLoading = false,
                    showError = "获取文章失败：${articleList.exception.message}"
                )
            }
        }
    }

    fun getHomeArticleList(isRefresh: Boolean = false) {
        viewModelScope.launch(Dispatchers.Main) {
            emitArticleUiState(true)

            if (isRefresh){
                currentPage=0
            }

            val articleList = homeRepository.getArticleList(currentPage)
            if (articleList is Result.Success) {
                val datas = articleList.data.datas

                currentPage++
                emitArticleUiState(showLoading = false, showSuccess = datas.toMutableList(), isRefresh = isRefresh)
            } else if (articleList is Result.Error) {
                emitArticleUiState(
                    showLoading = false,
                    showError = "获取文章失败：${articleList.exception.message}"
                )
            }
        }
    }

    private fun emitArticleUiState(
        showLoading: Boolean = false,
        showError: String? = null,
        showSuccess: MutableList<Article>? = null,
        showEnd: Boolean = false,
        isRefresh: Boolean = false,
        needLogin: Boolean? = null
    ) {
        val uiModel =
            ArticleUiModel(showLoading, showError, showSuccess, showEnd, isRefresh, needLogin)
        _uiState.value = uiModel
    }

    data class ArticleUiModel(
        val showLoading: Boolean,
        val showError: String?,
        val showSuccess: MutableList<Article>?,
        val showEnd: Boolean, // 加载更多
        val isRefresh: Boolean, // 刷新
        val needLogin: Boolean? = null
    )

    data class HahaData(
        val textStr:String
    )
}