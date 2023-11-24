package com.quadroidev.medic.core.local.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.quadroidev.medic.core.local.db.dao.ColorDao
import com.quadroidev.medic.core.local.db.dao.IconDao
import com.quadroidev.medic.core.local.db.dao.UserDao
import com.quadroidev.medic.core.local.models.ColorEntity
import com.quadroidev.medic.core.local.models.IconEntity
import com.quadroidev.medic.core.local.models.UserEntity

@Database(
    entities = [UserEntity::class, IconEntity::class, ColorEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(UserEntity::class)
abstract class MedicMinderDb : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun iconDao(): IconDao
    abstract fun colorDao(): ColorDao
}