package com.example.nuntium.ui.screens.favorites

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nuntium.data.model.Article
import com.example.nuntium.data.model.ArticleDto
import com.example.nuntium.data.model.Source
import com.example.nuntium.data.repository.NewsRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val favoritesUiState: StateFlow<FavoritesUiState> = newsRepository.getAllLocalNews()
        .map { FavoritesUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FavoritesUiState()
        )

    fun setArticle(article: ArticleDto) {
        viewModelScope.launch {
            val articleObj = Gson().toJson(article.toArticle())
            savedStateHandle["Article"] = articleObj
        }
    }

    fun deleteArticle(article: ArticleDto) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.deleteLocalArticle(article)
        }
    }

}
private fun ArticleDto.toArticle(): Article =
    Article(
        source = Source(this.source, this.source),
        content = this.content,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt
    )
data class FavoritesUiState(val articles: List<ArticleDto> = listOf())