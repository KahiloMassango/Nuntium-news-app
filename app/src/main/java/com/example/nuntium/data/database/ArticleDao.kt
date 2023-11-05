package com.example.nuntium.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nuntium.data.model.ArticleDto
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleDto)

    @Delete
    suspend fun deleteArticle(article: ArticleDto)

    @Query("SELECT * FROM articles WHERE id = :id")
    suspend fun getArticleById(id: Int): ArticleDto

    @Query("SELECT * FROM articles")
    fun getAll(): Flow<List<ArticleDto>>



}