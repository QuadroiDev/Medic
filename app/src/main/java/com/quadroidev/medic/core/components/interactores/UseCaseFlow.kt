package com.quadroidev.medic.core.components.interactores

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withTimeout
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

abstract class UseCaseFlow<PARAMS, RESPONSE> {
    operator fun invoke(
        params: PARAMS, timeout: Duration = DefaultTimeout,
    ): Flow<InteractState<RESPONSE>> = execute(params).map { r ->
        withTimeout(timeout) { InteractState.Success(r) as InteractState<RESPONSE> }
    }.catch { e ->
        emit(InteractState.Error(e as Exception))
    }.onStart {
        emit(InteractState.Loading)
    }
    protected abstract fun execute(params: PARAMS): Flow<RESPONSE>
    companion object { private val DefaultTimeout = 100.milliseconds }
}

sealed class InteractState<out T> {
    data class Success<T>(val data: T) : InteractState<T>()
    data class Error(val errorMessage: Exception) : InteractState<Nothing>()
    object Loading : InteractState<Nothing>()
}

val <T> InteractState<T>.Success get() = this is InteractState.Success && data != null