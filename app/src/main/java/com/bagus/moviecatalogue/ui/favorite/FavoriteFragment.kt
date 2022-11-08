package com.bagus.moviecatalogue.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bagus.moviecatalogue.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private lateinit var fragmentFavoriteBinding: FragmentFavoriteBinding

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentFavoriteBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val sectionsPagerAdapter = SectionsPagerAdapter(this, childFragmentManager)
            fragmentFavoriteBinding.viewPager.adapter = sectionsPagerAdapter
            fragmentFavoriteBinding.tabs.setupWithViewPager(fragmentFavoriteBinding.viewPager)
//            supportActionBar?.elevation = 0f
        }
    }
}