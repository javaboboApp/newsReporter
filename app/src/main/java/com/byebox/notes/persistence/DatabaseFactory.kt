package com.byebox.notes.persistence

import android.content.Context
import androidx.room.Room
import com.bridge.androidtechnicaltest.persistence.AppDatabase

object DatabaseFactory {

    fun getDBInstance(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "newsReporter")
            .fallbackToDestructiveMigration()
            .build()

}