package com.quadroidev.medic.core.local.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.quadroidev.medic.core.common.enums.NotificationType

@Entity("habit")

data class HabitEntity(
    @ColumnInfo("name") val name: String,
    @ColumnInfo("start_time") val startTime: Long,
    @ColumnInfo("cunt") val cunt: Int = 1,
    @Embedded val category: CategoryEntity,
    @ColumnInfo("description") val description: String?,
    @ColumnInfo("notification_type") val notificationType: NotificationType,
    @ColumnInfo("song") val song: Int?,
    @ColumnInfo("periodic") val periodic: Long?,
    @ColumnInfo("user_id") val userId: Int,
    @PrimaryKey @ColumnInfo("hid") val id: Long = 0
)
