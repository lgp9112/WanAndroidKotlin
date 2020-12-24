package com.lgp.wanandroid.kotlin.ktx.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.blankj.utilcode.util.LogUtils

/**
 * Created by luyao
 * on 2019/8/6 15:10
 */
class KtxAppLifeObserver : LifecycleObserver {
    private val TAG:String="111"

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onForeground() {
        LogUtils.eTag(TAG, "KtxAppLifeObserver 应用进入前台")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onBackground() {
        LogUtils.eTag(TAG, "KtxAppLifeObserver 应用进入后台")
    }
}