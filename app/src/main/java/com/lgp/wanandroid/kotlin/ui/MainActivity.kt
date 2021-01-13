package com.lgp.wanandroid.kotlin.ui

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.*
import com.lgp.wanandroid.kotlin.R
import com.lgp.wanandroid.kotlin.util.transparentStatusBar4Drawer
import com.lgp.wanandroid.kotlin.ui.adapter.VersionAdapter
import com.lgp.wanandroid.kotlin.ui.fragment.*
import com.lgp.wanandroid.kotlin.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    enum class Index {
        HOME,
        SQUARE,
        WECHAT,
        SYSTEM,
        PROJECT
    }

    private var index: Index? = null

    private val homeFragment: HomeFragment by lazy { HomeFragment() }
    private val squareFragment: SquareFragment by lazy { SquareFragment() }
    private val weChatFragment: WeChatFragment by lazy { WeChatFragment() }
    private val systemFragment: SystemFragment by lazy { SystemFragment() }
    private val projectFragment: ProjectFragment by lazy { ProjectFragment() }

    private val versionAdapter: VersionAdapter by lazy { VersionAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //windowBackground启动白屏优化
        setTheme(R.style.Theme_WanAndroidKotlin)
//        BarUtils.transparentStatusBar(this)
        setContentView(R.layout.activity_main)

        transparentStatusBar4Drawer(this, drawerLayout)

        BarUtils.setStatusBarColor(
            barStatusDrawerFakeStatusBar,
            resources.getColor(R.color.colorPrimary)
        )
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
        // actionBarDrawerToggle.syncState()
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
    }

    private fun initView() {
        switchFragment(Index.HOME)

        bottom_navigation_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> switchFragment(Index.HOME)
                R.id.action_square -> switchFragment(Index.SQUARE)
                R.id.action_wechat -> switchFragment(Index.WECHAT)
                R.id.action_system -> switchFragment(Index.SYSTEM)
                R.id.action_project -> switchFragment(Index.PROJECT)
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun switchFragment(i: Index) {
        index = i

        hideFragment(homeFragment)
        hideFragment(squareFragment)
        hideFragment(weChatFragment)
        hideFragment(systemFragment)
        hideFragment(projectFragment)
        when (i) {
            Index.HOME -> {
                appBarLayout.setExpanded(true)
                collapsingToolbarLayout.title = "首页"
                showFragment(R.id.fragment_group, homeFragment)
            }
            Index.SQUARE -> {
                appBarLayout.setExpanded(false)
                collapsingToolbarLayout.title = "广场"
                showFragment(R.id.fragment_group, squareFragment)
            }
            Index.WECHAT -> {
                appBarLayout.setExpanded(false)
                collapsingToolbarLayout.title = "公众号"
                showFragment(R.id.fragment_group, weChatFragment)
            }
            Index.SYSTEM -> {
                appBarLayout.setExpanded(false)
                collapsingToolbarLayout.title = "体系"
                showFragment(R.id.fragment_group, systemFragment)
            }
            Index.PROJECT -> {
                appBarLayout.setExpanded(false)
                collapsingToolbarLayout.title = "项目"
                showFragment(R.id.fragment_group, projectFragment)
            }
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