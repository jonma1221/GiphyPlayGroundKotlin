package com.kotlinplaygroundmvvm.ui.util

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

abstract class RecyclerBaseAdapter<T, L : RecyclerBaseAdapter.BaseRecyclerListener>(val items: MutableList<T>) : RecyclerView.Adapter<BaseHolder<T, L>>() {

    fun replace(items: List<T>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun add(items: List<T>){
        this.items.addAll(items)
        val origSize = this.items.size
        notifyItemRangeInserted(origSize, this.items.size)
    }

    override abstract fun onCreateViewHolder(p0: ViewGroup, p1: Int): BaseHolder<T, L>

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(baseHolder: BaseHolder<T, L>, i: Int) {
        baseHolder.bind(items[i])
        baseHolder.listener = onItemClickListener
    }

    var onItemClickListener: L? = null

    interface BaseRecyclerListener
}