package com.bagus.moviecatalogue.ui.favorite.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bagus.moviecatalogue.data.source.MoviesRepository
import com.bagus.moviecatalogue.data.source.local.entity.MovieEntity
import com.bagus.moviecatalogue.vo.Resource

class FavoriteMovieViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    fun getFavorite(): LiveData<PagedList<MovieEntity>> {
        return moviesRepository.getFavoriteMovies()
    }
    fun setFavorite(movieEntity: MovieEntity) {
        val newState = !movieEntity.favorite
        moviesRepository.setFavoriteMovies(movieEntity, newState)
    }
}