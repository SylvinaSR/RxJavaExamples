package com.sylvieprojects.rxjavaapp.view.screens.operators

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers

/*Operadores que transforman observables*/

@Composable
fun OperadoresTOScreen(modifier: Modifier = Modifier) {
    LaunchedEffect(Unit) {
        testWindow()
    }
}

private fun testWindow() {
    Log.d("OSTO", "----------Window--------")
    Observable.range(1, 24)
        .window(3)
        .subscribe { windowObservable ->
            Log.d("OSTO", "OnNext Window → Siguiente ventana")

            windowObservable.subscribe { item ->
                Log.d("OSTO", "OnNext Inside Window → item en ventana: $item")
            }
        }
}

private fun testScan() {
    Log.d("OSTO", "----------Scan--------")
    Observable.just(1, 2, 3, 4, 5, 6, 7)
        .scan(object : BiFunction<Int, Int, Int> {
            override fun apply(t1: Int, t2: Int): Int {
                return t1 + t2
            }
        })
        .subscribe(
            { t -> Log.d("OSTO", "Scan onNext: $t") }
        )
}

private fun testGroupBy() {
    Log.d("OSTO", "----------GroupBy--------")
    val observableNumber = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val groupObservable = observableNumber.groupBy { number ->
        if (number % 2 == 0) "PAR" else "IMPAR"
    }
    groupObservable.subscribe(
        { groupedObservable ->
            groupedObservable.subscribe { t ->
                if (groupedObservable.key.equals("PAR")) {
                    Log.d("OSTO", "GroupBy onNext: $t es par")
                } else {
                    Log.d("OSTO", "GroupBy onNext: $t es impar")
                }
            }
        }
    )
}

private fun testFlatMap() {
    Log.d("OSTO", "----------FlatMap--------")
    Observable
        .just("item2")
        .flatMap { s ->
            Log.d("OSTO", "Inside flatMap")
            Observable.just("$s 1 ", "$s 2 ", "$s 3 ")
        }
        .subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: String) {
                Log.d("OSTO", "FlatMap onNext: $t")
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }

        })

}

private fun testMap() {
    Log.d("OSTO", "----------Map--------")
    val list = EmpleadoDummyData.listEmpleados
    Observable.fromArray(list)
        .map { listEmpleado ->
            listEmpleado.map { it.name }
        }

        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            object : Observer<List<String>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: List<String>) {
                    Log.d("OSTO", "Map onNext: $t")
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                }

            }
        )
}

private fun testBuffer() {
    Log.d("OSTO", "----------Buffer--------")
    val integerObservable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
    integerObservable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .buffer(3)
        .subscribe(
            object : Observer<List<Int>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: List<Int>) {
                    Log.d("OSTO", "Buffer onNext forEach: $t")
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                }

            }
        )
}
