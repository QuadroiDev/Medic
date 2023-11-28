package com.quadroidev.medic.core.components.interactores

import com.quadroidev.medic.core.components.result.Result
import com.quadroidev.medic.domain.utils.ExceptionHandler

abstract class UseCase<in PARAMS, out RESPONSE> {
    suspend operator fun invoke(params: PARAMS): Result<RESPONSE> = try {
        Result.Success(execute(params))
    } catch (e: Exception) {
        ExceptionHandler.justLogMessage(e)
        Result.Error(e)
    }

    @Throws(Exception::class)
    protected abstract suspend fun execute(params: PARAMS): RESPONSE
}
