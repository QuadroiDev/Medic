package com.quadroidev.medic.core.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.quadroidev.medic.core.local.models.HabitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Upsert
    fun upsertSpecificHabit(habitEntity: HabitEntity)

    @Query("SELECT * FROM habit")
    fun getAllHabits(): Flow<List<HabitEntity>>

    @Query("SELECT * FROM habit WHERE user_id = :userId")
    fun getAllHabitsForOneUser(userId: Int): Flow<List<HabitEntity>>

    @Query("SELECT * FROM habit WHERE name LIKE :name")
    fun findSpecificHabitWithName(name: String): Flow<List<HabitEntity>>

    @Delete
    fun deleteSpecificHabit(habitEntity: HabitEntity)

    @Query("DELETE FROM habit")
    fun deleteAllHabits()
}