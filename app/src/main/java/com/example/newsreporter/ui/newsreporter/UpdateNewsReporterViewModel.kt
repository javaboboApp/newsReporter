package com.example.newsreporter.ui.newsreporter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bridge.androidtechnicaltest.persistence.INewsRepository
import com.example.newsreporter.models.NewReporter
import com.example.newsreporter.utils.Resource

class UpdateNewsReporterViewModel (val repo: INewsRepository) : ViewModel() {

    fun updateNewsReporter(newReporter: NewReporter): LiveData<Resource<NewReporter>> {
        return repo.update(newReporter)
    }

}