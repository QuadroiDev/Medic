package com.quadroidev.medic.core.local.di.module

import android.content.Context
import androidx.room.Room
import com.quadroidev.medic.core.local.db.database.MedicMinderDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideMedicMinderDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MedicMinderDb::class.java, "medic_minder_database")
            .build()

    @Provides
    @Singleton
    fun provideUserDao(medicMinderDb: MedicMinderDb) = medicMinderDb.userDao()

    @Provides
    @Singleton
    fun provideIconDao(medicMinderDb: MedicMinderDb) = medicMinderDb.iconDao()

    @Provides
    @Singleton
    fun provideColorDao(medicMinderDb: MedicMinderDb) = medicMinderDb.colorDao()

    @Provides
    @Singleton
    fun provideHabitDao(medicMinderDb: MedicMinderDb) = medicMinderDb.habitDao()

    @Provides
    @Singleton
    fun provideCategoryDao(medicMinderDb: MedicMinderDb) = medicMinderDb.categoryDao()

}