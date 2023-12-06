package com.quadroidev.medic.core.model.converter

import com.quadroidev.medic.core.components.maper.Mapper
import com.quadroidev.medic.core.local.models.CategoryEntity
import com.quadroidev.medic.core.model.Category
import javax.inject.Inject

class CategoryEntityToCategory @Inject constructor() : Mapper<CategoryEntity, Category> {

    override fun map(from: CategoryEntity): Category = Category(
        categoryName = from.name,
        image = from.image
    )
}