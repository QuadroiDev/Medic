package com.quadroidev.medic.core.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.quadroidev.medic.core.local.models.UserEntity
import com.quadroidev.medic.core.local.models.UserWithHabit
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Upsert
    fun upsertUser(vararg userEntity: UserEntity)

    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<UserEntity>>

    @Query("SELECT * FROM user")
    @Transaction
    fun getAllWithHabit(): Flow<List<UserWithHabit>>

    @Query("SELECT * FROM user WHERE name LIKE :name")
    fun findWithName(name: String): Flow<List<UserEntity>>

    @Delete
    fun delete(userEntity: UserEntity)

    @Query("DELETE FROM user")
    fun deleteAll()
}