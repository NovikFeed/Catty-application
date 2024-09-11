package com.example.catty.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catty.personList.data.repository.UserListEvents
import com.example.catty.presentation.model.UserListState
import com.example.catty.personList.data.repository.UserListRepository
import com.example.catty.personList.domain.model.User
import com.example.catty.personList.domain.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userListRepository: UserListRepository
) : ViewModel() {

    init {
        updateData()
    }
    private val _userList = MutableStateFlow(UserListState())
    val userList = _userList.asStateFlow()

    fun onEvent(events : UserListEvents){
        when(events){
            UserListEvents.UpdateData -> updateData()
        }
    }

    private fun updateData() {
        viewModelScope.launch {
            _userList.update {
                it.copy(isLoading = true)
            }
            userListRepository.getUserList(_userList.value.count).collectLatest { result ->
                when(result){
                    is Response.Error -> {
                        _userList.update { it.copy(isLoading = false, error = true, errorMessage = result.message) }
                        result.data?.let {list ->
                            _userList.update { it.copy(userList = ((userList.value.userList.plus(list) ?: list)).distinct()) }
                        }
                    }
                    is Response.Loading -> {
                        _userList.update { it.copy(isLoading = result.isLoading) }
                    }
                    is Response.LocalData -> {
                        _userList.update { it.copy(isLoading = false,  fromData = result.message) }
                        result.data?.let {list ->
                            _userList.update { it.copy(userList = ((userList.value.userList.plus(list) ?: list)).distinct()) }
                        }
                    }
                    is Response.RemoteData -> {
                        _userList.update{it.copy(isLoading = false, fromData = result.message, count = userList.value.userList.size + 6)  }
                        result.data?.let {list ->
                            _userList.update { it.copy(userList = ((userList.value.userList.plus(list) ?: list)).distinct()) }
                        }
                    }
                }
            }
        }
    }
}