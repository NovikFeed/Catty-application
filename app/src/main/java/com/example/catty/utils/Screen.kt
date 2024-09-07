package com.example.catty.utils

sealed class Screen(val rout: String) {
    object Loading : Screen("loading")
    object Main : Screen("main")
    object Users : Screen("users")
    object SingUp : Screen("singUp")
    object Disconnect : Screen("disconnect")
}