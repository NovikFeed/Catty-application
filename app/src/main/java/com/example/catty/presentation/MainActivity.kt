package com.example.catty.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catty.core.util.NetworkTracker
import com.example.catty.presentation.Screens.LoadingScreen
import com.example.catty.presentation.Screens.MainScreen
import com.example.catty.presentation.Screens.NotConnectedScreen
import com.example.catty.utils.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val networkTracker = NetworkTracker(this)
        setContent {
            val isConnected = networkTracker.isConnected.collectAsState()
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.Loading.rout) {
                composable(Screen.Loading.rout) { LoadingScreen(navController) }
                composable(Screen.Main.rout) {
                    if (isConnected.value) {
                        MainScreen()
                    } else {
                        NotConnectedScreen()
                    }
                }
            }
        }
    }
}
