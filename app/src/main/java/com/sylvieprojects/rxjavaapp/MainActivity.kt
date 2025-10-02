package com.sylvieprojects.rxjavaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sylvieprojects.rxjavaapp.ui.theme.RxJavaAppTheme
import com.sylvieprojects.rxjavaapp.view.core.navigation.NavigationWrapper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RxJavaAppTheme {
                NavigationWrapper()
            }
        }
    }
}
