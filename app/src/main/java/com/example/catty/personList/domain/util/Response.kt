package com.example.catty.personList.domain.util

sealed class Response<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T>(val isLoading: Boolean = true) : Response<T>()
    class RemoteData<T>(data: T?) : Response<T>(data, "Last update")
    class LocalData<T>(data: T?) : Response<T>(data, "Local data")
    class Error<T>(data: T?, private val errorMessage : String ? = "") : Response<T>(data, errorMessage)
}