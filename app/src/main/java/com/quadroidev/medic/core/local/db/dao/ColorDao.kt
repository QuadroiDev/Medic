package com.quadroidev.medic.core.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.quadroidev.medic.core.local.models.ColorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ColorDao {
    @Upsert
    fun insertColor(colorEntity: ColorEntity)

    @Query("SELECT * FROM color")
    fun getAll(): Flow<List<ColorEntity>>

    @Delete
    fun delete(colorEntity: ColorEntity)
}