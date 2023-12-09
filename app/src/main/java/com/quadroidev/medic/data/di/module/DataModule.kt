package com.quadroidev.medic.data.di.module

import com.quadroidev.medic.core.local.db.dao.HabitDao

import android.content.Context
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile

import com.quadroidev.medic.core.local.db.dao.UserDao
import com.quadroidev.medic.core.model.converter.HabitEntityToHabit
import com.quadroidev.medic.core.model.converter.HabitToHabitEntity
import com.quadroidev.medic.core.model.converter.UserToUserEntity
import com.quadroidev.medic.data.local.ReminderLocalDataSource
import com.quadroidev.medic.data.local.LoginLocalDataSource
import com.quadroidev.medic.data.repository.ReminderRepositoryImpl
import com.quadroidev.medic.data.repository.LoginRepositoryImpl
import com.quadroidev.medic.domain.repository.ReminderRepository
import com.quadroidev.medic.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

const val APP_PREFERENCES_NAME = "medic_minder_preferences"

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideLoginLocalDataSource(userDao: UserDao, userEntity: UserToUserEntity) =
        LoginLocalDataSource(userDao, userEntity)

    @Provides
    fun provideLoginRepository(loginLocalDataSource: LoginLocalDataSource): LoginRepository =
        LoginRepositoryImpl(loginLocalDataSource)

    @Provides
    fun provideReminderLocalDataSource(
        habitDao: HabitDao,
        habitEntity: HabitToHabitEntity,
        habit: HabitEntityToHabit
    ) =
        ReminderLocalDataSource(habitDao, habitEntity, habit)

    @Provides
    fun provideReminderRepository(reminderLocalDataSource: ReminderLocalDataSource): ReminderRepository =
        ReminderRepositoryImpl(reminderLocalDataSource)

    fun provideDataStore(@ApplicationContext context: Context) = PreferenceDataStoreFactory.create(
        ReplaceFileCorruptionHandler { emptyPreferences() },
        listOf(SharedPreferencesMigration(context, APP_PREFERENCES_NAME)),
        CoroutineScope(Dispatchers.IO + SupervisorJob())
    ) { context.preferencesDataStoreFile(APP_PREFERENCES_NAME) }
}