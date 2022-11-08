package com.bagus.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bagus.moviecatalogue.data.source.MoviesRepository
import com.bagus.moviecatalogue.data.source.local.entity.MovieEntity
import com.bagus.moviecatalogue.data.source.local.entity.TvshowEntity
import com.bagus.moviecatalogue.utils.DataDummy
import com.bagus.moviecatalogue.vo.Resource

class DetailTvshowViewModel(private val moviesRepository: MoviesRepository): ViewModel() {
    fun getTvshowDetail(id: Int): LiveData<Resource<TvshowEntity>> = moviesRepository.getTvshowDetail(id)

    fun setFavoriteTvshow(tvshowEntity: TvshowEntity) {
        if (tvshowEntity != null) {
            val newState = !tvshowEntity.favorite
            moviesRepository.setFavoriteTvshows(tvshowEntity, newState)
        }
    }
}