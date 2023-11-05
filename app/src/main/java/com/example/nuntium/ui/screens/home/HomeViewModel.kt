package com.example.nuntium.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.nuntium.data.model.Article
import com.example.nuntium.data.model.toArticleDto
import com.example.nuntium.data.repository.NewsRepository
import com.example.nuntium.di.NuntiumApplication
import com.example.nuntium.ui.screens.article.ArticleViewModel
import com.example.nuntium.ui.screens.favorites.FavoritesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface HomeUiState {
    data class Success(val newsList: List<Article>) : HomeUiState
    data object Error : HomeUiState
    data object Loading : HomeUiState
}

class HomeViewModel(
    private val newsRepository: NewsRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    var uiState = _uiState.asStateFlow()

    init {
        getRecommendedNews()
    }

    var selectedCategory by mutableStateOf("General")
        private set

    fun addToFavorite(article: Article){
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.saveLocalArticle(article.toArticleDto())
        }
    }

    fun updateCategory(category: String) {
        selectedCategory = category
        getNewsByCategory()
    }

    var searchText by mutableStateOf("")
        private set

    fun updateSearchText(value: String) {
        searchText = value
    }

    private fun getNewsByCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            searchText = ""
            _uiState.value = try {
                HomeUiState.Success(
                    newsList = newsRepository.fetchRemoteNewsByCategory(selectedCategory)
                )
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    fun getRecommendedNews() {
        viewModelScope.launch(Dispatchers.IO) {
                _uiState.value = try {
                HomeUiState.Success(newsList = newsRepository.fetchRemoteLatestNews())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    fun getNewsByKeyword() {
        viewModelScope.launch(Dispatchers.IO) {
            selectedCategory = "General"
            if(searchText.isNotEmpty()){
                _uiState.value = try {
                    HomeUiState.Success(newsRepository.fetchRemoteNewsByKeyword(searchText))
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
            initializer {
                val application = (this[APPLICATION_KEY] as NuntiumApplication)
                val newsRepository = application.container.newsRepository
                ArticleViewModel(newsRepository = newsRepository)
            }
            initializer {
                val application = (this[APPLICATION_KEY] as NuntiumApplication)
                val newsRepository = application.container.newsRepository
                FavoritesViewModel(newsRepository = newsRepository)
            }
        }
    }
}

