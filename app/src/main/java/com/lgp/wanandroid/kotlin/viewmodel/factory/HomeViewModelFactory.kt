package com.lgp.wanandroid.kotlin.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lgp.wanandroid.kotlin.repository.HomeRepository
import com.lgp.wanandroid.kotlin.viewmodel.HomeViewModel


/**
 * 创建者     罗国鹏
 * 创建时间   2020/12/30 15:42
 * 描述
 * 更新者     $
 * 更新时间   $
 * 更新描述
 */
class HomeViewModelFactory(private val homeRepository: HomeRepository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(homeRepository) as T
    }
}