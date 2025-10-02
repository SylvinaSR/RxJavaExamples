package com.sylvieprojects.rxjavaapp.view.screens.subjects

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.AsyncSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject

@Composable
fun SubjectsScreen(modifier: Modifier = Modifier) {
    LaunchedEffect(Unit) {
        testObserverAndObservable()
    }
}

private fun testObserverAndObservable() {
    Log.d("Subject", "----------Observer and Observable------------")
    val observable: Observable<String> = Observable.just("Sylvina", "Ivan")
    val replaySubject = ReplaySubject.create<String>()
    observable.subscribe(replaySubject)
    val observerOne = object : Observer<String>{
        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: String) {
            Log.d("Subject", "onNext Primer Observer: $t")
        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
            Log.d("Subject", "onComplete Primer Observer")
        }
    }
    val observerTwo = object : Observer<String>{
        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: String) {
            Log.d("Subject", "onNext Segundo Observer: $t")
        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
            Log.d("Subject", "onComplete Segundo Observer")
        }
    }
    replaySubject.subscribe(observerOne)
    replaySubject.subscribe(observerTwo)
}

private fun testAsyncSubject() {
    Log.d("Subject", "----------AsyncSubject------------")
    val asyncSubject = AsyncSubject.create<String>()
    val observerOne = object : Observer<String>{
        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: String) {
            Log.d("Subject", "onNext Primer Observer: $t")
        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
            Log.d("Subject", "onComplete Primer Observer")
        }
    }
    val observerTwo = object : Observer<String>{
        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: String) {
            Log.d("Subject", "onNext Segundo Observer: $t")
        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
            Log.d("Subject", "onComplete Segundo Observer")
        }
    }
    asyncSubject.subscribe(observerOne)
    asyncSubject.onNext("S")
    asyncSubject.onNext("y")
    asyncSubject.onNext("l")
    asyncSubject.onNext("v")
    asyncSubject.onNext("i")
    asyncSubject.onNext("n")
    asyncSubject.onNext("a")
    asyncSubject.onComplete()
    asyncSubject.subscribe(observerTwo)
}

private fun testReplaySubject() {
    Log.d("Subject", "----------ReplaySubject------------")
    val replaySubject = ReplaySubject.create<String>()
    val observerOne = object : Observer<String>{
        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: String) {
            Log.d("Subject", "onNext Primer Observer: $t")
        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
            Log.d("Subject", "onComplete Primer Observer")
        }
    }
    val observerTwo = object : Observer<String>{
        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: String) {
            Log.d("Subject", "onNext Segundo Observer: $t")
        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
            Log.d("Subject", "onComplete Segundo Observer")
        }
    }
    replaySubject.subscribe(observerOne)
    replaySubject.onNext("S")
    replaySubject.onNext("y")
    replaySubject.onNext("l")
    replaySubject.subscribe(observerTwo)
    replaySubject.onNext("v")
    replaySubject.onNext("i")
    replaySubject.onNext("n")
    replaySubject.onNext("a")
    replaySubject.onComplete()
}

private fun testPublishSubject() {
    Log.d("Subject", "----------PublishSubject------------")
    val publishSubject = PublishSubject.create<String>()
    val observerOne = object : Observer<String>{
        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: String) {
            Log.d("Subject", "onNext Primer Observer: $t")
        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
            Log.d("Subject", "onComplete Primer Observer")
        }
    }
    val observerTwo = object : Observer<String>{
        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: String) {
            Log.d("Subject", "onNext Segundo Observer: $t")
        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
            Log.d("Subject", "onComplete Segundo Observer")
        }
    }
    publishSubject.subscribe(observerOne)
    publishSubject.onNext("S")
    publishSubject.onNext("y")
    publishSubject.onNext("l")
    publishSubject.subscribe(observerTwo)
    publishSubject.onNext("v")
    publishSubject.onNext("i")
    publishSubject.onNext("n")
    publishSubject.onNext("a")
    publishSubject.onComplete()
}
