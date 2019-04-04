package com.kotlinplaygroundmvvm.data.model.giphy

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Ignore

data class Images(
    @Ignore var original: Original? = null,
    @Ignore var fixedHeightSmall: FixedHeightSmall? = null,
    @Ignore var fixedWidth: FixedWidth? = null,
    @Ignore var downsizedStill: DownsizedStill? = null,
    @Ignore var downsizedLarge: DownsizedLarge? = null,
    @Ignore var fixedHeightDownsampled: FixedHeightDownsampled? = null,
    @Ignore var fixedHeight: FixedHeight? = null,
    @Ignore var fixedWidthDownsampled: FixedWidthDownsampled? = null,
    @Ignore var fixedHeightSmallStill: FixedHeightSmallStill? = null,
    @Ignore var fixedWidthSmall: FixedWidthSmall? = null,
    @Ignore var fixedHeightStill: FixedHeightStill? = null,
    @Ignore var fixedWidthSmallStill: FixedWidthSmallStill? = null,
    @Ignore var fixedWidthStill: FixedWidthStill? = null,
    @Embedded var downsized: Downsized? = null,
    @Ignore var originalStill: OriginalStill? = null)
