package com.quadroidev.medic.presentation.features.createreminder

import androidx.lifecycle.viewModelScope
import com.quadroidev.medic.core.components.base.BaseViewModel
import com.quadroidev.medic.core.model.Category
import com.quadroidev.medic.core.model.Habit
import com.quadroidev.medic.domain.usecases.feature.createreminder.CreateReminderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateReminderViewModel @Inject constructor(
    private val createReminderUseCase: CreateReminderUseCase
) : BaseViewModel() {

    private val _eventChannel: Channel<SubmitEvents> = Channel()
    val eventChannel: Flow<SubmitEvents> = _eventChannel.receiveAsFlow()

    fun submitButtonClicked(
        name: String,
        startTime: Long,
        count: Int,
        categoryName: String,
        categoryImage: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val category = Category(categoryName, categoryImage)
            val habit = Habit(name, startTime, count, category)
            createReminderUseCase.invoke(CreateReminderUseCase.Params(habit))
            _eventChannel.send(SubmitEvents.SubmitSuccessfully(""))
        }
    }

    sealed class SubmitEvents {
        data class SubmitSuccessfully(val message: String) : SubmitEvents()
    }
}