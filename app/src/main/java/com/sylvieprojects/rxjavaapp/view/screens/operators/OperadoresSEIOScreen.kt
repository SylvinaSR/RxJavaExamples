package com.sylvieprojects.rxjavaapp.view.screens.operators

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

/*Operadores que selectivamente emiten items de un observable*/

@Composable
fun OperadoresSEIOScreen(modifier: Modifier = Modifier) {
    LaunchedEffect(Unit) {
        testTakeLast()
    }
}

private fun testTakeLast() {
    Log.d("OSEIO", "----------TakeLast----------")
    Observable.just(1,2,3,4,2,2,3,78,98,78)
        .takeLast(3)
        .subscribe { e ->
            Log.d("OSEIO", "TakeLast onNext: $e")
        }
}

private fun testTake() {
    Log.d("OSEIO", "----------Take----------")
    Observable.just(1,2,3,4,2,2,3,78,98,78)
        .take(3)
        .subscribe { e ->
            Log.d("OSEIO", "Take onNext: $e")
        }
}

private fun testSkipLast() {
    Log.d("OSEIO", "----------SkipLast----------")
    Observable.just(1,2,3,4,2,2,3,78,98,78)
        .skipLast(3)
        .subscribe { e ->
            Log.d("OSEIO", "SkipLast onNext: $e")
        }
}

private fun testSkip() {
    Log.d("OSEIO", "----------Skip----------")
    Observable.just(1,2,3,4,2,2,3,78,98,78)
        .skip(3)
        .subscribe { e ->
            Log.d("OSEIO", "Skip onNext: $e")
        }
}

private fun testSample() {
    Log.d("OSEIO", "----------Sample----------")
    Observable.interval(500, TimeUnit.MILLISECONDS)
        .take(10)
        .sample(2000, TimeUnit.MILLISECONDS)
        .subscribe { e ->
            Log.d("OSEIO", "Sample onNext: $e")
        }
}

private fun testLast() {
    Log.d("OSEIO", "----------Last----------")
    Observable.just(1,2,3,4,2,2,3,78,98,78)
        .last(0) //Default value
        .subscribe { firstNum ->
            Log.d("OSEIO", "Last onNext last number: $firstNum")
        }
}

private fun testIgnoreElement() {
    Log.d("OSEIO", "----------IgnoreElement----------")
    Observable.just(1,2,3,4,2,2,3,78,98,78)
        .ignoreElements()
        .subscribe(
            { Log.d("OSEIO", "IgnoreElement onComplete") },
            { error -> Log.e("OSEIO", "Error: $error") }
        )
}

private fun testFirst() {
    Log.d("OSEIO", "----------First----------")
    Observable.just(1,2,3,4,2,2,3,78,98,78)
        .first(0) //Default value
        .subscribe { firstNum ->
            Log.d("OSEIO", "elementAt onNext first number: $firstNum")
        }
}

private fun testFilter() {
    Log.d("OSEIO", "----------Filter----------")
    Observable.just(1,2,3,4,2,2,3,78,98,78)
        .filter { predicate ->
            predicate % 2 == 0
        }
        .subscribe { number ->
            Log.d("OSEIO", "elementAt onNext number $number is par")
        }
}

private fun testElementAt() {
    Log.d("OSEIO", "----------ElementAt----------")
    Observable.just(1,2,3,4,2,2,3,78,98,78)
        .elementAt(7)
        .subscribe { number ->
            Log.d("OSEIO", "elementAt onNext: $number")
        }
}

private fun testDistinct() {
    Log.d("OSEIO", "----------Distinct----------")
    Observable.just(1,2,3,4,2,2,3,78,98,78)
        .distinct()
        .subscribe { number ->
            Log.d("OSEIO", "Distinct onNext: $number")
        }
}
