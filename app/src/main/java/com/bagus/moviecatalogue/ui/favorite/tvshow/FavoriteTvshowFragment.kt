package com.bagus.moviecatalogue.ui.favorite.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bagus.moviecatalogue.R
import com.bagus.moviecatalogue.databinding.FragmentFavoriteMovieBinding
import com.bagus.moviecatalogue.databinding.FragmentFavoriteTvshowBinding
import com.bagus.moviecatalogue.ui.favorite.movies.FavoriteMovieAdapter
import com.bagus.moviecatalogue.ui.favorite.movies.FavoriteMovieViewModel
import com.bagus.moviecatalogue.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class FavoriteTvshowFragment : Fragment() {
    private  var  binding: FragmentFavoriteTvshowBinding? = null
    private val favoriteTvshowBinding get()=  binding
    private lateinit var viewModel: FavoriteTvshowViewModel
    private lateinit var adapter: FavoriteTvshowAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteTvshowBinding.inflate(layoutInflater, container, false)
        return favoriteTvshowBinding?.root
    }



    override fun onResume() {
        super.onResume()
        viewModel.getFavorite().observe(viewLifecycleOwner, { tvshow ->
            if (tvshow != null) {
                adapter.submitList(tvshow)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding?.rvFavoriteTvshow)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteTvshowViewModel::class.java]

            adapter = FavoriteTvshowAdapter()
            favoriteTvshowBinding?.progressBar?.visibility = View.VISIBLE
            viewModel.getFavorite().observe(viewLifecycleOwner, { tvshow ->
                if (tvshow != null) {
                    favoriteTvshowBinding?.progressBar?.visibility = View.GONE
                    favoriteTvshowBinding?.rvFavoriteTvshow?.visibility = View.VISIBLE
                    adapter.submitList(tvshow)
                    adapter.notifyDataSetChanged()
                }
            })

            with(favoriteTvshowBinding?.rvFavoriteTvshow) {
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
                val tvshowEntity = adapter.getSwipedData(swipedPosition)
                tvshowEntity?.let { viewModel.setFavorite(it) }
                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    tvshowEntity?.let { viewModel.setFavorite(it) }
                }
                snackbar.show()
            }
        }
    })
}