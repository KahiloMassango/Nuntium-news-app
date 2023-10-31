package com.example.nuntium.data.api

import com.example.nuntium.data.model.News
import retrofit2.http.GET
import retrofit2.http.Query


const val apiKey = "67996391eb853956378810bea63d15a8"
interface NewsApiService {

    @GET("news?limit=10")
    suspend fun getNewsByCategory(
        @Query("categories") category: String,
        @Query("access_key") accessKey: String = apiKey
    ): News

    @GET("news?limit=10")
    suspend fun getNewsByKeyword(
        @Query("keywords") keyword: String,
        @Query("access_key") accessKey: String = apiKey
    ): News

    @GET("news?limit=10&countries=us,gb,de,au")
    suspend fun getRecommendedNews(
        @Query("access_key") accessKey: String = apiKey
    ): News


}