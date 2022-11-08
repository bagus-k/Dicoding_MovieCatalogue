package com.bagus.moviecatalogue.ui.movies

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagus.moviecatalogue.R
import com.bagus.moviecatalogue.data.source.local.entity.MovieEntity
import com.bagus.moviecatalogue.databinding.FragmentMoviesBinding
import com.bagus.moviecatalogue.utils.SortUtils
import com.bagus.moviecatalogue.viewmodel.ViewModelFactory
import com.bagus.moviecatalogue.vo.Resource
import com.bagus.moviecatalogue.vo.Status

class MoviesFragment : Fragment() {

    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var viewModel: MoviesViewModel

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]
            moviesAdapter = MoviesAdapter()
            fragmentMoviesBinding.progressBar.visibility = View.VISIBLE
            viewModel.getAllMovies(SortUtils.NEWEST).observe(viewLifecycleOwner, moviesData)

            with(fragmentMoviesBinding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = moviesAdapter
            }

        }
    }

    private val moviesData = Observer<Resource<PagedList<MovieEntity>>> { movies ->
        if (movies != null) {
            when (movies.status) {
                Status.LOADING -> fragmentMoviesBinding?.progressBar?.visibility = View.VISIBLE
                Status.SUCCESS -> {
                    fragmentMoviesBinding?.progressBar?.visibility = View.GONE
                    moviesAdapter.submitList(movies.data)
                    moviesAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    fragmentMoviesBinding?.progressBar?.visibility = View.GONE
                    Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @Override
    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.menu_date_filter, menu)
        return super.onCreateOptionsMenu(menu, menuInflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""
        when (item.getItemId()) {
            R.id.action_newest -> sort = SortUtils.NEWEST
            R.id.action_oldest -> sort = SortUtils.OLDEST
            R.id.action_random -> sort = SortUtils.RANDOM
        }
        viewModel.getAllMovies(sort).observe(this, moviesData)
        item.setChecked(true)
        return super.onOptionsItemSelected(item)
    }

}