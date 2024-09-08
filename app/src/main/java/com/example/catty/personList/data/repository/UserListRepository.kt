package com.example.catty.personList.data.repository

import com.example.catty.personList.data.local.UserDatabase
import com.example.catty.personList.data.mapers.toEntity
import com.example.catty.personList.data.mapers.toUser
import com.example.catty.personList.data.remote.UserAPI
import com.example.catty.personList.domain.interfaces.Repository
import com.example.catty.personList.domain.model.User
import com.example.catty.personList.domain.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserListRepository @Inject constructor(
    private val userAPI : UserAPI,
    private val database: UserDatabase
) : Repository{
    override suspend fun getUserList(count: Int): Flow<Response<List<User>>> {
        return flow{
            emit(Response.Loading(true))
            val remoteData = userAPI.getUsers(count)
            if (remoteData != null){
                database.userDao.upsertUserList(remoteData.users.map { it.toEntity() })
                emit(Response.RemoteData(remoteData.users.map{it.toUser()}))
            }
        }
    }
}