package com.kotlinplaygroundmvvm.ui.giphylist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kotlinplaygroundmvvm.R
import com.kotlinplaygroundmvvm.data.model.giphy.GiphyObject
import com.kotlinplaygroundmvvm.ui.util.BaseHolder
import com.kotlinplaygroundmvvm.ui.util.RecyclerBaseAdapter

class GiphyListAdapter(items: MutableList<GiphyObject>) : RecyclerBaseAdapter<GiphyObject, GiphyListAdapter.OnGiphyClickListener>(items) {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseHolder<GiphyObject, OnGiphyClickListener> {
        val layoutInflater: LayoutInflater = LayoutInflater.from(viewGroup.context)
        val v = layoutInflater.inflate(R.layout.giphy_list_item, viewGroup, false)
        return GiphyListViewHolder(v)
    }

    interface OnGiphyClickListener: RecyclerBaseAdapter.BaseRecyclerListener {
        fun onGiphyClicked(giphyObject: GiphyObject)
    }
}

