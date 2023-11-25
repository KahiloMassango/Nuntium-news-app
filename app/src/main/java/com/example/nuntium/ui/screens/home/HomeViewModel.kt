package com.example.nuntium.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nuntium.data.model.Article
import com.example.nuntium.data.model.toArticleDto
import com.example.nuntium.data.repository.NewsRepository
import com.example.nuntium.data.repository.WorkManagerRepository
import com.example.nuntium.di.CustomHandler
import com.example.nuntium.workers.OUTPUT_PATH
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val workManagerRepository: WorkManagerRepository,
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
            val name = UUID.randomUUID().toString()
            val fileName = "$OUTPUT_PATH/$name.jpg"
            val dto = article.copy(urlToImage = fileName)
            workManagerRepository.downloadImage(article.urlToImage, name)
            newsRepository.saveLocalArticle(dto.toArticleDto())
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
                null
            } catch (e: HttpException) {
                null
            }
        }
    }

    private fun getRecommendedNews() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = try {
                newsRepository.fetchRemoteLatestNews()
            } catch (e: IOException) {
                null
            } catch (e: HttpException) {
                null
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
                    null
                } catch (e: HttpException) {
                    null
                }
            }
        }
    }
}

