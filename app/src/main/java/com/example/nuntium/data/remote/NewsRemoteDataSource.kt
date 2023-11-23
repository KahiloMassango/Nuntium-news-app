package com.example.nuntium.data.remote

import com.example.nuntium.data.model.Article

interface NewsRemoteDataSource {
    suspend fun fetchNewsByCategory(category: String): List<Article>

    suspend fun fetchNewsByKeyword(keyword: String): List<Article>

    suspend fun fetchLatestNews(): List<Article>
}

class NewsRemoteDataSourceImpl(
    private val newsApi: NewsApiService
): NewsRemoteDataSource {
    override suspend fun fetchNewsByCategory(category: String): List<Article> {
        return newsApi.fetchNewsByCategory(category.lowercase()).articles
    }

    override suspend fun fetchNewsByKeyword(keyword: String): List<Article> {
        return newsApi.fetchNewsByKeyword(keyword).articles
    }

    override suspend fun fetchLatestNews(): List<Article> {
        return newsApi.fetchLatestNews().articles
    }

}