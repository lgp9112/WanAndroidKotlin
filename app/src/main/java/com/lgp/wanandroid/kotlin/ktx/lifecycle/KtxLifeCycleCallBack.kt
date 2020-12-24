package com.lgp.wanandroid.kotlin.ktx.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.blankj.utilcode.util.LogUtils

/**
 * Created by luyao
 * on 2019/8/6 10:45
 */
class KtxLifeCycleCallBack : Application.ActivityLifecycleCallbacks {
    private val TAG:String="111"

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        KtxManager.pushActivity(activity)
        LogUtils.eTag(TAG, "KtxLifeCycleCallBack.onActivityCreated ${activity.localClassName}")
    }

    override fun onActivityStarted(activity: Activity) {
        LogUtils.eTag(TAG, "KtxLifeCycleCallBack.onActivityStarted ${activity.localClassName}")
    }

    override fun onActivityResumed(activity: Activity) {
        LogUtils.eTag(TAG, "KtxLifeCycleCallBack.onActivityResumed ${activity.localClassName}")
    }

    override fun onActivityPaused(activity: Activity) {
        LogUtils.eTag(TAG, "KtxLifeCycleCallBack.onActivityPaused ${activity.localClassName}")
    }

    override fun onActivityStopped(activity: Activity) {
        LogUtils.eTag(TAG, "KtxLifeCycleCallBack.onActivityStopped ${activity.localClassName}")
    }

    override fun onActivityDestroyed(activity: Activity) {
        LogUtils.eTag(TAG, "KtxLifeCycleCallBack.onActivityDestroyed ${activity.localClassName}")
        KtxManager.popActivity(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        LogUtils.eTag(TAG, "KtxLifeCycleCallBack.onActivitySaveInstanceState ${activity.localClassName}")
    }
}