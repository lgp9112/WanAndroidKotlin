package com.lgp.wanandroid.kotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lgp.wanandroid.kotlin.R
import kotlinx.android.synthetic.main.activity_test.*
import java.lang.StringBuilder


/**
 * 创建者     罗国鹏
 * 创建时间   2020/12/25 16:42
 * 描述
 * 更新者     $
 * 更新时间   $
 * 更新描述
 */
class TestActivity  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val stringBuilder = StringBuilder()
        for (i in 0..100){
            stringBuilder.append("我是 $i \n")
        }

        textView.text = stringBuilder.toString()
    }
}