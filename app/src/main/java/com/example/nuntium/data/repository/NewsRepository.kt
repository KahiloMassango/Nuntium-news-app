package com.example.nuntium.data.repository

import com.example.nuntium.data.api.NewsApiService
import com.example.nuntium.data.model.Data
import com.example.nuntium.data.model.News

interface NewsRepository {
    suspend fun getNewsByCategory(category: String): List<Data>

    suspend fun getNewsByKeyword(keyword: String): List<Data>

    suspend fun getRecommendedNews(): List<Data>
}

class NetworkNewsRepository(
    private val newsApi: NewsApiService
): NewsRepository {
    override suspend fun getNewsByCategory(category: String): List<Data> {
        return newsApi.getNewsByCategory(category).data
    }

    override suspend fun getNewsByKeyword(keyword: String): List<Data> {
        return newsApi.getNewsByKeyword(keyword).data
    }

    override suspend fun getRecommendedNews(): List<Data> {
        return newsApi.getRecommendedNews().data
    }

}