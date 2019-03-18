package com.kotlinplaygroundmvvm.ui.util

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

object RxBus {
    private val publishSubject = BehaviorSubject.create<Any>()

    fun <T> register(eventClass: Class<T>): Observable<T> {
        return publishSubject
            .filter { o -> o.javaClass == eventClass }
            .map { o -> o as T }
    }

    fun send(o: Any) {
        publishSubject.onNext(o)
    }
}