package com.example.nuntium.data.local

import com.example.nuntium.data.model.ArticleDto
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {

    suspend fun insertArticle(article: ArticleDto)

    suspend fun deleteArticle(article: ArticleDto)

    suspend fun getArticleById(id: Int): ArticleDto

    fun getAll(): Flow<List<ArticleDto>>
}

class NewsLocalDataSourceImpl(
    private val articleDao: ArticleDao
): NewsLocalDataSource {
    override suspend fun insertArticle(article: ArticleDto) {
        articleDao.insertArticle(article)
    }

    override suspend fun deleteArticle(article: ArticleDto) {
        articleDao.deleteArticle(article)
    }

    override suspend fun getArticleById(id: Int): ArticleDto {
        return articleDao.getArticleById(id)
    }

    override fun getAll(): Flow<List<ArticleDto>> {
        return articleDao.getAll()
    }
}