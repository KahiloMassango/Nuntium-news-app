package com.example.nuntium.ui.screens.article

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nuntium.data.model.Article
import com.example.nuntium.data.model.toArticleDto
import com.example.nuntium.data.repository.NewsRepository
import com.example.nuntium.di.CustomHandler
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    handler: CustomHandler
): ViewModel() {

    private val json = handler.savedStateHandle.get<String>("Article")
    private val articleObj = Gson().fromJson(json, Article::class.java)
    init {
        Log.d("article" ,"JSON:  $json ")
        Log.d("article" ,"ARTICLE-OBJ: $articleObj ")
    }
    val uiState: Article? by mutableStateOf(articleObj)

    fun saveArticleLocally(article: Article){
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.saveLocalArticle(article.toArticleDto())
        }
    }

}
