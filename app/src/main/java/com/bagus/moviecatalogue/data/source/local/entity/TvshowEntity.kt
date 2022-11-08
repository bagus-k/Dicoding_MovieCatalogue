package com.bagus.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tvshowentities")

data class TvshowEntity(
    @ColumnInfo(name = "overview")
    val overview: String? = null,

    @ColumnInfo(name = "releaseDate")
    val releaseDate: String? = null,

    @ColumnInfo(name = "voteAverage")
    val voteAverage: Double? = null,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int=0,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "posterPath")
    val posterPath: String? = null,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)
