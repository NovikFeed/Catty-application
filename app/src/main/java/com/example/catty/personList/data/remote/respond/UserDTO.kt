package com.example.catty.personList.data.remote.respond

data class UserDTO(
    val email: String?,
    val id: Int?,
    val name: String?,
    val phone: String?,
    val photo: String?,
    val position: String?,
    val position_id: Int?,
    val registration_timestamp: Int?
)