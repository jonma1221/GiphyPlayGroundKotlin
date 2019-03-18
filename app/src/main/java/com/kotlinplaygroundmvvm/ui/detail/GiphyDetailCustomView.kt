package com.kotlinplaygroundmvvm.ui.detail

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.kotlinplaygroundmvvm.R
import com.kotlinplaygroundmvvm.data.model.GiphyData
import kotlinx.android.synthetic.main.giphy_detail_layout.view.*

class GiphyDetailCustomView: ConstraintLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var mGiphyData: GiphyData? = null

    init {
        View.inflate(context, R.layout.giphy_detail_layout, this)
    }

    fun loadImage(url: String){
        Glide.with(context)
            .load(url)
            .apply(
                RequestOptions().placeholder(R.color.grey_200)
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            )
            .into(giphy_detail_img)
    }
}