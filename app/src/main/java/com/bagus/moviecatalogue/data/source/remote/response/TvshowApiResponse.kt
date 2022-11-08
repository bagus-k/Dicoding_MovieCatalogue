package com.bagus.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvshowApiResponse(
    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("results")
    val results: List<TvshowResponse>
)
