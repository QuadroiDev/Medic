package com.quadroidev.medic.domain.usecases.feature.createreminder

import com.quadroidev.medic.domain.repository.ReminderRepository
import javax.inject.Inject

class GetAllRemindersUseCase @Inject constructor(
    private val reminderRepository: ReminderRepository
) {
    suspend fun getAllHabits() = reminderRepository.getAllHabits()
}