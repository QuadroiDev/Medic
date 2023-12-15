package com.quadroidev.medic.data.local

import com.quadroidev.medic.core.components.maper.userToEntityConverter
import com.quadroidev.medic.core.local.db.dao.UserDao
import com.quadroidev.medic.core.model.User

class LoginLocalDataSource(
    private val userDao: UserDao,
) {
    suspend fun addUser(user: User) { userDao.upsertUser(userToEntityConverter.convert(user)) }
}