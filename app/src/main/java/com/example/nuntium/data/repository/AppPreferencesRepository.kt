package com.example.nuntium.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


const val APP_PREFERENCE_NAME = "app_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = APP_PREFERENCE_NAME
)

class AppPreferencesRepository(
    private val context: Context
) {

    val shouldStartFromHomeScreen = context.dataStore.data
        .catch {
            if(it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[SHOULD_START_FROM_HOME_SCREEN] ?: false

        }

    private companion object {
        val SHOULD_START_FROM_HOME_SCREEN =
            booleanPreferencesKey("should_start_from_home_screen")

        const val TAG = "UserPreferencesRepo"

    }

    suspend fun saveStartScreenPreference() {
        context.dataStore.edit { preferences ->
            preferences[SHOULD_START_FROM_HOME_SCREEN] = true
        }
    }

}