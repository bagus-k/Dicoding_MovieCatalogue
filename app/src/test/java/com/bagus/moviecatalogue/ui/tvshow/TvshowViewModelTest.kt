package com.bagus.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.bagus.moviecatalogue.data.source.MoviesRepository
import com.bagus.moviecatalogue.data.source.local.entity.MovieEntity
import com.bagus.moviecatalogue.data.source.local.entity.TvshowEntity
import com.bagus.moviecatalogue.ui.movies.MoviesViewModel
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
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvshowViewModelTest {

    private lateinit var viewModel: TvshowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvshowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvshowEntity>

    @Before
    fun setUp() {
        viewModel = TvshowViewModel(moviesRepository)
    }

    @Test
    fun getTvshow() {
        val dummyTvshow = Resource.success(pagedList)
        Mockito.`when`(dummyTvshow.data?.size).thenReturn(10)
        val tvshow = MutableLiveData<Resource<PagedList<TvshowEntity>>>()
        tvshow.value = dummyTvshow

        Mockito.`when`(moviesRepository.getAllTvshows("NEWEST")).thenReturn(tvshow)
        val tvshowEntities = viewModel.getAllTvshow("NEWEST").value?.data
        verify(moviesRepository).getAllTvshows("NEWEST")
        assertNotNull(tvshowEntities)
        assertEquals(10, tvshowEntities?.size)

        viewModel.getAllTvshow("NEWEST").observeForever(observer)
        verify(observer).onChanged(dummyTvshow)
    }

}