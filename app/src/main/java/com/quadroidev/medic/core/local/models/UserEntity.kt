package com.quadroidev.medic.core.local.models

import androidx.annotation.ColorInt
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.quadroidev.medic.core.common.enums.Gender

@Entity(tableName = "user")
data class UserEntity(
    @ColumnInfo("name") val name: String = "Ghost",
    @ColumnInfo("phone_number") val phoneNumber: String? = null,
    @ColumnInfo("age") val age: Int? = null,
    @ColumnInfo("gender") val gender: Gender? = Gender.OTHER,
    @ColumnInfo("image") val image: Int? = null,
    @ColumnInfo("color") @ColorInt val color: Int? = null,
    @PrimaryKey(true) @ColumnInfo("uid") val uid: Int = 0
)