package com.bagus.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.bagus.moviecatalogue.data.NetworkBoundResource
import com.bagus.moviecatalogue.data.source.MoviesDataSource
import com.bagus.moviecatalogue.data.source.local.LocalDataSource
import com.bagus.moviecatalogue.data.source.local.entity.MovieEntity
import com.bagus.moviecatalogue.data.source.local.entity.TvshowEntity
import com.bagus.moviecatalogue.data.source.remote.ApiResponse
import com.bagus.moviecatalogue.data.source.remote.RemoteDataSource
import com.bagus.moviecatalogue.data.source.remote.response.MoviesResponse
import com.bagus.moviecatalogue.data.source.remote.response.TvshowResponse
import com.bagus.moviecatalogue.utils.AppExecutors
import com.bagus.moviecatalogue.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.ArrayList

class FakeMoviesRepository (private val remoteDataSource: RemoteDataSource,
                            private val localDataSource: LocalDataSource,
                            private val appExecutors: AppExecutors
) : MoviesDataSource {
    override fun getAllMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>> {

        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MoviesResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(sort), config).build()
            }
            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()
            public override fun createCall(): LiveData<ApiResponse<List<MoviesResponse>>> =
                remoteDataSource.getAllMovies()
            public override fun saveCallResult(data: List<MoviesResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        overview = response.overview,
                        releaseDate = response.releaseDate,
                        voteAverage = response.voteAverage,
                        id = response.id,
                        title = response.title,
                        posterPath = response.posterPath,
                        favorite = false
                    )
                    movieList.add(movie)
                }

                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()

    }

    override fun getMovieDetail(id: Int): LiveData<Resource<MovieEntity>> {

        return object : NetworkBoundResource<MovieEntity, MoviesResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getMovieDetail(id)
            override fun shouldFetch(movieEntity: MovieEntity?): Boolean =
                movieEntity == null
            override fun createCall(): LiveData<ApiResponse<MoviesResponse>> =
                remoteDataSource.getMovieDetail(id)
            override fun saveCallResult(response: MoviesResponse) {
                val movie = MovieEntity(
                    overview = response.overview,
                    releaseDate = response.releaseDate,
                    voteAverage = response.voteAverage,
                    id = response.id,
                    title = response.title,
                    posterPath = response.posterPath,
                    favorite = false
                )
                localDataSource.updateMovie(movie, false)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun setFavoriteMovies(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movie, state) }

    override fun getAllTvshows(sort: String): LiveData<Resource<PagedList<TvshowEntity>>> {

        return object : NetworkBoundResource<PagedList<TvshowEntity>, List<TvshowResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<TvshowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvshows(sort), config).build()
            }
            override fun shouldFetch(data: PagedList<TvshowEntity>?): Boolean =
                data == null || data.isEmpty()
            public override fun createCall(): LiveData<ApiResponse<List<TvshowResponse>>> =
                remoteDataSource.getAllTvshows()
            public override fun saveCallResult(tvshowResponse: List<TvshowResponse>) {
                val tvshowList = ArrayList<TvshowEntity>()
                for (response in tvshowResponse) {
                    val tvshow = TvshowEntity(
                        overview = response.overview,
                        releaseDate = response.releaseDate,
                        voteAverage = response.voteAverage,
                        id = response.id,
                        title = response.title,
                        posterPath = response.posterPath,
                        favorite = false
                    )
                    tvshowList.add(tvshow)
                }

                localDataSource.insertTvshows(tvshowList)
            }
        }.asLiveData()
    }

    override fun getTvshowDetail(id: Int): LiveData<Resource<TvshowEntity>> {
        return object : NetworkBoundResource<TvshowEntity, TvshowResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvshowEntity> =
                localDataSource.getTvshowDetail(id)

            override fun shouldFetch(tvshowEntity: TvshowEntity?): Boolean =
                tvshowEntity == null

            override fun createCall(): LiveData<ApiResponse<TvshowResponse>> =
                remoteDataSource.getTvshowDetail(id)

            override fun saveCallResult(response: TvshowResponse) {
                val tvshow = TvshowEntity(
                    overview = response.overview,
                    releaseDate = response.releaseDate,
                    voteAverage = response.voteAverage,
                    id = response.id,
                    title = response.title,
                    posterPath = response.posterPath,
                    favorite = false
                )
                localDataSource.updateTvshow(tvshow)
            }
        }.asLiveData()
    }

    override fun getFavoriteTvshows(): LiveData<PagedList<TvshowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvshows(), config).build()
    }

    override fun setFavoriteTvshows(tvshow: TvshowEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvshows(tvshow, state) }
}