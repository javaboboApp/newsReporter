package com.bridge.androidtechnicaltest.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.javabobo.notes.models.NewReporter

@Dao
interface NewsDao {

    @Query("SELECT * FROM NewReporter ORDER BY  timestamp DESC ")
    fun getNewsReport(): LiveData<List<NewReporter>>

    @Insert
    fun insertNewsReport(newReporter: NewReporter): Long

    @Delete
    fun removeNewsReport(newReporter: NewReporter) : Int

    @Update
    fun updateNewsReport(newReporter: NewReporter) : Int



}