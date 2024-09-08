package com.example.catty.personList.domain.model

import androidx.room.PrimaryKey

data class User(
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val photo: String,
    val position: String,
    val position_id: Int,
    val registration_timestamp: Int
)