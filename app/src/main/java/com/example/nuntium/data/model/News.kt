package com.example.nuntium.data.model

import com.google.gson.annotations.SerializedName

data class News(
    val id: Int,
    val author: String,
    val category: String,
    val country: String,
    val description: String,
    val image: String,
    val language: String,
    @SerializedName("published_at")
    val publishedAt: String,
    val source: String,
    val title: String,
    val url: String,
    val bookmarked: Boolean
)

val DataTest = listOf(
    News(
        id = 0,
        author = "Author",
        category = "category",
        country = "country",
        description = "description",
        image = "image",
        language = "language",
        publishedAt = "publishedAt",
        source = "source",
        title = "title",
        url = "url",
        bookmarked = false
    ),
    News(
        id = 0,
        author = "Author",
        category = "category",
        country = "country",
        description = "description",
        image = "image",
        language = "language",
        publishedAt = "publishedAt",
        source = "source",
        title = "title",
        url = "url",
        bookmarked = false
    ),
    News(
        id = 0,
        author = "Author",
        category = "category",
        country = "country",
        description = "description",
        image = "image",
        language = "language",
        publishedAt = "publishedAt",
        source = "source",
        title = "title",
        url = "url",
        bookmarked = false
    ),
)