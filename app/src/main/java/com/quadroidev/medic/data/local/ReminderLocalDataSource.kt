package com.quadroidev.medic.data.local

import com.quadroidev.medic.core.components.maper.map
import com.quadroidev.medic.core.local.db.dao.HabitDao
import com.quadroidev.medic.core.model.Habit
import com.quadroidev.medic.core.model.converter.HabitEntityToHabit
import com.quadroidev.medic.core.model.converter.HabitToHabitEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReminderLocalDataSource(
    private val habitDao: HabitDao,
    private val habitToHabitEntity: HabitToHabitEntity,
    private val habitEntityToHabit: HabitEntityToHabit
) {
    fun upsertHabit(habit: Habit) {
        habitToHabitEntity.map(habit).also { habitEntity -> habitDao.upsertSpecificHabit(habitEntity) }
    }

    fun getAllHabits(): Flow<List<Habit>> =
        habitDao.getAllHabits().map { habitEntityToHabit.map(it) }

}