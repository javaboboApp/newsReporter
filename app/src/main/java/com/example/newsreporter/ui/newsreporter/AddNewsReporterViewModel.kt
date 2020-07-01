package com.example.newsreporter.ui.newsreporter

import androidx.lifecycle.*
import com.bridge.androidtechnicaltest.persistence.INewsRepository
import com.example.newsreporter.models.NewReporter
import com.example.newsreporter.utils.Resource
import org.koin.android.viewmodel.ext.android.viewModel


class AddNewsReporterViewModel(val repo: INewsRepository) : ViewModel() {
    val TAG = "AddNewsReporterViewModel"

    fun insertNewsReportedList(newReporter: NewReporter) : LiveData<Resource<NewReporter>>{
        return repo.insertNew(newReporter)
    }


}