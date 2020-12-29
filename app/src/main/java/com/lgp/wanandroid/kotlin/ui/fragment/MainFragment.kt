package com.lgp.wanandroid.kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lgp.wanandroid.kotlin.R
import com.lgp.wanandroid.kotlin.ktx.lifecycle.MyLifecycleObserver
import com.lgp.wanandroid.kotlin.ui.adapter.VersionAdapter
import kotlinx.android.synthetic.main.fragment_main.*


/**
 * 创建者     罗国鹏
 * 创建时间   2020/12/24 13:45
 * 描述
 * 更新者     $
 * 更新时间   $
 * 更新描述
 */
class MainFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycle.addObserver(MyLifecycleObserver())

        initView()
    }

    private fun initView() {
        recyclerView.layoutManager=LinearLayoutManager(activity)
        val versionAdapter = VersionAdapter()
        recyclerView.adapter=versionAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        val mutableListOf = mutableListOf<String>()
        for (i in 0..100){
            mutableListOf.add("我是 $i")
        }
        versionAdapter.notifyDataSetChanged(mutableListOf)
    }
}