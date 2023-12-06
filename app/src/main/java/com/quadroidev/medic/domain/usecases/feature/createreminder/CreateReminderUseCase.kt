package com.quadroidev.medic.domain.usecases.feature.createreminder

import android.util.Log
import com.quadroidev.medic.core.components.interactores.UseCase
import com.quadroidev.medic.core.model.Habit
import com.quadroidev.medic.domain.repository.ReminderRepository
import javax.inject.Inject

class CreateReminderUseCase @Inject constructor(
    private val reminderRepository: ReminderRepository
) : UseCase<CreateReminderUseCase.Params, Unit>() {

    data class Params(val habit: Habit)


    override suspend fun execute(params: Params) = with(params) {
        Log.i("CreateReminderUseCase", "execute: ${params.habit.name}")
        reminderRepository.createReminder(habit)
    }
}