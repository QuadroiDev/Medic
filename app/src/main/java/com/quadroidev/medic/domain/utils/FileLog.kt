package com.quadroidev.medic.domain.utils

import android.util.Log
import io.grpc.android.BuildConfig

object FileLog {
    private const val ERROR = "ERROR"
    fun e(message: String?) {
        if (BuildConfig.DEBUG) Log.e(ERROR, " $message")
    }
}