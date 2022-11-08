package com.bagus.moviecatalogue.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bagus.moviecatalogue.data.source.MoviesRepository
import com.bagus.moviecatalogue.di.Injection
import com.bagus.moviecatalogue.ui.detail.DetailMovieViewModel
import com.bagus.moviecatalogue.ui.detail.DetailTvshowViewModel
import com.bagus.moviecatalogue.ui.favorite.movies.FavoriteMovieViewModel
import com.bagus.moviecatalogue.ui.favorite.tvshow.FavoriteTvshowViewModel
import com.bagus.moviecatalogue.ui.movies.MoviesViewModel
import com.bagus.moviecatalogue.ui.tvshow.TvshowViewModel

class ViewModelFactory private constructor(private val mMoviesRepository: MoviesRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(mMoviesRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(mMoviesRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
               FavoriteMovieViewModel(mMoviesRepository) as T
            }
            modelClass.isAssignableFrom(TvshowViewModel::class.java) -> {
                TvshowViewModel(mMoviesRepository) as T
            }
            modelClass.isAssignableFrom(DetailTvshowViewModel::class.java) -> {
                DetailTvshowViewModel(mMoviesRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteTvshowViewModel::class.java) -> {
                FavoriteTvshowViewModel(mMoviesRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}