package com.byebox.notes.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "NewReporter")
data class NewReporter(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var newsReportId: Long = 0,

    @ColumnInfo(name = "name")
    var title: String?,

    @ColumnInfo(name = "country")
    var history: String?

    ,
    @ColumnInfo(name = "timestamp")

    var timestamp: Long

) : Parcelable