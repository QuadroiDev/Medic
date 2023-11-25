package com.quadroidev.medic.core.components.model

class EntityModelAdapter<DataEntity, Model>(
    private val toModelConverter: (DataEntity) -> Model,
    private val toEntityConverter: (Model) -> DataEntity
) {
    fun toModel(entity: DataEntity?): Model? = entity?.let(toModelConverter)
    fun toEntity(model: Model?): DataEntity? = model?.let(toEntityConverter)
}

