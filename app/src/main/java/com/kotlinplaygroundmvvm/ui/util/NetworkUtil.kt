package com.kotlinplaygroundmvvm.ui.util

import android.content.Context
import android.net.ConnectivityManager

fun hasNetwork(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val isNetworkAvailable = cm.activeNetworkInfo != null
    return isNetworkAvailable && cm.activeNetworkInfo.isConnected
}