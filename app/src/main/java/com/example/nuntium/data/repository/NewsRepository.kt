package com.example.nuntium.data.repository

import com.example.nuntium.data.api.NewsApiService
import com.example.nuntium.data.model.News

interface NewsRepository {
    suspend fun getNewsByCategory(category: String): List<News>

    suspend fun getNewsByKeyword(keyword: String): List<News>

    suspend fun getRecommendedNews(): List<News>
}

class NetworkNewsRepository(
    private val newsApi: NewsApiService
): NewsRepository {
    override suspend fun getNewsByCategory(category: String): List<News> {
        return newsApi.getNewsByCategory(category).data
    }

    override suspend fun getNewsByKeyword(keyword: String): List<News> {
        return newsApi.getNewsByKeyword(keyword).data
    }

    override suspend fun getRecommendedNews(): List<News> {
        return newsApi.getRecommendedNews().data
    }

}