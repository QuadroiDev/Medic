package com.quadroidev.medic.domain.repository

import com.quadroidev.medic.core.model.User

interface LoginRepository {
    suspend fun addUser(user: User)
}