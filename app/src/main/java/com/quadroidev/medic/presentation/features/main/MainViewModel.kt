package com.quadroidev.medic.presentation.features.main

import androidx.lifecycle.viewModelScope
import com.quadroidev.medic.core.components.base.BaseViewModel
import com.quadroidev.medic.core.model.Habit
import com.quadroidev.medic.domain.usecases.feature.createreminder.GetAllRemindersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllRemindersUseCase: GetAllRemindersUseCase
) :BaseViewModel() {

    private val _habits: MutableStateFlow<List<Habit>> = MutableStateFlow(emptyList())
    val habits: Flow<List<Habit>> = _habits

    private val _eventChannel: Channel<AddHabitEvents> = Channel()
    val eventChannel: Flow<AddHabitEvents> = _eventChannel.receiveAsFlow()

    fun getAllHabits() {
        viewModelScope.launch {
            _eventChannel.send(AddHabitEvents.FetchAllHabitsEvent)
            getAllRemindersUseCase.getAllHabits().collect {
                _habits.value = it
            }
        }
    }

    fun createEvent() = viewModelScope.launch {
        _eventChannel.send(AddHabitEvents.AddHabitSuccessfully)
    }

    sealed class AddHabitEvents {
        data object AddHabitSuccessfully: AddHabitEvents()

        data object FetchAllHabitsEvent: AddHabitEvents()
    }

}