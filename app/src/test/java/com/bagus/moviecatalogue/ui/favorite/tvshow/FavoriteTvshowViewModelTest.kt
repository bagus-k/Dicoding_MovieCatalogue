package com.bagus.moviecatalogue.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.bagus.moviecatalogue.data.source.MoviesRepository
import com.bagus.moviecatalogue.data.source.local.entity.MovieEntity
import com.bagus.moviecatalogue.data.source.local.entity.TvshowEntity
import com.bagus.moviecatalogue.ui.favorite.movies.FavoriteMovieViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvshowViewModelTest {

    private lateinit var viewModel: FavoriteTvshowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<PagedList<TvshowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvshowEntity>

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        viewModel = FavoriteTvshowViewModel(moviesRepository)
    }

    @Test
    fun getFavoriteMovie() {
        val dummyTvshow = pagedList
        Mockito.`when`(dummyTvshow.size).thenReturn(10)
        val tvshow = MutableLiveData<PagedList<TvshowEntity>>()
        tvshow.value = dummyTvshow

        Mockito.`when`(moviesRepository.getFavoriteTvshows()).thenReturn(tvshow)
        val tvshowEntities = viewModel.getFavorite().value
        Mockito.verify<MoviesRepository>(moviesRepository).getFavoriteTvshows()
        assertNotNull(tvshowEntities)
        assertEquals(10, tvshowEntities?.size)

        viewModel.getFavorite().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvshow)
    }
}