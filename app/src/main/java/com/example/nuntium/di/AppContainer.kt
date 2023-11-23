package com.example.nuntium.di

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import com.example.nuntium.data.local.AppDatabase
import com.example.nuntium.data.local.ArticleDao
import com.example.nuntium.data.local.NewsLocalDataSource
import com.example.nuntium.data.local.NewsLocalDataSourceImpl
import com.example.nuntium.data.remote.NewsApiService
import com.example.nuntium.data.remote.NewsRemoteDataSource
import com.example.nuntium.data.remote.NewsRemoteDataSourceImpl
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


interface CustomHandler {
    val savedStateHandle: SavedStateHandle
}
class HandlerImpl(handler: SavedStateHandle): CustomHandler {
    override val savedStateHandle = handler
}

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
    fun provideSavedStateHandle(): CustomHandler {
        return HandlerImpl(SavedStateHandle())
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