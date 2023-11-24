package com.quadroidev.medic.core.local.models

import androidx.annotation.ColorInt
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("category")
data class CategoryEntity(
    @ColumnInfo("category_name") val name: String,
    @ColumnInfo("image") val image: Int,
    @ColumnInfo("color") @ColorInt val color: Int?,
    @PrimaryKey(true) @ColumnInfo("id") val id: Int = 0
)
