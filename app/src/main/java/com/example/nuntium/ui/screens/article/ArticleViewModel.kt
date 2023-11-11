package com.example.nuntium.ui.screens.article

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nuntium.data.model.Article
import com.example.nuntium.data.model.toArticleDto
import com.example.nuntium.data.repository.NewsRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ArticleViewModel(
    private val newsRepository: NewsRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val json = savedStateHandle.get<String>("Article")

    private val articleObj = Gson().fromJson(json, Article::class.java)

    val uiState: Article? by mutableStateOf(articleObj)

    fun saveArticleLocally(article: Article){
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.saveLocalArticle(article.toArticleDto())
        }
    }

}
