package com.bagus.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.bagus.moviecatalogue.data.source.local.LocalDataSource
import com.bagus.moviecatalogue.data.source.local.entity.MovieEntity
import com.bagus.moviecatalogue.data.source.local.entity.TvshowEntity
import com.bagus.moviecatalogue.data.source.remote.RemoteDataSource
import com.bagus.moviecatalogue.utils.AppExecutors
import com.bagus.moviecatalogue.utils.DataDummy
import com.bagus.moviecatalogue.utils.LiveDataTestUtil
import com.bagus.moviecatalogue.utils.PagedListUtil
import com.bagus.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MoviesRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val moviesRepository = FakeMoviesRepository(remote, local, appExecutors)

    private val moviesResponse = DataDummy.generateRemoteDummyMovies()
    private val movieResponse = DataDummy.generateRemoteDummyMovies()[0]
    private val movieId = moviesResponse[0].id
    private val movieDetail = DataDummy.getDetailMovie()

    private val tvshowsResponse = DataDummy.generateRemoteDummyTvshows()
    private val tvshowResponse = DataDummy.generateRemoteDummyTvshows()[0]
    private val tvshowId = tvshowsResponse[0].id
    private val tvshowDetail = DataDummy.getDetailTvshow()

    @Test
    fun getAllMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies("NEWEST")).thenReturn(dataSourceFactory)
        moviesRepository.getAllMovies("NEWEST")

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovie()))
        verify(local).getAllMovies("NEWEST")
        assertNotNull(movieEntities.data)
        assertEquals(moviesResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val dummyDetail = MutableLiveData<MovieEntity>()
        dummyDetail.value = DataDummy.getDetailMovie()
        `when`(local.getMovieDetail(movieId)).thenReturn(dummyDetail)

        val movieDetailEntity = LiveDataTestUtil.getValue(moviesRepository.getMovieDetail(movieId))
        verify(local).getMovieDetail(movieId)
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetail.id, movieDetailEntity.data?.id)
    }

    @Test
    fun getFavoriteMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        moviesRepository.getFavoriteMovies()
        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovie()))
        verify(local).getFavoriteMovies()
        assertNotNull(movieEntities)
        assertEquals(moviesResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvshows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,TvshowEntity>
        `when`(local.getAllTvshows("NEWEST")).thenReturn(dataSourceFactory)
        moviesRepository.getAllTvshows("NEWEST")

        val tvshowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvshow()))
        verify(local).getAllTvshows("NEWEST")
        assertNotNull(tvshowEntities.data)
        assertEquals(tvshowsResponse.size.toLong(), tvshowEntities.data?.size?.toLong())
    }

    @Test
    fun getTvshowDetail() {
        val dummyDetail = MutableLiveData<TvshowEntity>()
        dummyDetail.value = DataDummy.getDetailTvshow()
        `when`(local.getTvshowDetail(tvshowId)).thenReturn(dummyDetail)

        val tvshowDetailEntity = LiveDataTestUtil.getValue(moviesRepository.getTvshowDetail(tvshowId))
        verify(local).getTvshowDetail(tvshowId)
        assertNotNull(tvshowDetailEntity)
        assertEquals(tvshowDetail.id, tvshowDetailEntity.data?.id)
    }

    @Test
    fun getFavoriteTvshow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvshowEntity>
        `when`(local.getFavoriteTvshows()).thenReturn(dataSourceFactory)
        moviesRepository.getFavoriteTvshows()
        val tvshowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovie()))
        verify(local).getFavoriteTvshows()
        assertNotNull(tvshowEntities)
        assertEquals(tvshowsResponse.size.toLong(), tvshowEntities.data?.size?.toLong())
    }
}