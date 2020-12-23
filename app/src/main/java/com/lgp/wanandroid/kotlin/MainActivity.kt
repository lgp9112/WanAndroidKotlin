package com.lgp.wanandroid.kotlin

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import com.blankj.utilcode.util.BarUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1.windowBackground启动白屏优化
        setTheme(R.style.Theme_WanAndroidKotlin)
        //2.沉浸式状态栏（注意顺序要在启动白屏优化之后）
        BarUtils.transparentStatusBar(this)

        setContentView(R.layout.activity_main)

//        btn.setOnClickListener {
//            drawerLayout.openDrawer(Gravity.START)
//        }
    }
}