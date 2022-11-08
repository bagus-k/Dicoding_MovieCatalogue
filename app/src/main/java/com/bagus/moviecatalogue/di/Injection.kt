package com.bagus.moviecatalogue.di

import android.content.Context
import com.bagus.moviecatalogue.data.source.MoviesRepository
import com.bagus.moviecatalogue.data.source.local.LocalDataSource
import com.bagus.moviecatalogue.data.source.local.room.MovieDatabase
import com.bagus.moviecatalogue.data.source.remote.RemoteDataSource
import com.bagus.moviecatalogue.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MoviesRepository {

        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()

        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MoviesRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}