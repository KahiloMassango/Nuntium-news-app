package com.example.nuntium.ui.nuntium

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nuntium.data.repository.AppPreferencesRepository
import com.example.nuntium.ui.nvgraph.Route
import kotlinx.coroutines.launch

class MainAppViewModel(
    appPreferencesRepository: AppPreferencesRepository
): ViewModel() {


     var startScreen by mutableStateOf(Route.HomeScreen.route)
        private set
    init {
        viewModelScope.launch {
                appPreferencesRepository.shouldStartFromHomeScreen.collect { condition ->
                    startScreen = if (condition) {
                        Route.HomeScreen.route
                    } else {
                        Route.OnBoardingScreen.route
                    }
                }
        }
    }
}


