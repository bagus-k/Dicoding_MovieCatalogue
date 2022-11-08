package com.bagus.moviecatalogue.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bagus.moviecatalogue.data.source.MoviesRepository
import com.bagus.moviecatalogue.data.source.local.entity.MovieEntity
import com.bagus.moviecatalogue.data.source.local.entity.TvshowEntity

class FavoriteTvshowViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    fun getFavorite(): LiveData<PagedList<TvshowEntity>> {
        return moviesRepository.getFavoriteTvshows()
    }
    fun setFavorite(tvshowEntity: TvshowEntity) {
        val newState = !tvshowEntity.favorite
        moviesRepository.setFavoriteTvshows(tvshowEntity, newState)
    }
}