package com.quadroidev.medic.domain.usecases.feature.login

import com.quadroidev.medic.core.components.interactores.UseCase
import com.quadroidev.medic.core.model.User
import com.quadroidev.medic.domain.repository.LoginRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val loginRepository: LoginRepository) :
    UseCase<CreateUserUseCase.Params, Unit>() {
    data class Params(val user: User)
    override suspend fun execute(params: Params) = with(params) { loginRepository.addUser(user) }
}