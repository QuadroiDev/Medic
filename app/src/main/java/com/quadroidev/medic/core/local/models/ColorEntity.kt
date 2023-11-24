package com.quadroidev.medic.core.local.models

import androidx.annotation.ColorInt
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("color")
data class ColorEntity(
    @ColumnInfo("src") @ColorInt val src: Int,
    @PrimaryKey(true) @ColumnInfo("id") val id: Int = 0
)