package com.quadroidev.medic.core.model

import com.quadroidev.medic.core.common.enums.NotificationType

data class Habit (
    val name: String = "",
    val startTime: Long = 0,
    val count: Int = 0,
    val category: Category = Category("", 0),
    val timeOfStart: String = "",
    val dateOfStart: String = "",
    val notificationType: NotificationType = NotificationType.SMS,
    val userId: Int = 0
)