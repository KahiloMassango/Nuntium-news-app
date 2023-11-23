package com.example.nuntium.data.repository

import com.example.nuntium.data.local.NewsLocalDataSource
import com.example.nuntium.data.model.Article
import com.example.nuntium.data.model.ArticleDto
import com.example.nuntium.data.remote.NewsRemoteDataSource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun saveLocalArticle(article: ArticleDto)

    suspend fun deleteLocalArticle(article: ArticleDto)

    suspend fun getLocalArticleById(id: Int): ArticleDto

    fun getAllLocalNews(): Flow<List<ArticleDto>>

    suspend fun fetchRemoteNewsByCategory(category: String): List<Article>

    suspend fun fetchRemoteNewsByKeyword(keyword: String): List<Article>

    suspend fun fetchRemoteLatestNews(): List<Article>

}

class NewsRepositoryImpl(
    private val localDataSource: NewsLocalDataSource,
    private val remoteDataSource: NewsRemoteDataSource
): NewsRepository {
    override suspend fun saveLocalArticle(article: ArticleDto) {
        localDataSource.insertArticle(article)
    }

    override suspend fun deleteLocalArticle(article: ArticleDto) {
        localDataSource.deleteArticle(article)
    }

    override suspend fun getLocalArticleById(id: Int): ArticleDto {
        return localDataSource.getArticleById(id)
    }

    override fun getAllLocalNews(): Flow<List<ArticleDto>> {
        return localDataSource.getAll()
    }

    override suspend fun fetchRemoteNewsByCategory(category: String): List<Article> {
        return remoteDataSource.fetchNewsByCategory(category)
    }

    override suspend fun fetchRemoteNewsByKeyword(keyword: String): List<Article> {
        return remoteDataSource.fetchNewsByKeyword(keyword)
    }

    override suspend fun fetchRemoteLatestNews(): List<Article> {
        return remoteDataSource.fetchLatestNews()
    }
}