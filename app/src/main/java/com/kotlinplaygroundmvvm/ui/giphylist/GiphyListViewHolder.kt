package com.kotlinplaygroundmvvm.ui.giphylist

import kotlinx.android.synthetic.main.giphy_list_item.view.*
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.kotlinplaygroundmvvm.R
import com.kotlinplaygroundmvvm.data.model.giphy.GiphyObject
import com.kotlinplaygroundmvvm.ui.util.BaseHolder

class GiphyListViewHolder(itemView: View) : BaseHolder<GiphyObject, GiphyListAdapter.OnGiphyClickListener>(itemView), View.OnClickListener {

    lateinit var giphyObject: GiphyObject
    init {
        itemView.setOnClickListener(this)
    }

    override fun bind(giphyObject: GiphyObject) {
        this.giphyObject = giphyObject
        Glide.with(itemView.context)
            .load(giphyObject.images.downsized?.url)
            .apply(
                RequestOptions().centerCrop().transform(RoundedCorners(16))
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .placeholder(R.color.grey_200))
            .into(itemView.giphy_list_item_preview)
    }

    override fun onClick(p0: View?) {
        listener?.onGiphyClicked(giphyObject)
    }
}