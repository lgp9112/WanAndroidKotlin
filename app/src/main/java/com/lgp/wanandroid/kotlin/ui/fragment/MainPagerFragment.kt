package com.lgp.wanandroid.kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lgp.wanandroid.kotlin.R
import com.lgp.wanandroid.kotlin.ktx.lifecycle.MyLifecycleObserver


/**
 * 创建者     罗国鹏
 * 创建时间   2020/12/24 13:45
 * 描述
 * 更新者     $
 * 更新时间   $
 * 更新描述
 */
class MainPagerFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_pager,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycle.addObserver(MyLifecycleObserver())
    }
}