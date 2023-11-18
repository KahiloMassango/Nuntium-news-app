package com.example.nuntium.ui.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nuntium.data.repository.AppPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val appPreferencesRepository: AppPreferencesRepository
): ViewModel() {

    fun updateStartScreenPreference() {
        viewModelScope.launch {
            appPreferencesRepository.saveStartScreenPreference()
        }
    }

}