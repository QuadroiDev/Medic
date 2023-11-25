package com.quadroidev.medic.core.local.models.converters

import androidx.room.TypeConverter
import com.quadroidev.medic.core.common.enums.Gender
import com.quadroidev.medic.core.common.enums.NotificationType

object MedicTypeConverters {
    @TypeConverter
    fun fromGender(gender: Gender) = gender.name

    @TypeConverter
    fun fromStringToGender(gender: String) = Gender.valueOf(gender)

    @TypeConverter
    fun fromNotificationType(notificationType: NotificationType) = notificationType.name

    @TypeConverter
    fun fromStringToNotificationType(notificationType: String) =
        NotificationType.valueOf(notificationType)
}
