package com.quadroidev.medic.data.repository

import com.quadroidev.medic.core.model.Habit
import com.quadroidev.medic.data.local.ReminderLocalDataSource
import com.quadroidev.medic.domain.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow

class ReminderRepositoryImpl(
    private val reminderLocalDataSource: ReminderLocalDataSource
) : ReminderRepository {
    override suspend fun upsertHabit(habit: Habit) =
        reminderLocalDataSource.upsertHabit(habit)
    override suspend fun getAllHabits(): Flow<List<Habit>> =
        reminderLocalDataSource.getAllHabits()
}