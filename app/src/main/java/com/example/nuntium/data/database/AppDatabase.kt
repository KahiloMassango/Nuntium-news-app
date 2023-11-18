package com.example.nuntium.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nuntium.data.model.ArticleDto

@Database(entities = [ArticleDto::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context = context, AppDatabase::class.java, "article_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}