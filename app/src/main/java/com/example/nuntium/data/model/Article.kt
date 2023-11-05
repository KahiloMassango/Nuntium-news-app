package com.example.nuntium.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Article(
    val id: Int,
    val source: Source,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    @SerializedName("published_at")
    val publishedAt: String? = null,
    val content: String,
)

@Entity(tableName = "articles")
data class ArticleDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val source: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    @ColumnInfo("published_at")
    val publishedAt: String? = null,
    val content: String,
)

fun Article.toArticleDto(): ArticleDto = ArticleDto(
    source = this.source.name,
    title = this.title,
    description = this.description,
    url = this.url,
    urlToImage = this.urlToImage,
    publishedAt = this.publishedAt,
    content = this.content,
)
