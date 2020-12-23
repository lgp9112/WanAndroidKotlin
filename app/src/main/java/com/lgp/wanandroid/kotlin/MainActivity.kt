package com.lgp.wanandroid.kotlin

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.blankj.utilcode.util.BarUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //windowBackground启动白屏优化
        setTheme(R.style.Theme_WanAndroidKotlin)
        setContentView(R.layout.activity_main)

        transparentStatusBar4Drawer(this,drawerLayout)
    }
}