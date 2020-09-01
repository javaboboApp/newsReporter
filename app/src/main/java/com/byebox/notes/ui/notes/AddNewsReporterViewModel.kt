package com.byebox.notes.ui.notes

import androidx.lifecycle.*
import com.bridge.androidtechnicaltest.persistence.INewsRepository
import com.byebox.notes.models.NewReporter
import com.byebox.notes.utils.Resource


class AddNewsReporterViewModel(val repo: INewsRepository) : ViewModel() {
    val TAG = "AddNewsReporterViewModel"

    fun insertNewsReportedList(newReporter: NewReporter) : LiveData<Resource<NewReporter>>{
        return repo.insertNew(newReporter)
    }


}