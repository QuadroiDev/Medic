package com.quadroidev.medic.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.quadroidev.medic.domain.utils.FileLog
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppDataStore @Inject constructor(private val dataStore: DataStore<Preferences>) {
    suspend fun saveUserLoggedIn(value: Boolean) =
        save(PreferencesKeys.userLoggedIn, value)

    val readUserLoggedIn = read(PreferencesKeys.userLoggedIn, DefaultValues.USER_LOGGED_IN)

    private suspend fun <T> save(key: Preferences.Key<T>, value: T) =
        dataStore.edit { preferences ->
            preferences[key] = value
        }

    private fun <T> read(key: Preferences.Key<T>, defaultValue: T) =
        dataStore.data.catch { exception -> FileLog.e(exception) }.map { preferences ->
            preferences[key] ?: defaultValue
        }

    private object Keys {
        const val USER_LOGGED = "userLogin"
    }

    private object PreferencesKeys {
        val userLoggedIn = booleanPreferencesKey(Keys.USER_LOGGED)
    }

    private object DefaultValues {
        const val USER_LOGGED_IN = false
    }

}