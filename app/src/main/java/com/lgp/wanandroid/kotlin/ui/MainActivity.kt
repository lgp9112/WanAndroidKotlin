package com.lgp.wanandroid.kotlin.ui

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.*
import com.lgp.wanandroid.kotlin.R
import com.lgp.wanandroid.kotlin.util.transparentStatusBar4Drawer
import com.lgp.wanandroid.kotlin.ui.adapter.VersionAdapter
import com.lgp.wanandroid.kotlin.ui.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val mainFragment: MainFragment by lazy { MainFragment() }
    private val blogFragment: BlogFragment by lazy { BlogFragment() }
    private val searchFragment: SearchFragment by lazy { SearchFragment() }
    private val projectFragment: ProjectFragment by lazy { ProjectFragment() }
    private val profileFragment: ProfileFragment by lazy { ProfileFragment() }

    private val versionAdapter: VersionAdapter by lazy { VersionAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //windowBackground启动白屏优化
        setTheme(R.style.Theme_WanAndroidKotlin)
//        BarUtils.transparentStatusBar(this)
        setContentView(R.layout.activity_main)

        transparentStatusBar4Drawer(this, drawerLayout)
        BarUtils.setStatusBarColor(barStatusDrawerFakeStatusBar,Color.TRANSPARENT)
//        BarUtils.addMarginTopEqualStatusBarHeight(common_toolbar)

        initToolbar()
        initDrawerLayout()
        initView()
    }

    private fun initToolbar() {
        setSupportActionBar(common_toolbar)
//        supportActionBar?.setDisplayShowTitleEnabled(false)//去掉toolbar默认title
//        common_toolbar_title_tv.text = getString(R.string.home_pager)//设置toolbar title

    }

    private fun initDrawerLayout() {
        //会覆盖toolbar.setNavigationOnClickListener
        val actionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            common_toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                LogUtils.eTag("222", "MainActivity.onDrawerSlide")
                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view

                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view
                val mContent = drawerLayout.getChildAt(0)
                val scale = 1 - slideOffset
                val endScale = 0.8f + scale * 0.2f
                val startScale = 1 - 0.3f * scale

                //设置左边菜单滑动后的占据屏幕大小

                //设置左边菜单滑动后的占据屏幕大小
                drawerView.scaleX = startScale
                drawerView.scaleY = startScale
                //设置菜单透明度
                //设置菜单透明度
                drawerView.alpha = 0.6f + 0.4f * (1 - scale)

                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度

                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.translationX = drawerView.measuredWidth * (1 - scale)
                //设置内容界面操作无效（比如有button就会点击无效）
                //设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate()
                //设置右边菜单滑动后的占据屏幕大小
                //设置右边菜单滑动后的占据屏幕大小
                mContent.scaleX = endScale
                mContent.scaleY = endScale
            }

            override fun onDrawerOpened(drawerView: View) {
                LogUtils.eTag("222", "MainActivity.onDrawerOpened")
            }

            override fun onDrawerClosed(drawerView: View) {
                LogUtils.eTag("222", "MainActivity.onDrawerClosed")
            }

            override fun onDrawerStateChanged(newState: Int) {
                LogUtils.eTag("222", "MainActivity.onDrawerStateChanged")
            }
        }
//        //ActionBarDrawerToggle是DrawerListener子类
        drawerLayout.addDrawerListener(actionBarDrawerToggle)

        //启动默认导航图标和切换动画，即三条杠变箭头，若想自定义导航图标，需注释这里
//        actionBarDrawerToggle.syncState()
        //自定义图标，没有切换动画，动画需自己实现
        actionBarDrawerToggle.isDrawerIndicatorEnabled = false
        val bitmapDrawable = BitmapDrawable(
            resources,
            Bitmap.createScaledBitmap(
                ImageUtils.getBitmap(R.drawable.menu),
                ConvertUtils.dp2px(20.0f),
                ConvertUtils.dp2px(20.0f),
                true
            )
        )
        actionBarDrawerToggle.setHomeAsUpIndicator(bitmapDrawable)
        actionBarDrawerToggle.setToolbarNavigationClickListener {
            drawerLayout.open()
        }

//        BarUtils.addMarginTopEqualStatusBarHeight(launcherMainToolbar)

//        drawerLayout.addDrawerListener(object :DrawerLayout.DrawerListener{
//            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
//                LogUtils.eTag("222", "MainActivity.onDrawerSlide")
//                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
//                //获取抽屉的view
//
//                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
//                //获取抽屉的view
//                val mContent = drawerLayout.getChildAt(0)
//                val scale = 1 - slideOffset
//                val endScale = 0.8f + scale * 0.2f
//                val startScale = 1 - 0.3f * scale
//
//                //设置左边菜单滑动后的占据屏幕大小
//
//                //设置左边菜单滑动后的占据屏幕大小
//                drawerView.scaleX = startScale
//                drawerView.scaleY = startScale
//                //设置菜单透明度
//                //设置菜单透明度
//                drawerView.alpha = 0.6f + 0.4f * (1 - scale)
//
//                //设置内容界面水平和垂直方向偏转量
//                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
//
//                //设置内容界面水平和垂直方向偏转量
//                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
//                mContent.translationX = drawerView.measuredWidth * (1 - scale)
//                //设置内容界面操作无效（比如有button就会点击无效）
//                //设置内容界面操作无效（比如有button就会点击无效）
//                mContent.invalidate()
//                //设置右边菜单滑动后的占据屏幕大小
//                //设置右边菜单滑动后的占据屏幕大小
//                mContent.scaleX = endScale
//                mContent.scaleY = endScale
//            }
//
//            override fun onDrawerOpened(drawerView: View) {
//
//            }
//
//            override fun onDrawerClosed(drawerView: View) {
//
//            }
//
//            override fun onDrawerStateChanged(newState: Int) {
//
//            }
//
//        })

//        iv_main_activity_menu.setOnClickListener{
//            drawerLayout.open()
//        }
    }

    private fun initView() {
        switchFragment(0)

        bottom_navigation_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> switchFragment(0)
                R.id.blog -> switchFragment(1)
                R.id.search -> switchFragment(2)
                R.id.project -> switchFragment(3)
                R.id.profile -> switchFragment(4)
            }
            true
        }
    }

    private fun switchFragment(i: Int) {
        hideFragment(mainFragment)
        hideFragment(blogFragment)
        hideFragment(searchFragment)
        hideFragment(projectFragment)
        hideFragment(profileFragment)
        when (i) {
            0 -> showFragment(R.id.fragment_group, mainFragment)
            1 -> showFragment(R.id.fragment_group, blogFragment)
            2 -> showFragment(R.id.fragment_group, searchFragment)
            3 -> showFragment(R.id.fragment_group, projectFragment)
            4 -> showFragment(R.id.fragment_group, profileFragment)
        }
    }

    private fun showFragment(@IdRes containerViewId: Int, fragment: Fragment?) {
        if (fragment == null) {
            return
        }
        val beginTransaction = supportFragmentManager.beginTransaction()
        if (!fragment.isAdded) {
            beginTransaction.add(containerViewId, fragment)
        }
        beginTransaction.show(fragment).commit()
    }

    private fun hideFragment(fragment: Fragment?) {
        if (fragment != null && fragment.isAdded && !fragment.isHidden) {
            supportFragmentManager.beginTransaction().hide(fragment).commit()
        }
    }
}