package com.bagus.moviecatalogue.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bagus.moviecatalogue.data.source.MoviesRepository
import com.bagus.moviecatalogue.data.source.local.entity.MovieEntity
import com.bagus.moviecatalogue.utils.DataDummy
import com.bagus.moviecatalogue.vo.Resource

class MoviesViewModel(private val moviesRepository: MoviesRepository): ViewModel() {
fun getAllMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>> = moviesRepository.getAllMovies(sort)
}
