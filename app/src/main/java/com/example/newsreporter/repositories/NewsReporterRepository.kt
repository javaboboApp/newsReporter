package com.bridge.androidtechnicaltest.persistence

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsreporter.AppExecutors
import com.example.newsreporter.models.NewReporter
import com.example.newsreporter.utils.Constants.ERROR_INSERTING_DB_MSG
import com.example.newsreporter.utils.Constants.ERROR_REMOVING_DB_MSG
import com.example.newsreporter.utils.Constants.ERROR_UPDATING_DB_MSG
import com.example.newsreporter.utils.Event
import com.example.newsreporter.utils.Resource


interface INewsRepository {
    fun getOrFetchNewsReporter(): LiveData<Resource<List<NewReporter>>>
    fun insertNew(newReporter: NewReporter): LiveData<Resource<NewReporter>>
    fun remove(newReporter: NewReporter): LiveData<Resource<NewReporter>>
    fun update(newReporter: NewReporter): LiveData<Resource<NewReporter>>
}

class NewsReporterRepository(val database: AppDatabase) : INewsRepository {

    private val TAG = "NewsReporterRepository"

    override fun getOrFetchNewsReporter(): LiveData<Resource<List<NewReporter>>> {
        val result = MediatorLiveData<Resource<List<NewReporter>>>()

        result.addSource(database.newsDao.getNewsReport()) { listNewsReporters ->
            result.removeSource(result)
            result.value = Resource.success(Event(listNewsReporters))
        }

        return result
    }

    override fun insertNew(newReporter: NewReporter): LiveData<Resource<NewReporter>> {
        val result = MutableLiveData<Resource<NewReporter>>()

        AppExecutors.instance?.diskIO()?.execute {
            try {
                val valueReturned = database.newsDao.insertNewsReport(newReporter)
                if (valueReturned != -1L) {
                    newReporter.newsReportId = valueReturned
                    result.postValue(Resource.success(Event(newReporter)))
                    return@execute
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            result.postValue(Resource.error(ERROR_INSERTING_DB_MSG, null))
        }

        return result
    }

    override fun remove(newReporter: NewReporter): LiveData<Resource<NewReporter>> {
        val result = MutableLiveData<Resource<NewReporter>>()

        AppExecutors.instance?.diskIO()?.execute {
            try {


                val valueReturned = database.newsDao.removeNewsReport(newReporter)
                if (valueReturned != -1) {
                    result.postValue(Resource.success(Event(newReporter)))
                    return@execute
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            result.postValue(Resource.error(ERROR_REMOVING_DB_MSG, null))
        }

        return result
    }

    override fun update(newReporter: NewReporter): LiveData<Resource<NewReporter>> {
        val result = MutableLiveData<Resource<NewReporter>>()

        AppExecutors.instance?.diskIO()?.execute {
            try {
                val valueReturned = database.newsDao.updateNewsReport(newReporter)


                if (valueReturned != -1) {
                    result.postValue(Resource.success(Event(newReporter)))
                    return@execute
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            result.postValue(Resource.error(ERROR_UPDATING_DB_MSG, null))
        }

        return result
    }


}