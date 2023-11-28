package com.quadroidev.medic.core.model.converter

import com.quadroidev.medic.core.components.maper.Mapper
import com.quadroidev.medic.core.local.models.UserEntity
import com.quadroidev.medic.core.model.User
import javax.inject.Inject

class UserEntityToUser @Inject constructor() : Mapper<UserEntity, User> {
    override fun map(from: UserEntity): User = User(from.phoneNumber)
}