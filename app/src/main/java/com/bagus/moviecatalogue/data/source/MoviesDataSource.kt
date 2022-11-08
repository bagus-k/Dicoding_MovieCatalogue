package com.bagus.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.bagus.moviecatalogue.data.source.local.entity.MovieEntity
import com.bagus.moviecatalogue.data.source.local.entity.TvshowEntity
import com.bagus.moviecatalogue.vo.Resource

interface MoviesDataSource {
    fun getAllMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>>
    fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>>
    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>
    fun setFavoriteMovies(movie: MovieEntity, state: Boolean)


    fun getAllTvshows(sort: String): LiveData<Resource<PagedList<TvshowEntity>>>
    fun getTvshowDetail(tvshowId: Int): LiveData<Resource<TvshowEntity>>
    fun getFavoriteTvshows(): LiveData<PagedList<TvshowEntity>>
    fun setFavoriteTvshows(tvshow: TvshowEntity, state: Boolean)
}