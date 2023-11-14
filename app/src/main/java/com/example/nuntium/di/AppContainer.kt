package com.example.nuntium.di

import android.content.Context
import androidx.room.RoomDatabase
import com.example.nuntium.data.database.AppDatabase
import com.example.nuntium.data.database.ArticleDao
import com.example.nuntium.data.database.NewsLocalDataSource
import com.example.nuntium.data.database.NewsLocalDataSourceImpl
import com.example.nuntium.data.network.NewsApiService
import com.example.nuntium.data.network.NewsRemoteDataSource
import com.example.nuntium.data.network.NewsRemoteDataSourceImpl
import com.example.nuntium.data.repository.NewsRepository
import com.example.nuntium.data.repository.NewsRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
   val newsRepository: NewsRepository
}

class AppDefaultContainer(context: Context): AppContainer {
    private val baseUrl = "https://newsapi.org/v2/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
    private val databaseService: ArticleDao by lazy {
        AppDatabase.getDatabase(context).articleDao()
    }

    private val localDataSource: NewsLocalDataSource by lazy {
        NewsLocalDataSourceImpl(databaseService)
    }

    private val remoteDataSource: NewsRemoteDataSource by lazy {
        NewsRemoteDataSourceImpl(retrofitService)
    }

    override val newsRepository: NewsRepository by lazy {
        NewsRepositoryImpl(localDataSource = localDataSource, remoteDataSource = remoteDataSource)
    }
}