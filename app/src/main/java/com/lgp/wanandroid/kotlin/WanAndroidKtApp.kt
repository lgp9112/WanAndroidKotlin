package com.lgp.wanandroid.kotlin

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.LogUtils.IFormatter
import com.lgp.wanandroid.kotlin.ktx.lifecycle.KtxAppLifeObserver
import com.lgp.wanandroid.kotlin.ktx.lifecycle.KtxLifeCycleCallBack
import java.util.*


/**
 * 创建者     罗国鹏
 * 创建时间   2020/12/1 16:14
 * 描述
 * 更新者     $
 * 更新时间   $
 * 更新描述
 */
class WanAndroidKtApp : Application() {
    private var isDebug: Boolean? = null

    override fun onCreate() {
        super.onCreate()

        initLog()

        registerActivityLifecycleCallbacks(KtxLifeCycleCallBack())
        ProcessLifecycleOwner.get().lifecycle.addObserver(KtxAppLifeObserver())
    }

    private fun initLog() {
        val config = LogUtils.getConfig()
            .setLogSwitch(isDebug()) // 设置 log 总开关，包括输出到控制台和文件，默认开
            .setConsoleSwitch(isDebug()) // 设置是否输出到控制台开关，默认开
            .setGlobalTag(null) // 设置 log 全局标签，默认为空
            // 当全局标签不为空时，我们输出的 log 全部为该 tag，
            // 为空时，如果传入的 tag 为空那就显示类名，否则显示 tag
            .setLogHeadSwitch(false) // 设置 log 头信息开关，默认为开
            .setLog2FileSwitch(false) // 打印 log 时是否存到文件的开关，默认关
            .setDir("") // 当自定义路径为空时，写入应用的/cache/log/目录中
            .setFilePrefix("") // 当文件前缀为空时，默认为"util"，即写入文件为"util-yyyy-MM-dd$fileExtension"
            .setFileExtension(".log") // 设置日志文件后缀
            .setBorderSwitch(false) // 输出日志是否带边框开关，默认开
            .setSingleTagSwitch(true) // 一条日志仅输出一条，默认开，为美化 AS 3.1 的 Logcat
            .setConsoleFilter(LogUtils.V) // log 的控制台过滤器，和 logcat 过滤器同理，默认 Verbose
            .setFileFilter(LogUtils.E) // log 文件过滤器，和 logcat 过滤器同理，默认 Verbose
            .setStackDeep(1) // log 栈深度，默认为 1
            .setStackOffset(0) // 设置栈偏移，比如二次封装的话就需要设置，默认为 0
            .setSaveDays(3) // 设置日志可保留天数，默认为 -1 表示无限时长
            // 新增 ArrayList 格式化器，默认已支持 Array, Throwable, Bundle, Intent 的格式化输出
            .addFormatter(object : IFormatter<ArrayList<*>>() {
                override fun format(arrayList: ArrayList<*>): String {
                    return "LogUtils Formatter ArrayList { $arrayList }"
                }
            })
            .setFileWriter(null)
        LogUtils.i(config.toString())
    }

    private fun isDebug(): Boolean {
        if (isDebug == null) isDebug = AppUtils.isAppDebug()
        return isDebug as Boolean
    }
}