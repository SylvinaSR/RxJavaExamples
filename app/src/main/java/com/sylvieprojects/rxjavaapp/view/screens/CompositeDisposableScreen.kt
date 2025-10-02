package com.sylvieprojects.rxjavaapp.view.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

@Composable
fun CompositeDisposableScreen(modifier: Modifier = Modifier) {

    val compositeDisposable = remember { CompositeDisposable() }

    LaunchedEffect(Unit) {
        makingObservable(compositeDisposable)
    }

    DisposableEffect(Unit) {
        onDispose {
            Log.d("CD", "onDispose llamado — limpiando CompositeDisposable")
            compositeDisposable.clear()
        }
    }

    Column(modifier = modifier.fillMaxSize()) {

    }
}

private fun makingObservable(compositeDisposable: CompositeDisposable) {
    val observableNumbers = Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
    val observableLetters = Observable.just("one", "two", "three", "four", "five", "six")

    val numbersDisposableObserver = object : DisposableObserver<String>() {
        override fun onNext(t: String) {
            Log.d("CD", "Number onNext: $t, hilo: ${Thread.currentThread().name}")
        }

        override fun onError(e: Throwable) {
            Log.d("CD", "Number onError: ${e.message}, hilo: ${Thread.currentThread().name}")
        }

        override fun onComplete() {
            Log.d("CD", "Number onComplete, hilo: ${Thread.currentThread().name}")
        }
    }

    val lettersDisposableObserver = object : DisposableObserver<String>() {
        override fun onNext(t: String) {
            Log.d("CD", "Letter onNext: $t, hilo: ${Thread.currentThread().name}")
        }

        override fun onError(e: Throwable) {
            Log.d("CD", "Letter onError: ${e.message}, hilo: ${Thread.currentThread().name}")
        }

        override fun onComplete() {
            Log.d("CD", "Letter onComplete, hilo: ${Thread.currentThread().name}")
        }
    }

    compositeDisposable.add(
        observableNumbers
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(numbersDisposableObserver)
    )

    compositeDisposable.add(
        observableLetters
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(lettersDisposableObserver)
    )
}