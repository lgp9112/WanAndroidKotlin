package com.lgp.wanandroid.kotlin.util

import android.app.Activity
import android.os.Build
import androidx.drawerlayout.widget.DrawerLayout
import com.blankj.utilcode.util.BarUtils


/**
 * 创建者     罗国鹏
 * 创建时间   2020/12/23 23:32
 * 描述
 * 更新者     $
 * 更新时间   $
 * 更新描述
 */
fun transparentStatusBar4Drawer(
    activity: Activity,
    drawer: DrawerLayout,
) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return

    BarUtils.transparentStatusBar(activity)
    drawer.fitsSystemWindows = false
    var i = 0
    val count = drawer.childCount
    while (i < count) {
        drawer.getChildAt(i).fitsSystemWindows = false
        i++
    }
}