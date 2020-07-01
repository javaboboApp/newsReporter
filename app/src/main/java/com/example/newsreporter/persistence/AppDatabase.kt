package com.bridge.androidtechnicaltest.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsreporter.models.NewReporter

@Database(entities = [NewReporter::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract val newsDao: NewsDao
}