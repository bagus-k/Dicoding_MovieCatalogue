package com.bagus.moviecatalogue.ui.detail

import android.graphics.Movie
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.bagus.moviecatalogue.data.source.MoviesRepository
import com.bagus.moviecatalogue.data.source.local.entity.MovieEntity
import com.bagus.moviecatalogue.utils.DataDummy
import com.bagus.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DataDummy.getDetailMovie()
    private val movieId = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(moviesRepository)
    }

    @Test
    fun getMovie() {
        val dummyDetail = Resource.success(DataDummy.getDetailMovie())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetail

        `when`(moviesRepository.getMovieDetail(movieId)).thenReturn(movie)

        val actualValue = viewModel.getMovieDetail(movieId).value?.data
        val expectedValue = movie.value?.data

        assertNotNull(actualValue)
        assertEquals(expectedValue?.id, actualValue?.id)
        assertEquals(expectedValue?.title, actualValue?.title)
        assertEquals(expectedValue?.posterPath, actualValue?.posterPath)
        assertEquals(expectedValue?.overview, actualValue?.overview)
        assertEquals(expectedValue?.releaseDate, actualValue?.releaseDate)
        assertEquals(expectedValue?.voteAverage, actualValue?.voteAverage)

        viewModel.getMovieDetail(movieId).observeForever(observer)
        verify(observer).onChanged(dummyDetail)
    }
}