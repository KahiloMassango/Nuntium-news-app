package com.example.nuntium.data.model

import com.google.gson.annotations.SerializedName

data class Data(
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
    val url: String
)

val DataTest = Data(
     author = "Author",
     category = "category",
     country = "country",
     description = "description",
     image = "image",
     language = "language",
     publishedAt = "publishedAt",
     source = "source",
     title = "title",
     url = "url"
)