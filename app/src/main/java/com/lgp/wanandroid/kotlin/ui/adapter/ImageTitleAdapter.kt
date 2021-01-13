package com.lgp.wanandroid.kotlin.ui.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide
import com.lgp.wanandroid.kotlin.R
import com.lgp.wanandroid.kotlin.repository.remote.bean.Banner
import com.youth.banner.adapter.BannerAdapter


/**
 * 创建者     罗国鹏
 * 创建时间   2021/1/8 16:32
 * 描述
 * 更新者     $
 * 更新时间   $
 * 更新描述
 */
class ImageTitleAdapter(datas: MutableList<Banner>?) :
    BannerAdapter<Banner, ImageTitleAdapter.ImageTitleHolder>(datas) {

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): ImageTitleHolder {
        return ImageTitleHolder(
            LayoutInflater.from(parent!!.context)
                .inflate(R.layout.banner_image_title, parent, false)
        )
    }

    override fun onBindView(holder: ImageTitleHolder, data: Banner, position: Int, size: Int) {
        Glide.with(holder.itemView)
            .load(data.imagePath)
            .placeholder(ColorDrawable(Color.BLACK))
            .into(holder.imageView)

        holder.title.text=data.title
    }

    class ImageTitleHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.image)
        var title: TextView = view.findViewById(R.id.bannerTitle)

    }
}