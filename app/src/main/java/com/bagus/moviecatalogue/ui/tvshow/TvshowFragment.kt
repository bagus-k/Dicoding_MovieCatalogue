package com.bagus.moviecatalogue.ui.tvshow

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
import com.bagus.moviecatalogue.data.source.local.entity.TvshowEntity
import com.bagus.moviecatalogue.databinding.FragmentMoviesBinding
import com.bagus.moviecatalogue.databinding.FragmentTvshowBinding
import com.bagus.moviecatalogue.ui.movies.MoviesAdapter
import com.bagus.moviecatalogue.ui.movies.MoviesViewModel
import com.bagus.moviecatalogue.utils.SortUtils
import com.bagus.moviecatalogue.viewmodel.ViewModelFactory
import com.bagus.moviecatalogue.vo.Resource
import com.bagus.moviecatalogue.vo.Status

class TvshowFragment : Fragment() {
    private lateinit var fragmentTvshowBinding: FragmentTvshowBinding
    private lateinit var tvshowAdapter: TvshowAdapter
    private lateinit var viewModel: TvshowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentTvshowBinding = FragmentTvshowBinding.inflate(layoutInflater, container, false)
        return fragmentTvshowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvshowViewModel::class.java]

            tvshowAdapter = TvshowAdapter()
            fragmentTvshowBinding.progressBar.visibility = View.VISIBLE
            viewModel.getAllTvshow(SortUtils.NEWEST).observe(viewLifecycleOwner, tvshowData)

            with(fragmentTvshowBinding.rvTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = tvshowAdapter
            }
        }
    }

    private val tvshowData = Observer<Resource<PagedList<TvshowEntity>>> { tvshow ->
        if (tvshow != null) {
            when (tvshow.status) {
                Status.LOADING -> fragmentTvshowBinding?.progressBar?.visibility = View.VISIBLE
                Status.SUCCESS -> {
                    fragmentTvshowBinding?.progressBar?.visibility = View.GONE
                    tvshowAdapter.submitList(tvshow.data)
                    tvshowAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    fragmentTvshowBinding?.progressBar?.visibility = View.GONE
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
        viewModel.getAllTvshow(sort).observe(this, tvshowData)
        item.setChecked(true)
        return super.onOptionsItemSelected(item)
    }
}