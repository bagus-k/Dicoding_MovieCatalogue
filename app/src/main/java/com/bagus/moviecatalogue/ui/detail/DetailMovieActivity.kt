package com.bagus.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.bagus.moviecatalogue.R
import com.bagus.moviecatalogue.data.source.local.entity.MovieEntity
import com.bagus.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.bagus.moviecatalogue.viewmodel.ViewModelFactory
import com.bagus.moviecatalogue.vo.Resource
import com.bagus.moviecatalogue.vo.Status
import com.bumptech.glide.Glide

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var detailMovieBinding: ActivityDetailMovieBinding
    private lateinit var viewModel: DetailMovieViewModel
    private var menu: Menu? = null
    private lateinit var favoriteMovie: MovieEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(detailMovieBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val extras = intent.extras
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[DetailMovieViewModel::class.java]

        if (extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIE, 0)
            if (movieId != null) {

                detailMovieBinding.content.visibility = View.GONE
                detailMovieBinding.progressBar.visibility = View.VISIBLE

                 viewModel.getMovieDetail(movieId).observe(this,{ movie ->
                    if (movie != null) {
                        when (movie.status) {
                            Status.LOADING -> detailMovieBinding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> if (movie.data != null) {
                                favoriteMovie = movie.data
                                detailMovieBinding.progressBar.visibility = View.GONE
                                detailMovieBinding.content.visibility = View.VISIBLE

                                supportActionBar?.title = movie.data.title
                                Glide.with(this)
                                    .load("https://image.tmdb.org/t/p/original" + movie.data.posterPath)
                                    .into(detailMovieBinding.imagePoster)
                                detailMovieBinding.textTitle.text = movie.data.title
                                detailMovieBinding.textReleaseDate.text = movie.data.releaseDate
                                detailMovieBinding.textDescItem.text = movie.data.overview
                            }
                            Status.ERROR -> {
                                detailMovieBinding.progressBar.visibility = View.GONE
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val extras = intent.extras
        val movieId = extras?.getInt(EXTRA_MOVIE, 0)
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        if (movieId != null) {
            viewModel.getMovieDetail(movieId).observe(this, { movie ->
                when (movie.status) {
                    Status.LOADING -> detailMovieBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> if (movie.data != null) {
                        detailMovieBinding.progressBar.visibility = View.GONE
                        val state = movie.data.favorite
                        setFavoriteState(state)

                    }
                    Status.ERROR -> {
                        detailMovieBinding.progressBar.visibility = View.GONE
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_bookmark) {
            viewModel.setFavoriteMovie(favoriteMovie)
            if (!favoriteMovie.favorite) {
                Toast.makeText(this,favoriteMovie.title + " add to Favorite", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,favoriteMovie.title + " remove from Favorite", Toast.LENGTH_SHORT).show()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_bookmark)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_nonfavorite_white)
        }
    }
}