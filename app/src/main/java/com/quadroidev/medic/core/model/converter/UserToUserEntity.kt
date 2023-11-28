package com.quadroidev.medic.core.model.converter

import com.quadroidev.medic.core.components.maper.Mapper
import com.quadroidev.medic.core.local.models.UserEntity
import com.quadroidev.medic.core.model.User
import javax.inject.Inject

class UserToUserEntity @Inject constructor() : Mapper<User, UserEntity> {
    override fun map(from: User): UserEntity = UserEntity(phoneNumber = from.phoneNumber)
}