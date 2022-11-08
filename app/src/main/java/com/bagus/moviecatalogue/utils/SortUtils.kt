package com.bagus.moviecatalogue.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val NEWEST = "Newest"
    const val OLDEST = "Oldest"
    const val RANDOM = "Random"
    const val MOVIES = "movieentities"
    const val TVSHOW = "tvshowentities"

    fun getSortedQuery(filter: String, table: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM $table ")
        if (filter == NEWEST) {
            simpleQuery.append("ORDER BY releaseDate DESC")
        } else if (filter == OLDEST) {
            simpleQuery.append("ORDER BY releaseDate ASC")
        } else if (filter == RANDOM) {
            simpleQuery.append("ORDER BY RANDOM()")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}