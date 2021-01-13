package com.lgp.wanandroid.kotlin.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.lgp.wanandroid.kotlin.R
import com.lgp.wanandroid.kotlin.databinding.ItemArticleConstraintBinding
import com.lgp.wanandroid.kotlin.repository.remote.bean.Article


/**
 * 创建者     罗国鹏
 * 创建时间   2021/1/5 15:10
 * 描述
 * 更新者     $
 * 更新时间   $
 * 更新描述
 */
class HomeAdapter :
    BaseQuickAdapter<Article, BaseDataBindingHolder<ItemArticleConstraintBinding>>(R.layout.item_article_constraint),
    LoadMoreModule {

    override fun convert(
        holder: BaseDataBindingHolder<ItemArticleConstraintBinding>,
        item: Article
    ) {
        holder.dataBinding?.article = item
        holder.dataBinding?.executePendingBindings()

        val author = if (item.author.isNotBlank()) "作者：${item.author}" else "分享者：${item.shareUser}"
        holder.setText(R.id.tv_article_author, author)

        val chapterName = when {
            item.superChapterName.isNotEmpty() and item.chapterName.isNotEmpty() ->
                "${item.superChapterName} / ${item.chapterName}"
            item.superChapterName.isNotEmpty() -> item.superChapterName
            item.chapterName.isNotEmpty() -> item.chapterName
            else -> ""
        }

        holder.setText(R.id.tv_article_chapterName, chapterName)

        holder.setImageResource(
            R.id.iv_like,
            if (item.collect) R.drawable.ic_like else R.drawable.ic_like_not
        )
    }
}