package com.example.nuntium.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.nuntium.data.repository.AppPreferencesRepository

private const val APP_PREFERENCE_NAME = "app_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = APP_PREFERENCE_NAME
)
class NuntiumApplication: Application() {
    lateinit var container: AppContainer
    lateinit var appPreferencesRepository: AppPreferencesRepository

    override fun onCreate() {
        super.onCreate()
        container = AppDefaultContainer(this)
        appPreferencesRepository = AppPreferencesRepository(dataStore)
    }
}