package com.example.catty.personList.data.remote.respond

data class UserListDTO(
    val count: Int,
    val links: Links,
    val page: Int,
    val success: Boolean,
    val total_pages: Int,
    val total_users: Int,
    val users: List<UserDTO>
)