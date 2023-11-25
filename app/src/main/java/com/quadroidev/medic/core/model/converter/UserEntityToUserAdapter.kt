package com.quadroidev.medic.core.model.converter

import com.quadroidev.medic.core.components.model.EntityModelAdapter
import com.quadroidev.medic.core.local.models.UserEntity
import com.quadroidev.medic.core.model.User

val userEntityToUserAdapter = EntityModelAdapter<UserEntity, User>(
    toModelConverter = { entity ->
        User(name = entity.name)
    },
    toEntityConverter = { model ->
        UserEntity(name = model.name)
    }
)