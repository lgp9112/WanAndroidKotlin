package com.lgp.wanandroid.kotlin.repository.remote.api

import com.blankj.utilcode.util.LogUtils
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.lgp.wanandroid.kotlin.BuildConfig
import com.lgp.wanandroid.kotlin.WanKtApp
import com.lgp.wanandroid.kotlin.repository.remote.constant.HttpConstant
import com.lgp.wanandroid.kotlin.repository.remote.interceptor.CacheInterceptor
import com.lgp.wanandroid.kotlin.repository.remote.interceptor.HeaderInterceptor
import com.lgp.wanandroid.kotlin.repository.remote.interceptor.SaveCookieInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


/**
 * 创建者     罗国鹏
 * 创建时间   2020/12/30 18:17
 * 描述
 * 更新者     $
 * 更新时间   $
 * 更新描述
 */
object RetrofitHelper {

    val service: WanService by lazy { getRetrofit(WanService.BASE_URL).create(WanService::class.java) }

    private val cookieJar by lazy { PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(WanKtApp.CONTEXT)) }

    private fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())//
            //.addConverterFactory(MoshiConverterFactory.create())//kotlin中替代GsonConverterFactory
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            //.addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())//配和Deferred使用
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {

        val builder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor {
            LogUtils.eTag(
                "111",
                "RetrofitHelper.log==$it"
            )
        }
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        //设置 请求的缓存的大小跟位置
        val cacheFile = File(WanKtApp.CONTEXT.cacheDir, "cache")
        val cache = Cache(cacheFile, HttpConstant.MAX_CACHE_SIZE)

        builder.run {
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(HeaderInterceptor())
            addInterceptor(CacheInterceptor())
            //addInterceptor(SaveCookieInterceptor())//保存cookie
            cookieJar(cookieJar)//保存cookie
            // cookieJar(CookieManager())
            cache(cache)  //添加缓存
            connectTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true) // 错误重连
        }
        return builder.build()
    }
}