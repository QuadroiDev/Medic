package com.quadroidev.medic.domain.repository

import com.quadroidev.medic.core.model.Habit
import kotlinx.coroutines.flow.Flow

interface ReminderRepository {

    suspend fun createReminder(habit: Habit)

    suspend fun getAllHabits(): Flow<List<Habit>>
}