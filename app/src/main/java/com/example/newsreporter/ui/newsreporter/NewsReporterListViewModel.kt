package com.example.newsreporter.ui.newsreporter

import androidx.lifecycle.*
import com.bridge.androidtechnicaltest.persistence.INewsRepository
import com.example.newsreporter.models.NewReporter
import com.example.newsreporter.utils.Resource


class NewsReporterListViewModel(val repo: INewsRepository) : ViewModel() {
    val TAG = "NewsReporterListViewModel"

    fun getNewsReportedList() : LiveData<Resource<List<NewReporter>>>{
        return repo.getOrFetchNewsReporter()
    }

    fun remove(newReporter: NewReporter) : LiveData<Resource<NewReporter>>{
        return repo.remove(newReporter)
    }


}


