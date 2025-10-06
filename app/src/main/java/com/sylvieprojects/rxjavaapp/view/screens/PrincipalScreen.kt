package com.sylvieprojects.rxjavaapp.view.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PrincipalScreen(
    navigateToIntro: () -> Unit,
    navigateToDisposable: () -> Unit,
    navigateToCompositeDispose: () -> Unit,
    navigateToOperadores: () -> Unit,
    navigateToOperadoresTO: () -> Unit,
    navigateToOperadoresSEIO: () -> Unit,
    navigateToOperadoresMOSO: () -> Unit,
    navigateToOperadoresBOCO: () -> Unit,
    navigateTypeObservables: () -> Unit,
    navigateToSubjects: () -> Unit,
    navigateToRxBus: () -> Unit,
    navigateToBackPressure: () -> Unit,
    navigateToColdAndHot: () -> Unit
) {
    Scaffold { innerPadding ->
        LazyColumn(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
            item {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp), onClick = {
                        navigateToIntro()
                    }) {
                    Text(text = "Introduccion")
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp), onClick = {
                        navigateToDisposable()
                    }) {
                    Text(text = "Disposable")
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        navigateToCompositeDispose()
                    }) {
                    Text(text = "Composite Disposable")
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        navigateToOperadores()
                    }) {
                    Text(text = "Operadores")
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        navigateToOperadoresTO()
                    }) {
                    Text(text = "Operadores Transform Observables")
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        navigateToOperadoresSEIO()
                    }) {
                    Text(text = "Operadores SEIO")
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        navigateToOperadoresMOSO()
                    }) {
                    Text(text = "Operadores MOSO")
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        navigateToOperadoresBOCO()
                    }) {
                    Text(text = "Operadores BOCO")
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        navigateTypeObservables()
                    }) {
                    Text(text = "Types Observables")
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        navigateToSubjects()
                    }) {
                    Text(text = "Subjects")
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        navigateToRxBus()
                    }) {
                    Text(text = "RxBus")
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        navigateToBackPressure()
                    }) {
                    Text(text = "BackPressure")
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        navigateToColdAndHot()
                    }) {
                    Text(text = "Cold and Hot")
                }
            }
        }
    }

}