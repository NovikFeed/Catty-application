package com.example.catty.di

import com.example.catty.personList.data.repository.UserListRepository
import com.example.catty.personList.domain.interfaces.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule(){
    @Binds
    @Singleton
    abstract fun bindsRepository(
        userListRepository: UserListRepository
    ):Repository
}