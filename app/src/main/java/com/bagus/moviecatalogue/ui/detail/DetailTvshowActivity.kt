package com.bagus.moviecatalogue.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bagus.moviecatalogue.R
import com.bagus.moviecatalogue.data.source.local.entity.MovieEntity
import com.bagus.moviecatalogue.data.source.local.entity.TvshowEntity
import com.bagus.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.bagus.moviecatalogue.databinding.ActivityDetailTvshowBinding
import com.bagus.moviecatalogue.viewmodel.ViewModelFactory
import com.bagus.moviecatalogue.vo.Status
import com.bumptech.glide.Glide

class DetailTvshowActivity : AppCompatActivity() {
    private lateinit var detailTvshowBinding: ActivityDetailTvshowBinding
    private lateinit var viewModel: DetailTvshowViewModel
    private var menu: Menu? = null
    private lateinit var favoriteTvshow: TvshowEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailTvshowBinding = ActivityDetailTvshowBinding.inflate(layoutInflater)
        setContentView(detailTvshowBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val extras = intent.extras
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[DetailTvshowViewModel::class.java]

        if (extras != null) {
            val tvshowId = extras.getInt(EXTRA_TVSHOW, 0)
            if (tvshowId != null) {

                detailTvshowBinding.content.visibility = View.GONE
                detailTvshowBinding.progressBar.visibility = View.VISIBLE

                viewModel.getTvshowDetail(tvshowId).observe(this,{ tvshow ->
                    if (tvshow != null) {
                        when (tvshow.status) {
                            Status.LOADING -> detailTvshowBinding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> if (tvshow.data != null) {
                                favoriteTvshow = tvshow.data
                                detailTvshowBinding.progressBar.visibility = View.GONE
                                detailTvshowBinding.content.visibility = View.VISIBLE

                                supportActionBar?.title = tvshow.data.title
                                Glide.with(this)
                                    .load("https://image.tmdb.org/t/p/original" + tvshow.data.posterPath)
                                    .into(detailTvshowBinding.imagePoster)
                                detailTvshowBinding.textTitle.text = tvshow.data.title
                                detailTvshowBinding.textReleaseDate.text = tvshow.data.releaseDate
                                detailTvshowBinding.textDescItem.text = tvshow.data.overview
                            }
                            Status.ERROR -> {
                                detailTvshowBinding.progressBar.visibility = View.GONE
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        }
    }

    companion object {
        const val EXTRA_TVSHOW = "extra_tvshow"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val extras = intent.extras
        val tvshowId = extras?.getInt(EXTRA_TVSHOW, 0)
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        if (tvshowId != null) {
            viewModel.getTvshowDetail(tvshowId).observe(this, { movie ->
                when (movie.status) {
                    Status.LOADING -> detailTvshowBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> if (movie.data != null) {
                        detailTvshowBinding.progressBar.visibility = View.GONE
                        val state = movie.data.favorite
                        setFavoriteState(state)
                    }
                    Status.ERROR -> {
                        detailTvshowBinding.progressBar.visibility = View.GONE
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_bookmark) {
            viewModel.setFavoriteTvshow(favoriteTvshow)
            if (!favoriteTvshow.favorite) {
                Toast.makeText(this,favoriteTvshow.title + " add to Favorite", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,favoriteTvshow.title + " remove from Favorite", Toast.LENGTH_SHORT).show()
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