package com.example.nuntium.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nuntium.data.model.Article
import com.example.nuntium.data.model.toArticleDto
import com.example.nuntium.data.repository.NewsRepository
import com.example.nuntium.di.CustomHandler
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


sealed interface HomeUiState {
    data class Success(val newsList: List<Article>?) : HomeUiState
    data object Error : HomeUiState
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val handler: CustomHandler
): ViewModel() {

    private val _uiState: MutableStateFlow<List<Article>?> = MutableStateFlow(emptyList())
    var uiState = _uiState.asStateFlow()

    init {
        getRecommendedNews()
    }

    fun setArticle(article: Article) {
        viewModelScope.launch {
            val articleObj = Gson().toJson(article)!!
            handler.savedStateHandle["Article"] = articleObj
        }
    }

    var selectedCategory by mutableStateOf("General")
        private set

    fun saveArticleLocally(article: Article){
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.saveLocalArticle(article.toArticleDto())
        }
    }

    fun updateCategory(category: String) {
        if (category != selectedCategory) {
            selectedCategory = category
            getNewsByCategory()
        }
    }

    var searchText by mutableStateOf("")
        private set

    fun updateSearchText(value: String) {
        searchText = value
    }

    private fun getNewsByCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = try {
                    newsRepository.fetchRemoteNewsByCategory(selectedCategory)
            } catch (e: IOException) {
                emptyList()
            } catch (e: HttpException) {
                emptyList()
            }
        }
    }

    private fun getRecommendedNews() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = try {
                newsRepository.fetchRemoteLatestNews()
            } catch (e: IOException) {
                emptyList()
            } catch (e: HttpException) {
                emptyList()
            }
        }
    }

    fun searchNewsByKeyword() {
        viewModelScope.launch(Dispatchers.IO) {
            selectedCategory = "General"
            if(searchText.isNotEmpty()){
                _uiState.value = try {
                    newsRepository.fetchRemoteNewsByKeyword(searchText)
                } catch (e: IOException) {
                    emptyList()
                } catch (e: HttpException) {
                    emptyList()
                }
            }
        }
    }
}

