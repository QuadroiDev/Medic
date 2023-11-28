package com.quadroidev.medic.data.local

import com.quadroidev.medic.core.local.db.dao.UserDao
import com.quadroidev.medic.core.model.User
import com.quadroidev.medic.core.model.converter.UserToUserEntity

class LoginLocalDataSource(
    private val userDao: UserDao,
    private val userToUserEntity: UserToUserEntity
) {

    suspend fun addUser(user: User) {
        userToUserEntity.map(user).also { userEntity -> userDao.upsertUser(userEntity) }
    }
}