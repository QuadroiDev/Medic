package com.quadroidev.medic.core.local.models

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithHabit(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "uid",
        entityColumn = "user_id"
    )
    val habits: List<HabitEntity>
)