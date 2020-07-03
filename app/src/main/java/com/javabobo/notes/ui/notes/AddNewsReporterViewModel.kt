package com.javabobo.notes.ui.notes

import androidx.lifecycle.*
import com.bridge.androidtechnicaltest.persistence.INewsRepository
import com.javabobo.notes.models.NewReporter
import com.javabobo.notes.utils.Resource


class AddNewsReporterViewModel(val repo: INewsRepository) : ViewModel() {
    val TAG = "AddNewsReporterViewModel"

    fun insertNewsReportedList(newReporter: NewReporter) : LiveData<Resource<NewReporter>>{
        return repo.insertNew(newReporter)
    }


}