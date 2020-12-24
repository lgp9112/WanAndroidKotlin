package com.lgp.wanandroid.kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lgp.wanandroid.kotlin.R
import java.util.ArrayList


/**
 * 创建者     罗国鹏
 * 创建时间   2020/12/9 16:01
 * 描述
 * 更新者     $
 * 更新时间   $
 * 更新描述
 */
class VersionAdapter: RecyclerView.Adapter<VersionAdapter.ViewHolder>() {

    private val mList: MutableList<String> = ArrayList()

    fun notifyDataSetChanged(list:List<String>?){
        mList.clear()
        if (list!=null){
            mList.addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.item_version, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]
        holder.textView.text=item
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView

        init {
            textView = itemView.findViewById<View>(R.id.textView) as TextView
        }
    }
}