package com.example.newsreporter.di

import com.bridge.androidtechnicaltest.persistence.INewsRepository
import com.bridge.androidtechnicaltest.persistence.NewsReporterRepository
import com.example.newsreporter.persistence.DatabaseFactory
import com.example.newsreporter.ui.newsreporter.AddNewsReporterViewModel
import com.example.newsreporter.ui.newsreporter.NewsReporterListViewModel
import com.example.newsreporter.ui.newsreporter.UpdateNewsReporterViewModel
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
