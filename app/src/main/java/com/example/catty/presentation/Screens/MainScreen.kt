package com.example.catty.presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catty.R
import com.example.catty.presentation.navigationScreen.SingUpScreen
import com.example.catty.presentation.navigationScreen.UsersScreen
import com.example.catty.utils.Screen
import com.example.catty.viewModels.UserViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val userViewModel = hiltViewModel<UserViewModel>()
    val userListState = userViewModel.userList.collectAsState().value
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Box(modifier = Modifier.padding(it)) {
            NavHost(navController = navController, startDestination = Screen.Users.rout) {
                composable(Screen.Users.rout) {
                    UsersScreen(userListState, userViewModel::onEvent)
                }
                composable(Screen.SingUp.rout) {
                    SingUpScreen()
                }
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(
        BottomItem.Users,
        BottomItem.SingUp
    )
    val selected = rememberSaveable {
        mutableIntStateOf(0)
    }
    NavigationBar(modifier = Modifier.background(Color(0xFFF8F8F8))) {
        items.forEachIndexed { index, bottomItem ->
            NavigationBarItem(
                selected = selected.intValue == index,
                onClick = {
                    selected.intValue = index
                    navController.popBackStack()
                    navController.navigate(bottomItem.rout)

                },
                icon = {
                    Row {
                        Icon(
                            painter = painterResource(id = bottomItem.icon),
                            contentDescription = bottomItem.title
                        )
                        Text(modifier = Modifier.padding(start = 6.dp), text = bottomItem.title)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF00BDD3),
                    unselectedIconColor = Color(0, 0, 0, 85),
                    selectedTextColor = Color(0xFF00BDD3),
                    unselectedTextColor = Color(0, 0, 0, 85)
                )
            )
        }
    }
}

sealed class BottomItem(val title: String, val icon: Int, val rout: String) {
    object Users : BottomItem("Users", R.drawable.peoples, Screen.Users.rout)
    object SingUp : BottomItem("Sing Up", R.drawable.add_persone, Screen.SingUp.rout)
}