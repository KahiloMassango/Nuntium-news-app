package com.example.nuntium.ui.screens.article

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nuntium.data.model.Article
import com.example.nuntium.data.model.Source
import com.example.nuntium.data.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ArticleViewModel(private val newsRepository: NewsRepository): ViewModel() {

    val article = Article(
        id = 1234,
        source = Source("bbc", "NewsSource"),
        title = "Random Article Title",
        description = "This is a randomly generated article description.",
        url = "https://example.com/random-article",
        urlToImage = "https://example.com/random-image.jpg",
        publishedAt = "2023-11-04T10:00:00Z",
        content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    )


    var uiState by mutableStateOf(article)
        private set


    private fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
            uiState = article
        }
    }

}