package com.example.catty.personList.data.repository

import android.util.Log
import com.example.catty.personList.data.local.UserDatabase
import com.example.catty.personList.data.mapers.toEntity
import com.example.catty.personList.data.mapers.toUser
import com.example.catty.personList.data.remote.UserAPI
import com.example.catty.personList.domain.interfaces.Repository
import com.example.catty.personList.domain.model.User
import com.example.catty.personList.domain.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserListRepository @Inject constructor(
    private val userAPI: UserAPI,
    private val database: UserDatabase
) : Repository {
    override suspend fun getUserList(count: Int): Flow<Response<List<User>>> {
        return flow {
            emit(Response.Loading(true))
            val localData = database.userDao.getUsers()

            val remoteData = try {
                userAPI.getUsers(count)
            }
            catch (e : Exception){
                e.printStackTrace()
                emit(Response.Error(localData.map{it.toUser()}, e.message))
                emit(Response.Loading(false))
                return@flow
            }
            catch (e : IOException){
                e.printStackTrace()
                emit(Response.Error(localData.map{it.toUser()}, e.message))
                emit(Response.Loading(false))
                return@flow
            }
            catch (e : HttpException){
                e.printStackTrace()
                emit(Response.Error(localData.map{it.toUser()}, e.message))
                emit(Response.Loading(false))
                return@flow
            }
            if (remoteData != null) {
                database.userDao.upsertUserList(remoteData.users.map { it.toEntity() })
                emit(Response.RemoteData(remoteData.users.map { it.toUser() }))
                emit(Response.Loading(false))
                return@flow
            }
        }
    }
}