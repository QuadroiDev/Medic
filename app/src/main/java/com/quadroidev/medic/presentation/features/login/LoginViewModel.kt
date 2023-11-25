package com.quadroidev.medic.presentation.features.login

import androidx.lifecycle.viewModelScope
import com.quadroidev.medic.core.components.base.BaseViewModel
import com.quadroidev.medic.core.model.User
import com.quadroidev.medic.domain.usecases.feature.login.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val createUserUseCase: CreateUserUseCase) : BaseViewModel() {

    private val _homeEventChanel: Channel<LoginEvents> = Channel()
    val homeEventChanel: Flow<LoginEvents> = _homeEventChanel.receiveAsFlow()
    fun createUser(username:String){
        viewModelScope.launch {
            createUserUseCase.invoke(CreateUserUseCase.Params(User(username)))
            _homeEventChanel.send(LoginEvents.UserLoggedInSuccessfully("Log in successfully"))
        }
    }

    sealed class LoginEvents {
        data class UserLoggedInSuccessfully(val message:String): LoginEvents()
    }
}