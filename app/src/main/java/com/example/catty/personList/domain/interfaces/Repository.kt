package com.example.catty.personList.domain.interfaces

import com.example.catty.personList.domain.model.User
import com.example.catty.personList.domain.util.Response
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getUserList(count : Int): Flow<Response<List<User>>>
}