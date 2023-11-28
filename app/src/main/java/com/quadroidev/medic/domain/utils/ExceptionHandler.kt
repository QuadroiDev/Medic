package com.quadroidev.medic.domain.utils

object ExceptionHandler {
    fun justLogMessage(exception: Exception) {
        FileLog.e(exception.message)
    }
}