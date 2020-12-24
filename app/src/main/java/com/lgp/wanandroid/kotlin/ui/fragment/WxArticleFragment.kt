package com.lgp.wanandroid.kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lgp.wanandroid.kotlin.R


/**
 * 创建者     罗国鹏
 * 创建时间   2020/12/24 13:53
 * 描述
 * 更新者     $
 * 更新时间   $
 * 更新描述
 */
class WxArticleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wx_article,container,false)
    }
}