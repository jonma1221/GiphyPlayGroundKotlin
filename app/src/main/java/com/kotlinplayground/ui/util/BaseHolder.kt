package com.kotlinplayground.ui.util

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.ButterKnife

abstract class BaseHolder<T, L : RecyclerBaseAdapter.BaseRecyclerListener>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var context: Context
    var listener: L? = null

    init {
        ButterKnife.bind(this, itemView)
        context = itemView.context
    }

    abstract fun bind(item: T)
}