package com.quadroidev.medic.core.local.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.quadroidev.medic.core.local.db.dao.CategoryDao
import com.quadroidev.medic.core.local.db.dao.ColorDao
import com.quadroidev.medic.core.local.db.dao.HabitDao
import com.quadroidev.medic.core.local.db.dao.IconDao
import com.quadroidev.medic.core.local.db.dao.UserDao
import com.quadroidev.medic.core.local.models.CategoryEntity
import com.quadroidev.medic.core.local.models.ColorEntity
import com.quadroidev.medic.core.local.models.HabitEntity
import com.quadroidev.medic.core.local.models.IconEntity
import com.quadroidev.medic.core.local.models.UserEntity

@Database(
    entities = [UserEntity::class, IconEntity::class, ColorEntity::class, HabitEntity::class, CategoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MedicMinderDb : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun iconDao(): IconDao
    abstract fun colorDao(): ColorDao
    abstract fun habitDao(): HabitDao
    abstract fun categoryDao(): CategoryDao
}