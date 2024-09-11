package com.example.catty.presentation.navigationScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.catty.personList.data.repository.UserListEvents
import com.example.catty.presentation.Screens.EmptyListScreen
import com.example.catty.presentation.Screens.component.UserCard
import com.example.catty.presentation.model.UserListState

@Composable
fun UsersScreen(userListState: UserListState, onEvents: (UserListEvents) -> Unit) {
    if (userListState.userList.isEmpty()) {
        EmptyListScreen()
    }
    else{
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(userListState.userList.size){ index ->
                UserCard(userListState.userList[index])
                if(index >= userListState.userList.size-1 && !userListState.isLoading){
                    onEvents(UserListEvents.UpdateData)
                }
            }
        }
    }
}