package com.example.catty.presentation.model

import com.example.catty.personList.domain.model.User

data class UserListState(
    val isLoading: Boolean = false,
    val count: Int = 6,
    val userList: List<User> = emptyList(),
    val error: Boolean = false,
    val errorMessage: String? = "",
    val fromData: String? = ""

)