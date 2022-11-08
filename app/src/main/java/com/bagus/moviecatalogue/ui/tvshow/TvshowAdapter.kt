package com.bagus.moviecatalogue.ui.tvshow

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bagus.moviecatalogue.data.source.local.entity.MovieEntity
import com.bagus.moviecatalogue.data.source.local.entity.TvshowEntity
import com.bagus.moviecatalogue.databinding.ItemsTvshowBinding
import com.bagus.moviecatalogue.ui.detail.DetailMovieActivity
import com.bagus.moviecatalogue.ui.detail.DetailTvshowActivity
import com.bumptech.glide.Glide

class TvshowAdapter : PagedListAdapter<TvshowEntity, TvshowAdapter.TvshowViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvshowEntity>() {
            override fun areItemsTheSame(oldItem: TvshowEntity, newItem: TvshowEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: TvshowEntity, newItem: TvshowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvshowAdapter.TvshowViewHolder {
        val itemsTvshowBinding = ItemsTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvshowViewHolder(itemsTvshowBinding)
    }

    override fun onBindViewHolder(holder: TvshowViewHolder, position: Int) {
        val tvshow = getItem(position)
        if (tvshow != null) {
            holder.bind(tvshow)
        }
    }

    class TvshowViewHolder(private val binding: ItemsTvshowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tvshow: TvshowEntity) {
            with(binding){
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/original" + tvshow.posterPath)
                    .into(imgPoster)
                tvItemTitle.text = tvshow.title
                tvReleaseDate.text = tvshow.releaseDate
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvshowActivity::class.java)
                    intent.putExtra(DetailTvshowActivity.EXTRA_TVSHOW, tvshow.id)

                    itemView.context.startActivity(intent)
                }
            }
        }
    }

}