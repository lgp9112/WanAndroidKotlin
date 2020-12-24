package com.lgp.wanandroid.kotlin.ktx.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.blankj.utilcode.util.LogUtils


/**
 * 创建者     罗国鹏
 * 创建时间   2020/12/24 14:57
 * 描述
 * 更新者     $
 * 更新时间   $
 * 更新描述
 */
class MyLifecycleObserver: LifecycleObserver {
    private val TAG:String="111"

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate(owner: LifecycleOwner) {
        LogUtils.eTag(TAG, "MyLifecycleObserver.onCreate ${owner.javaClass.simpleName}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart(owner: LifecycleOwner) {
        LogUtils.eTag(TAG, "MyLifecycleObserver.onStart ${owner.javaClass.simpleName}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume(owner: LifecycleOwner) {
        LogUtils.eTag(TAG, "MyLifecycleObserver.onResume ${owner.javaClass.simpleName}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause(owner: LifecycleOwner) {
        LogUtils.eTag(TAG,"MyLifecycleObserver.onPause ${owner.javaClass.simpleName}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop(owner: LifecycleOwner) {
        LogUtils.eTag(TAG,"MyLifecycleObserver.onStop ${owner.javaClass.simpleName}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy(owner: LifecycleOwner) {
        LogUtils.eTag(TAG,"MyLifecycleObserver.onDestroy ${owner.javaClass.simpleName}")
    }
}