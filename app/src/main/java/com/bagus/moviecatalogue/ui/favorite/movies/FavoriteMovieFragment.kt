package com.bagus.moviecatalogue.ui.favorite.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bagus.moviecatalogue.R
import com.bagus.moviecatalogue.databinding.FragmentFavoriteMovieBinding
import com.bagus.moviecatalogue.databinding.FragmentMoviesBinding
import com.bagus.moviecatalogue.ui.movies.MoviesAdapter
import com.bagus.moviecatalogue.ui.movies.MoviesFragment
import com.bagus.moviecatalogue.ui.movies.MoviesViewModel
import com.bagus.moviecatalogue.viewmodel.ViewModelFactory
import com.bagus.moviecatalogue.vo.Status
import com.google.android.material.snackbar.Snackbar
import kotlin.math.log


class FavoriteMovieFragment : Fragment() {
    private  var  binding: FragmentFavoriteMovieBinding? = null
    private val favoriteMovieBinding get()=  binding
    private lateinit var viewModel: FavoriteMovieViewModel
    private lateinit var adapter: FavoriteMovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return favoriteMovieBinding?.root
    }



    override fun onResume() {
        super.onResume()
        viewModel.getFavorite().observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                adapter.submitList(movies)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding?.rvFavoriteMovies)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]

            adapter = FavoriteMovieAdapter()
            favoriteMovieBinding?.progressBar?.visibility = View.VISIBLE
            viewModel.getFavorite().observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    favoriteMovieBinding?.progressBar?.visibility = View.GONE
                    favoriteMovieBinding?.rvFavoriteMovies?.visibility = View.VISIBLE
                    adapter.submitList(movies)
                    adapter.notifyDataSetChanged()
                }
            })

            with(favoriteMovieBinding?.rvFavoriteMovies) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = adapter
            }

        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movieEntity = adapter.getSwipedData(swipedPosition)
                movieEntity?.let { viewModel.setFavorite(it) }
                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    movieEntity?.let { viewModel.setFavorite(it) }
                }
                snackbar.show()
            }
        }
    })
}