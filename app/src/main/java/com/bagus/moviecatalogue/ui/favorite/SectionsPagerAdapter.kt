package com.bagus.moviecatalogue.ui.favorite

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bagus.moviecatalogue.R
import com.bagus.moviecatalogue.ui.favorite.movies.FavoriteMovieFragment
import com.bagus.moviecatalogue.ui.favorite.tvshow.FavoriteTvshowFragment
import com.bagus.moviecatalogue.ui.tvshow.TvshowFragment

class SectionsPagerAdapter(private val mContext: FavoriteFragment, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tv_show)
    }

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTvshowFragment()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }

}
