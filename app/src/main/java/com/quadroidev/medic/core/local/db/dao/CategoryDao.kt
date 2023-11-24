package com.quadroidev.medic.core.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.quadroidev.medic.core.local.models.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Upsert
    fun upsertColor(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM category")
    fun getAll(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM category WHERE category_name LIKE :name")
    fun findWithName(name: String): Flow<List<CategoryEntity>>

    @Delete
    fun delete(categoryEntity: CategoryEntity)
}