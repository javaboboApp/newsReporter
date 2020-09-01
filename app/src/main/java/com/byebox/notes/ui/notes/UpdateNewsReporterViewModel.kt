package com.byebox.notes.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bridge.androidtechnicaltest.persistence.INewsRepository
import com.byebox.notes.models.NewReporter
import com.byebox.notes.utils.Resource

class UpdateNewsReporterViewModel (val repo: INewsRepository) : ViewModel() {

    fun updateNewsReporter(newReporter: NewReporter): LiveData<Resource<NewReporter>> {
        return repo.update(newReporter)
    }

}