package com.quadroidev.medic.core.model.converter

import com.quadroidev.medic.core.common.enums.NotificationType
import com.quadroidev.medic.core.components.maper.Mapper
import com.quadroidev.medic.core.local.models.HabitEntity
import com.quadroidev.medic.core.model.Category
import com.quadroidev.medic.core.model.Habit
import javax.inject.Inject

class HabitEntityToHabit @Inject constructor() : Mapper<HabitEntity, Habit> {

    override fun map(from: HabitEntity): Habit = Habit(
        name = from.name,
        startTime = from.startTime,
        count = from.count,
        category = Category(from.category.name, from.category.image),
        notificationType = NotificationType.SMS,
        userId = 0
    )
}