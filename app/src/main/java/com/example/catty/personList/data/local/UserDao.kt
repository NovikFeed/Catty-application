package com.example.catty.personList.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserDao {
    @Upsert
    suspend fun upsertUserList(userList : List<UserEntity>)
    @Query("SELECT * FROM UserEntity")
    suspend fun getUser() : List<UserEntity>
}