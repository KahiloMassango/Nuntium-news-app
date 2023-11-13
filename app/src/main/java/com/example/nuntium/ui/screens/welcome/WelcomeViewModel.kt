package com.example.nuntium.ui.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nuntium.data.repository.AppPreferencesRepository
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val appPreferencesRepository: AppPreferencesRepository
): ViewModel() {

    fun updateStartScreenPreference() {
        viewModelScope.launch {
            appPreferencesRepository.saveStartScreenPreference()
        }
    }

}