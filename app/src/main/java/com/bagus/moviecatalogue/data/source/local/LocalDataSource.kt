package com.bagus.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.bagus.moviecatalogue.data.source.local.entity.MovieEntity
import com.bagus.moviecatalogue.data.source.local.entity.TvshowEntity
import com.bagus.moviecatalogue.data.source.local.room.MovieDao
import com.bagus.moviecatalogue.utils.SortUtils
import com.bagus.moviecatalogue.utils.SortUtils.MOVIES
import com.bagus.moviecatalogue.utils.SortUtils.TVSHOW

class LocalDataSource (private val mmovieDao: MovieDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }

    fun getAllMovies(sort: String): DataSource.Factory<Int, MovieEntity>  = mmovieDao.getAllMovies(
        SortUtils.getSortedQuery(sort,MOVIES))

    fun getMovieDetail(id: Int): LiveData<MovieEntity> =
        mmovieDao.getMovieDetail(id)

    fun insertMovies(movies: List<MovieEntity>) = mmovieDao.insertMovies(movies)

    fun updateMovie(movieEntity: MovieEntity, newState: Boolean) {
        movieEntity.favorite = newState
        mmovieDao.updateMovie(movieEntity)
    }
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = mmovieDao.getFavoriteMovie()

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.favorite = newState
        mmovieDao.updateMovie(movie)
    }

    fun getAllTvshows(sort: String): DataSource.Factory<Int, TvshowEntity> = mmovieDao.getAllTvshow(SortUtils.getSortedQuery(sort,
        TVSHOW))

    fun getTvshowDetail(id: Int): LiveData<TvshowEntity> =
        mmovieDao.getTvshowDetail(id)

    fun insertTvshows(tvshow: List<TvshowEntity>) = mmovieDao.insertTvshow(tvshow)

    fun updateTvshow(tvshowEntity: TvshowEntity) {
        mmovieDao.updateTvshow(tvshowEntity)
    }

    fun getFavoriteTvshows(): DataSource.Factory<Int, TvshowEntity> = mmovieDao.getFavoriteTvshow()

    fun setFavoriteTvshows(tvshow: TvshowEntity, newState: Boolean) {
        tvshow.favorite = newState
        mmovieDao.updateTvshow(tvshow)
    }
}