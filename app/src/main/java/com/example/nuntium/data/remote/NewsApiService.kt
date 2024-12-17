package com.example.nuntium.data.remote

import com.example.nuntium.data.model.NewsData
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


const val apiKey = "ff10e9671ecf4911a8f15a2974244df1"
const val responseSize = 20
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