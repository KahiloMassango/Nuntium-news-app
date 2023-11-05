package com.example.nuntium.data.model

data class NewsData(
    val status: String,
    val articles: List<Article>,
    val totalResults: Int
)