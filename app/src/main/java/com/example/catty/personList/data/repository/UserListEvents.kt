package com.example.catty.personList.data.repository

sealed interface UserListEvents {
    object UpdateData : UserListEvents
}