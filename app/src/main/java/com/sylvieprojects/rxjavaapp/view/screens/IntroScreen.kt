package com.sylvieprojects.rxjavaapp.view.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

@Composable
fun IntroScreen(modifier: Modifier = Modifier) {

    LaunchedEffect(Unit) {
        makingObservable()
    }

    Column(modifier = modifier.fillMaxSize()) {

    }
}

private fun makingObservable() {
    val numerosObservable: Observable<String> =
        Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
    val observer: Observer<String> = object : Observer<String>{
        override fun onSubscribe(d: Disposable) {
            Log.d("BasicScreenButtons", "onSubscribe hilo: ${Thread.currentThread().name}")
        }

        override fun onNext(t: String) {
            Log.d("BasicScreenButtons", "onNext numero: $t, hilo: ${Thread.currentThread().name}")
        }

        override fun onError(e: Throwable) {
            Log.d("BasicScreenButtons", "onError hilo: ${Thread.currentThread().name}")
        }

        override fun onComplete() {
            Log.d("BasicScreenButtons", "onComplete hilo: ${Thread.currentThread().name}")
        }

    }
    numerosObservable
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe(observer)
}

@Preview(showSystemUi = true)
@Composable
fun PreviewBasicScreenButton() {
    IntroScreen(modifier = Modifier.padding(top = 50.dp))
}