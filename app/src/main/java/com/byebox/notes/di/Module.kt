package com.byebox.notes.di

import com.bridge.androidtechnicaltest.persistence.INewsRepository
import com.bridge.androidtechnicaltest.persistence.NewsReporterRepository
import com.byebox.notes.persistence.DatabaseFactory
import com.byebox.notes.ui.notes.AddNewsReporterViewModel
import com.byebox.notes.ui.notes.NewsReporterListViewModel
import com.byebox.notes.ui.notes.UpdateNewsReporterViewModel
import org.koin.android.viewmodel.dsl.viewModel

import org.koin.dsl.module




val databaseModule = module {
    //factory return a new instance every single time
    factory { DatabaseFactory.getDBInstance(get()) }
    //Return one instance
    single<INewsRepository> { NewsReporterRepository(get()) }

}

val viewModelModule = module {
    viewModel { NewsReporterListViewModel(get()) }
    viewModel { AddNewsReporterViewModel(get()) }
    viewModel { UpdateNewsReporterViewModel(get()) }
}
