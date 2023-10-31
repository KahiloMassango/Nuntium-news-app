package com.example.nuntium.di

import com.example.nuntium.data.api.NewsApiService
import com.example.nuntium.data.repository.NetworkNewsRepository
import com.example.nuntium.data.repository.NewsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
   val newsRepository: NewsRepository
}

class AppDefaultContainer: AppContainer {
    private val baseUrl = "http://api.mediastack.com/v1/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }

    override val newsRepository: NewsRepository by lazy {
        NetworkNewsRepository(retrofitService)
    }
}