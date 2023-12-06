package com.quadroidev.medic.core.model.converter

import com.quadroidev.medic.core.common.enums.NotificationType
import com.quadroidev.medic.core.components.maper.Mapper
import com.quadroidev.medic.core.local.models.CategoryEntity
import com.quadroidev.medic.core.local.models.HabitEntity
import com.quadroidev.medic.core.model.Habit
import javax.inject.Inject

class HabitToHabitEntity @Inject constructor() : Mapper<Habit, HabitEntity> {
    override fun map(from: Habit): HabitEntity = HabitEntity(
        name = from.name,
        startTime = from.startTime,
        count = from.count,
        category = CategoryEntity(name = from.category.categoryName,
            image = from.category.image,
            null),
        description = null,
        notificationType = NotificationType.SMS,
        song = null,
        periodic = null,
        userId = 0
    )
}