package com.kotlinplaygroundmvvm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.kotlinplaygroundmvvm.ui.giphylist.FragmentGiphyList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentGiphyList = FragmentGiphyList.newInstance()
        startFragment(R.id.fragment_container, fragmentGiphyList)
    }

    fun startFragment(resId: Int, f: Fragment){
        val fm = supportFragmentManager
        fm.beginTransaction()
            .add(resId, f)
            .addToBackStack(null)
            .commit()
    }
}
