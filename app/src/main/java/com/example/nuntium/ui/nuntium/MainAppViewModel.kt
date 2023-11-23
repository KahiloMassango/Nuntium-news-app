package com.example.nuntium.ui.nuntium

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nuntium.data.repository.AppPreferencesRepository
import com.example.nuntium.ui.nvgraph.Route
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class MainAppViewModel @Inject constructor(
    appPreferencesRepository: AppPreferencesRepository
): ViewModel() {

    var splashCondition by mutableStateOf(true)
        private set

    var startScreen by mutableStateOf(Route.OnBoardingScreen.route)
        private set

    init {
        appPreferencesRepository.shouldStartFromHomeScreen.onEach { state ->
            if(state) {
                startScreen = Route.HomeScreen.route
            } else {
                startScreen = Route.OnBoardingScreen.route
            }
            delay(900)
            splashCondition = false
        }.launchIn(viewModelScope)
    }

}


