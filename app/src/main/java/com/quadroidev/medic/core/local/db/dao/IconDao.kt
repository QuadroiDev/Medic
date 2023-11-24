package com.quadroidev.medic.core.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.quadroidev.medic.core.local.models.IconEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IconDao {
    @Upsert
    fun upsertIcon(iconEntity: IconEntity)

    @Query("SELECT * FROM icon")
    fun getAll(): Flow<List<IconEntity>>

    @Delete
    fun delete(iconEntity: IconEntity)
}