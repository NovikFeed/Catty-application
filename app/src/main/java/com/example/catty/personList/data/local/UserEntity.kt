package com.example.catty.personList.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    val email: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    val phone: String,
    val photo: String,
    val position: String,
    val position_id: Int,
    val registration_timestamp: Int
)