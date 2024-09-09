package com.example.catty.personList.data.mapers

import com.example.catty.personList.data.local.UserEntity
import com.example.catty.personList.data.remote.respond.UserDTO
import com.example.catty.personList.domain.model.User

fun UserDTO.toEntity(): UserEntity {
    return UserEntity(
        email = email ?: "",
        id = id ?: 0,
        name = name ?: "",
        phone = phone ?: "",
        photo = photo ?: "",
        position = position ?: "",
        position_id = position_id ?: 0,
        registration_timestamp = registration_timestamp ?: 0
    )
}

fun UserDTO.toUser(): User {
    return User(
        email = email ?: "",
        id = id ?: 0,
        name = name ?: "",
        phone = phone ?: "",
        photo = photo ?: "",
        position = position ?: "",
        position_id = position_id ?: 0,
        registration_timestamp = registration_timestamp ?: 0
    )
}

fun UserEntity.toUser(): User {
    return User(
        email = email,
        id = id,
        name = name,
        phone = phone,
        photo = photo,
        position = position,
        position_id = position_id,
        registration_timestamp = registration_timestamp
    )
}