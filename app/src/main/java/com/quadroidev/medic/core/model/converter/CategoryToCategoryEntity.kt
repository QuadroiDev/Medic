package com.quadroidev.medic.core.model.converter

import com.quadroidev.medic.core.components.maper.Mapper
import com.quadroidev.medic.core.local.models.CategoryEntity
import com.quadroidev.medic.core.model.Category
import javax.inject.Inject

class CategoryToCategoryEntity @Inject constructor() : Mapper<Category, CategoryEntity> {
    override fun map(from: Category): CategoryEntity = CategoryEntity(
        name = from.categoryName,
        image = from.image,
        color = null
    )
}