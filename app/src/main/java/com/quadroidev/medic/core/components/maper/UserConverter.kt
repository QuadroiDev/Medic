package com.quadroidev.medic.core.components.maper

import com.quadroidev.medic.core.local.models.UserEntity
import com.quadroidev.medic.core.model.User


val userConverter = ModelConverter.create<UserEntity, User> { userEntity ->
    User(userEntity.phoneNumber, userEntity.name)
}

val userToEntityConverter = ModelConverter.create<User, UserEntity> { user ->
    UserEntity(user.name, user.phoneNumber)
}