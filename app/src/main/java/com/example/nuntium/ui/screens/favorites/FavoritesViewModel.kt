package com.example.nuntium.ui.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nuntium.data.model.ArticleDto
import com.example.nuntium.data.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class FavoritesViewModel(private val newsRepository: NewsRepository): ViewModel() {

    val favoritesUiState: StateFlow<FavoritesUiState> = newsRepository.getAllLocalNews()
        .map { FavoritesUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FavoritesUiState()
        )

    fun deleteArticle(article: ArticleDto) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.deleteLocalArticle(article)
        }
    }

}

data class FavoritesUiState(val itemList: List<ArticleDto> = listOf())