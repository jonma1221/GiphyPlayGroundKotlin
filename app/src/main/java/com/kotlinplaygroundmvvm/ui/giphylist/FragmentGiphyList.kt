package com.kotlinplaygroundmvvm.ui.giphylist

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.SearchView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlinplaygroundmvvm.R
import com.kotlinplaygroundmvvm.data.model.giphy.GiphyObject
import com.kotlinplaygroundmvvm.di.ViewModelFactory
import com.kotlinplaygroundmvvm.ui.detail.FragmentGiphyDetail
import com.kotlinplaygroundmvvm.ui.giphylist.deprecated.GiphyListPresenter
import com.kotlinplaygroundmvvm.ui.util.App
import com.kotlinplaygroundmvvm.ui.util.RxBus
import com.kotlinplaygroundmvvm.ui.util.Schedulers.SchedulerProviderImpl
import com.kotlinplaygroundmvvm.ui.util.viewmodelfactory.GiphyViewModelFactory
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_giphy_list.*
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FragmentGiphyList: Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var giphyListViewModel: GiphyListViewModel
    private lateinit var giphyListAdapter: GiphyListAdapter
    private lateinit var publishSubject: PublishSubject<String>
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

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

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        App.applicationContext().component.inject(this)
        giphyListViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(GiphyListViewModel::class.java)

        giphyListAdapter = GiphyListAdapter(ArrayList(0))
        giphyListAdapter.onItemClickListener = object: GiphyListAdapter.OnGiphyClickListener{
            override fun onGiphyClicked(giphyObject: GiphyObject) {
                RxBus.send(giphyObject)
                val fm: FragmentManager? = fragmentManager
                fm?.beginTransaction()?.add(
                    R.id.fragment_container,
                    FragmentGiphyDetail.newInstance(giphyObject.id)
                )?.addToBackStack(null)?.commit()
            }
        }

        fragment_giphy_list_rv.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = giphyListAdapter
        }

        fragment_giphy_list_searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(s: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String?): Boolean {
                if (s.isNullOrEmpty()) {
//                    giphyListViewModel.getTrendingGiphyList(0)
                } else {
                    publishSubject.onNext(s) // emit when text changes
                }
                return false
            }
        })

        setUpSearchPublishSubject()
        observeGiphyTrending()
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
                    compositeDisposable.addAll(d)
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

    //todo - figure out how to resolve the import conflicts
    private fun observeGiphyTrending() {
        giphyListViewModel.getGiphyListLiveData().observe(this,
            android.arch.lifecycle.Observer { it?.let {
                Log.d("here", "${it.size}")
                giphyListAdapter.replace(it) } })
    }
}