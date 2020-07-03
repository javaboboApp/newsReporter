package com.javabobo.notes.ui.notes

import androidx.lifecycle.*
import com.bridge.androidtechnicaltest.persistence.INewsRepository
import com.javabobo.notes.models.NewReporter
import com.javabobo.notes.utils.Resource


class NewsReporterListViewModel(val repo: INewsRepository) : ViewModel() {
    val TAG = "NewsReporterListViewModel"

    fun getNewsReportedList() : LiveData<Resource<List<NewReporter>>>{
        return repo.getOrFetchNewsReporter()
    }

    fun remove(newReporter: NewReporter) : LiveData<Resource<NewReporter>>{
        return repo.remove(newReporter)
    }


}


