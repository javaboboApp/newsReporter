package com.byebox.notes.ui.notes

import androidx.lifecycle.*
import com.bridge.androidtechnicaltest.persistence.INewsRepository
import com.byebox.notes.models.NewReporter
import com.byebox.notes.utils.Resource


class NewsReporterListViewModel(val repo: INewsRepository) : ViewModel() {
    val TAG = "NewsReporterListViewModel"

    fun getNewsReportedList() : LiveData<Resource<List<NewReporter>>>{
        return repo.getOrFetchNewsReporter()
    }

    fun remove(newReporter: NewReporter) : LiveData<Resource<NewReporter>>{
        return repo.remove(newReporter)
    }


}


