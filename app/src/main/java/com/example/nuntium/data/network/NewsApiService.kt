package com.example.nuntium.data.network

import com.example.nuntium.data.model.NewsData
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


const val apiKey = "API_KEY"
const val responseSize = 2
interface NewsApiService {

    @Headers("X-Api-Key: $apiKey")
    @GET("top-headlines?pageSize=$responseSize")
    suspend fun fetchNewsByCategory(@Query("q") q: String): NewsData

    @Headers("X-Api-Key: $apiKey")
    @GET("top-headlines?pageSize=$responseSize")
    suspend fun fetchNewsByKeyword(@Query("q") keyword: String): NewsData

    @Headers("X-Api-Key: $apiKey")
    @GET("top-headlines?sources=bbc-news&language=en&pageSize=$responseSize")
    suspend fun fetchLatestNews(): NewsData


}