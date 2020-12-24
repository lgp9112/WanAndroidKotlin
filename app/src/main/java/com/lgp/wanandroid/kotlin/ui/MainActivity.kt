package com.lgp.wanandroid.kotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.lgp.wanandroid.kotlin.R
import com.lgp.wanandroid.kotlin.transparentStatusBar4Drawer
import com.lgp.wanandroid.kotlin.ui.adapter.VersionAdapter
import com.lgp.wanandroid.kotlin.ui.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val mainPagerFragment: MainPagerFragment by lazy { MainPagerFragment() }
    private val knowledgeHierarchyFragment: KnowledgeHierarchyFragment by lazy { KnowledgeHierarchyFragment() }
    private val wxArticleFragment: WxArticleFragment by lazy { WxArticleFragment() }
    private val navigationFragment: NavigationFragment by lazy { NavigationFragment() }
    private val projectFragment: ProjectFragment by lazy { ProjectFragment() }

    private val versionAdapter: VersionAdapter by lazy { VersionAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //windowBackground启动白屏优化
        setTheme(R.style.Theme_WanAndroidKotlin)
//        BarUtils.transparentStatusBar(this)
        setContentView(R.layout.activity_main)

        transparentStatusBar4Drawer(this,drawerLayout)

        initView()
    }

    private fun initView() {
        //绑定BottomNavigationView和NavHostFragment容器中的fragment
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        val navController = host.navController
        NavigationUI.setupWithNavController(bottom_navigation_view,navController)

    }
}