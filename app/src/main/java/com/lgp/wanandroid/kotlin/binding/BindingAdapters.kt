package com.lgp.wanandroid.kotlin.binding

import android.graphics.drawable.Drawable
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.lgp.wanandroid.kotlin.repository.remote.bean.Tag

/**
 * Created by luyao
 * on 2019/10/15 16:15
 */

@BindingAdapter("bindingVisible")
fun bindVisible(view: View, visible: Boolean) {
    view.visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("bindingTag")
fun bindingTag(view: TextView,  tags:MutableList<Tag>) {
    if (tags.size>0){
        view.visibility =View.VISIBLE
        view.text=tags[0].name
    }else{
        view.visibility =View.GONE
    }
}

@BindingAdapter(
        "imageUrl",
        "imagePlaceholder",
        "circleCropImage",
        "crossFadeImage",
        "overrideImageWidth",
        "overrideImageHeight",
        "imageLoadListener",
        requireAll = false
)
fun bindImage(
        imageView: ImageView,
        imageUrl: String?,
        placeholder: Int? = null,
        circleCrop: Boolean? = false,
        crossFade: Boolean? = false,
        overrideWidth: Int? = null,
        overrideHeight: Int? = null,
        listener: RequestListener<Drawable>?
) {
    if (imageUrl==null||imageUrl.isBlank()) {
        imageView.visibility=View.GONE
    }else{
        imageView.visibility=View.VISIBLE

        var request = Glide.with(imageView.context).load(imageUrl)
        if (placeholder != null) {
            request = request.placeholder(placeholder)
        }
        if (circleCrop == true) {
            request = request.circleCrop()
        }
        if (crossFade == true) {
            request = request.transition(DrawableTransitionOptions.withCrossFade())
        }
        if (overrideWidth != null && overrideHeight != null) {
            request = request.override(overrideWidth, overrideHeight)
        }
        if (listener != null) {
            request = request.listener(listener)
        }
        request.into(imageView)
    }
}
