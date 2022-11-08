package com.bagus.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bagus.moviecatalogue.BuildConfig
import com.bagus.moviecatalogue.data.source.remote.response.MoviesResponse
import com.bagus.moviecatalogue.data.source.apiservice.ApiConfig
import com.bagus.moviecatalogue.data.source.remote.response.MoviesApiResponse
import com.bagus.moviecatalogue.data.source.remote.response.TvshowApiResponse
import com.bagus.moviecatalogue.data.source.remote.response.TvshowResponse
import com.bagus.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getAllMovies(): LiveData<ApiResponse<List<MoviesResponse>>> {
        EspressoIdlingResource.increment()
        val movieResult = MutableLiveData<ApiResponse<List<MoviesResponse>>>()
        val client = ApiConfig.getApiService().getAllMovies(BuildConfig.TMDB_API_KEY)
        client.enqueue(object : Callback<MoviesApiResponse> {
            override fun onResponse(call: Call<MoviesApiResponse>, response: Response<MoviesApiResponse>) {
                movieResult.value = ApiResponse.success(response.body()?.results as List<MoviesResponse>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MoviesApiResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovies onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return movieResult
    }

    fun getMovieDetail(id: Int): LiveData<ApiResponse<MoviesResponse>> {
        EspressoIdlingResource.increment()
        val movieDetailResult = MutableLiveData<ApiResponse<MoviesResponse>>()
        val client = ApiConfig.getApiService().getMovieDetail(id, BuildConfig.TMDB_API_KEY)
        client.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                movieDetailResult.value = ApiResponse.success(response.body() as MoviesResponse)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovieDetail onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return movieDetailResult
    }

    fun getAllTvshows(): LiveData<ApiResponse<List<TvshowResponse>>> {
        EspressoIdlingResource.increment()
        val tvshowResult = MutableLiveData<ApiResponse<List<TvshowResponse>>>()
        val client = ApiConfig.getApiService().getAlltvshows(BuildConfig.TMDB_API_KEY)
        client.enqueue(object : Callback<TvshowApiResponse> {
            override fun onResponse(call: Call<TvshowApiResponse>, response: Response<TvshowApiResponse>) {
                tvshowResult.value = ApiResponse.success(response.body()?.results as List<TvshowResponse>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvshowApiResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getTvshow onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return tvshowResult
    }

    fun getTvshowDetail(id: Int): LiveData<ApiResponse<TvshowResponse>> {
        EspressoIdlingResource.increment()
        val tvshowDetailResult = MutableLiveData<ApiResponse<TvshowResponse>>()
        val client = ApiConfig.getApiService().getTvshowDetail(id, BuildConfig.TMDB_API_KEY)
        client.enqueue(object : Callback<TvshowResponse> {
            override fun onResponse(call: Call<TvshowResponse>, response: Response<TvshowResponse>) {
                tvshowDetailResult.value = ApiResponse.success(response.body() as TvshowResponse)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvshowResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getTvshowDetail onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return tvshowDetailResult
    }

}