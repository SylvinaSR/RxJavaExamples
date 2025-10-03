package com.sylvieprojects.rxjavaapp.view.screens.rxBus

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.reactivex.rxjava3.disposables.CompositeDisposable

@Preview(showSystemUi = true)
@Composable
fun RxBusScreen() {

    val compositeDisposable = remember { CompositeDisposable() }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 150.dp)) {
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            RXBusExample.Companion.getInstance().setEvents("Hi! I'm the Bus")
        }) {
            Text("Click")
        }
        MyFragment()
    }

    LaunchedEffect(Unit) {
        makingObservable(compositeDisposable)
    }

    DisposableEffect(Unit) {
        onDispose {
            compositeDisposable.clear()
        }
    }
}

@Composable
fun MyFragment() {

    val compositeDisposable = remember { CompositeDisposable() }
    val obs = RXBusExample.getInstance().getEvents()
    var busText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        compositeDisposable.add(
            obs.subscribe { e ->
                busText = e.toString()
            }
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            compositeDisposable.clear()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(top = 20.dp)
            .background(Color.Cyan)

    ) {
        Text(modifier = Modifier.fillMaxSize(), text = busText)
    }
}

private fun makingObservable(compositeDisposable: CompositeDisposable) {
    val observable = RXBusExample.getInstance().getEvents()
    compositeDisposable.add(
        observable.subscribe { e ->
            Log.d("RxBus", "RxBus: $e")
        }
    )
}
