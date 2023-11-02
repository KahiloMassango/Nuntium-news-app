package com.example.nuntium.ui.screens.homePage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.nuntium.data.model.News
import com.example.nuntium.data.repository.NewsRepository
import com.example.nuntium.di.NuntiumApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface HomeUiState {
    data class Success(
        val newsList
        : List<News> = emptyList(),
    ) : HomeUiState
    object Error : HomeUiState
    object Loading : HomeUiState
}

class HomeViewModel(
    private val newsRepository: NewsRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    var uiState = _uiState.asStateFlow()

    init {
        getRecommendedNews()
    }

    var searchText by mutableStateOf("")
        private set

    fun updateSearchText(value: String) {
        searchText = value
    }

    fun getNewsByCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchText = ""
            _uiState.value = try {
                HomeUiState.Success(newsList = newsRepository.getNewsByCategory(category))
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    fun getRecommendedNews() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = HomeUiState.Loading
            _uiState.value = try {
                HomeUiState.Success(newsList = newsRepository.getRecommendedNews())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    fun getNewsByKeyword() {
        viewModelScope.launch(Dispatchers.IO) {
            if(searchText.isNotEmpty()){
                _uiState.value = try {
                    HomeUiState.Success(newsRepository.getNewsByKeyword(searchText))
                } catch (e: IOException) {
                    HomeUiState.Error
                } catch (e: HttpException) {
                    HomeUiState.Error
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as NuntiumApplication)
                val newsRepository = application.container.newsRepository
                HomeViewModel(newsRepository = newsRepository)
            }
        }
    }
}

