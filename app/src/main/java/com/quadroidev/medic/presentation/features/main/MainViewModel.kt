package com.quadroidev.medic.presentation.features.main

import androidx.lifecycle.viewModelScope
import com.quadroidev.medic.core.components.base.BaseViewModel
import com.quadroidev.medic.core.model.Habit
import com.quadroidev.medic.domain.usecases.feature.createreminder.GetAllRemindersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllRemindersUseCase: GetAllRemindersUseCase
) :BaseViewModel() {

    private val _habits: Channel<List<Habit>> = Channel()
    val habits: Flow<List<Habit>> = _habits.receiveAsFlow()

    fun getAllHabits() {
        viewModelScope.launch {
            getAllRemindersUseCase.getAllHabits().collect {
                _habits.send(it)
            }
        }
    }

}