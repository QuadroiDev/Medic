package com.quadroidev.medic.data.local

import com.quadroidev.medic.core.local.db.dao.UserDao
import com.quadroidev.medic.core.model.User
import com.quadroidev.medic.core.model.converter.userEntityToUserAdapter

class LoginLocalDataSource(private val userDao: UserDao) {

    suspend fun addUser(user: User) {
        userEntityToUserAdapter.toEntity(user)?.let { userDao.upsertUser(it) }
    }
}