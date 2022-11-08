package com.bagus.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bagus.moviecatalogue.data.source.MoviesRepository
import com.bagus.moviecatalogue.data.source.local.entity.MovieEntity
import com.bagus.moviecatalogue.data.source.local.entity.TvshowEntity
import com.bagus.moviecatalogue.utils.DataDummy
import com.bagus.moviecatalogue.vo.Resource

class TvshowViewModel(private val moviesRepository: MoviesRepository): ViewModel() {
    fun getAllTvshow(sort: String): LiveData<Resource<PagedList<TvshowEntity>>> = moviesRepository.getAllTvshows(sort)
}