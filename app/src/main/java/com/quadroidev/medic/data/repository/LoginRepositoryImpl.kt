package com.quadroidev.medic.data.repository

import com.quadroidev.medic.core.model.User
import com.quadroidev.medic.data.local.LoginLocalDataSource
import com.quadroidev.medic.domain.repository.LoginRepository

class LoginRepositoryImpl(private val localDataSource: LoginLocalDataSource) : LoginRepository {
    override suspend fun addUser(user: User) = localDataSource.addUser(user)
}