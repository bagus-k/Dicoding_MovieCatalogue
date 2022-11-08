package com.bagus.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.bagus.moviecatalogue.data.source.local.entity.MovieEntity
import com.bagus.moviecatalogue.data.source.local.entity.TvshowEntity

@Dao
interface MovieDao {

    //Movies
    @RawQuery(observedEntities = [MovieEntity::class])
    fun getAllMovies(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @Transaction
    @Query("SELECT * FROM movieentities WHERE id = :id")
    fun getMovieDetail(id: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Query("SELECT * FROM movieentities where favorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>


    //TV Shows
    @RawQuery(observedEntities = [TvshowEntity::class])
    fun getAllTvshow(query: SupportSQLiteQuery): DataSource.Factory<Int, TvshowEntity>

    @Transaction
    @Query("SELECT * FROM tvshowentities WHERE id = :id")
    fun getTvshowDetail(id: Int): LiveData<TvshowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvshow(movies: List<TvshowEntity>)

    @Update
    fun updateTvshow(movie: TvshowEntity)

    @Query("SELECT * FROM tvshowentities where favorite = 1")
    fun getFavoriteTvshow(): DataSource.Factory<Int, TvshowEntity>
}