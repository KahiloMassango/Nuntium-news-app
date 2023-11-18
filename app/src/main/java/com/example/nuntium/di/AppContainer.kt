package com.example.nuntium.di

import android.content.Context
import com.example.nuntium.data.database.AppDatabase
import com.example.nuntium.data.database.ArticleDao
import com.example.nuntium.data.database.NewsLocalDataSource
import com.example.nuntium.data.database.NewsLocalDataSourceImpl
import com.example.nuntium.data.network.NewsApiService
import com.example.nuntium.data.network.NewsRemoteDataSource
import com.example.nuntium.data.network.NewsRemoteDataSourceImpl
import com.example.nuntium.data.repository.AppPreferencesRepository
import com.example.nuntium.data.repository.NewsRepository
import com.example.nuntium.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppContainer {

    @Provides
    @Singleton
    fun provideRetrofitService(): NewsApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://newsapi.org/v2/")
            .build()
            .create(NewsApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesDatabaseService(@ApplicationContext context: Context): ArticleDao {
        return AppDatabase.getDatabase(context).articleDao()
    }

    @Provides
    fun providesLocalDataSource(databaseService: ArticleDao): NewsLocalDataSource {
        return  NewsLocalDataSourceImpl(databaseService)
    }

    @Provides
    fun provideRemoteDataSource(retrofitService: NewsApiService): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(retrofitService)
    }

    @Provides
    fun provideAppPreferencesRepository(@ApplicationContext context: Context): AppPreferencesRepository {
        return AppPreferencesRepository(context)
    }

    @Provides
    fun provideNewsRepository(
        remoteDataSource: NewsRemoteDataSource,
        localDataSource: NewsLocalDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(localDataSource, remoteDataSource)
    }
}