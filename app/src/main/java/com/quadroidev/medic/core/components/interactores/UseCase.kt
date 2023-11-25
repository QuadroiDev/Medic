package com.quadroidev.medic.core.components.interactores

import android.util.Log
import com.quadroidev.medic.core.components.result.Result

abstract class UseCase<in PARAMS, out RESPONSE> {
    suspend operator fun invoke(params: PARAMS): Result<RESPONSE> = try {
        Result.Success(execute(params))
    } catch (e: Exception) {
        Log.e("TAG", "invoke: $e", )
        Result.Error(e)
    }
    @Throws(Exception::class)
    protected abstract suspend fun execute(params: PARAMS): RESPONSE
}