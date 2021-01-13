package com.lgp.wanandroid.kotlin.di

import com.lgp.wanandroid.kotlin.repository.HomeRepository
import com.lgp.wanandroid.kotlin.repository.local.HomeDao
import com.lgp.wanandroid.kotlin.repository.remote.HomeNetwork
import com.lgp.wanandroid.kotlin.viewmodel.factory.HomeViewModelFactory


/**
 * 创建者     罗国鹏
 * 创建时间   2020/12/30 17:32
 * 描述
 * 更新者     $
 * 更新时间   $
 * 更新描述
 */
object InjectorUtil {
    private fun getHomeRepository()=HomeRepository.getInstance(HomeDao(), HomeNetwork())

    fun getHomeViewModelFactory() = HomeViewModelFactory(getHomeRepository())
}