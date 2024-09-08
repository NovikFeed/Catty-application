package com.example.catty.personList.data.remote

import com.example.catty.personList.data.remote.respond.UserListDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface UserAPI {
    @GET("/users")
    suspend fun getUsers(
        @Query("count") count : Int
    ) : UserListDTO

    companion object{
        const val BASE_URL = "https://frontend-test-assignment-api.abz.agency/api/v1/"
    }
}
