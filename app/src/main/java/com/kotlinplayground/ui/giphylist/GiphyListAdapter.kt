package com.kotlinplayground.ui.giphylist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kotlinplayground.R
import com.kotlinplayground.data.model.GiphyData
import com.kotlinplayground.ui.util.BaseHolder
import com.kotlinplayground.ui.util.RecyclerBaseAdapter

class GiphyListAdapter(items: MutableList<GiphyData>) : RecyclerBaseAdapter<GiphyData, GiphyListAdapter.OnGiphyClickListener>(items) {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseHolder<GiphyData, OnGiphyClickListener> {
        val layoutInflater: LayoutInflater = LayoutInflater.from(viewGroup.context)
        val v = layoutInflater.inflate(R.layout.giphy_list_item, viewGroup, false)
        return GiphyListViewHolder(v)
    }

    interface OnGiphyClickListener: RecyclerBaseAdapter.BaseRecyclerListener {
        fun onGiphyClicked(giphyData: GiphyData?)
    }
}

