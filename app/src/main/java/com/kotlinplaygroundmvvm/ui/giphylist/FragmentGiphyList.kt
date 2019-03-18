package com.kotlinplaygroundmvvm.ui.giphylist

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kotlinplaygroundmvvm.R
import com.kotlinplaygroundmvvm.data.model.GiphyData
import com.kotlinplaygroundmvvm.data.source.GiphyDataSourceImpl
import com.kotlinplaygroundmvvm.ui.detail.FragmentGiphyDetail
import com.kotlinplaygroundmvvm.ui.util.RxBus
import com.kotlinplaygroundmvvm.ui.util.Schedulers.SchedulerProviderImpl
import com.kotlinplaygroundmvvm.ui.util.viewmodelfactory.GiphyViewModelFactory
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_giphy_list.*
import java.util.*
import java.util.concurrent.TimeUnit

class FragmentGiphyList: Fragment() {

    private lateinit var giphyListViewModel: GiphyListViewModel
    private lateinit var giphyListAdapter: GiphyListAdapter
    private lateinit var mPresenter: GiphyListPresenter
    private lateinit var publishSubject: PublishSubject<String>

    companion object {
        fun newInstance(): FragmentGiphyList {
            val f = FragmentGiphyList()
            val args = Bundle()
            f.arguments = args
            return f
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_giphy_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        giphyListViewModel = ViewModelProviders.of(this, GiphyViewModelFactory()).get(GiphyListViewModel::class.java)
        giphyListAdapter = GiphyListAdapter(ArrayList(0))
        giphyListAdapter.onItemClickListener = object: GiphyListAdapter.OnGiphyClickListener{
            override fun onGiphyClicked(giphyData: GiphyData) {
                RxBus.send(giphyData)
                val fm = fragmentManager
                fm!!.beginTransaction().add(
                    R.id.fragment_container,
                    FragmentGiphyDetail.newInstance(giphyData.id)
                )
                    .addToBackStack(null)
                    .commit()
            }
        }

        fragment_giphy_list_rv.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = giphyListAdapter
        }

        setUpSearchPublishSubject()
        setupLiveDataObservers()
        fragment_giphy_list_searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(s: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String?): Boolean {
                if (s.isNullOrEmpty()) {
                    giphyListViewModel.getTrendingGiphyList(0)
                } else {
                    publishSubject.onNext(s) // emit when text changes
                }
                return false
            }
        })
        initData()
    }

    private fun setUpSearchPublishSubject() {
        publishSubject = PublishSubject.create()
        publishSubject.debounce (400, TimeUnit.MILLISECONDS)
            .subscribeOn(SchedulerProviderImpl().io())
            .observeOn(SchedulerProviderImpl().main())
            .doOnNext {showProgress()}
            .subscribe(object: Observer<String>{
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(s: String) {
                    list_progress.visibility = View.GONE
                    giphyListViewModel.searchGiphy(s, 0)
                }

                override fun onError(e: Throwable) {

                }
            })
    }

    fun showProgress(){
        list_progress.visibility = View.VISIBLE
    }

    private fun initData() {
        giphyListViewModel.getTrendingGiphyList(0)
    }

    private fun setupLiveDataObservers() {
        giphyListViewModel.getGiphyListLiveData().observe(this,
            android.arch.lifecycle.Observer { it?.let { giphyListAdapter.replace(it) } })
    }
}