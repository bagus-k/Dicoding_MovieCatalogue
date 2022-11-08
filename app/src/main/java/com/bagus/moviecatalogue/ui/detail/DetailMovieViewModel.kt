package com.bagus.moviecatalogue.ui.detail

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bagus.moviecatalogue.data.source.MoviesRepository
import com.bagus.moviecatalogue.data.source.local.entity.MovieEntity
import com.bagus.moviecatalogue.utils.DataDummy
import com.bagus.moviecatalogue.vo.Resource

class DetailMovieViewModel(private val moviesRepository: MoviesRepository): ViewModel() {

    fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>> = moviesRepository.getMovieDetail(movieId)

    fun setFavoriteMovie(movieEntity: MovieEntity) {
        if (movieEntity != null) {
                val newState = !movieEntity.favorite
                moviesRepository.setFavoriteMovies(movieEntity, newState)
        }
    }
}