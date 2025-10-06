package com.sylvieprojects.rxjavaapp.view.screens.coldAndHot

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

@Composable
fun ColdAndHotScreen() {
    LaunchedEffect(Unit) {
        coldObservable()
    }
}

private fun coldObservable() {
    Log.d("Observable", "----------Cold----------")
    val cold = Observable.interval(500, TimeUnit.MILLISECONDS).take(3)
    cold.subscribe(
        { e -> Log.d("Observable", "Subscriber 1: $e") }
    )
    try {
        Thread.sleep(2000)
    } catch (e: Exception) {

    }
    cold.subscribe(
        { e -> Log.d("Observable", "Subscriber 2: $e") }
    )
}
