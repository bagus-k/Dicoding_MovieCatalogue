package com.bagus.moviecatalogue.data.source.apiservice

import com.bagus.moviecatalogue.BuildConfig
import com.bagus.moviecatalogue.data.source.remote.response.MoviesApiResponse
import com.bagus.moviecatalogue.data.source.remote.response.MoviesResponse
import com.bagus.moviecatalogue.data.source.remote.response.TvshowApiResponse
import com.bagus.moviecatalogue.data.source.remote.response.TvshowResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("3/movie/popular")
    fun getAllMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Call <MoviesApiResponse>

    @GET("3/movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Call<MoviesResponse>

    @GET("3/tv/popular")
    fun getAlltvshows(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ) : Call<TvshowApiResponse>

    @GET("3/tv/{tv_id}")
    fun getTvshowDetail(
        @Path("tv_id") tvshowId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ) : Call<TvshowResponse>
}