package com.quadroidev.medic.data.di.module

import com.quadroidev.medic.core.local.db.dao.UserDao
import com.quadroidev.medic.data.local.LoginLocalDataSource
import com.quadroidev.medic.data.repository.LoginRepositoryImpl
import com.quadroidev.medic.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideLoginLocalDataSource(userDao: UserDao) = LoginLocalDataSource(userDao)

    @Provides
    fun provideLoginRepository(loginLocalDataSource: LoginLocalDataSource): LoginRepository =
        LoginRepositoryImpl(loginLocalDataSource)
}