package com.quadroidev.medic.core.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("icon")
data class IconEntity(
    @ColumnInfo("src") val src: Int,
    @PrimaryKey(true) @ColumnInfo("id") val id: Int = 0
)