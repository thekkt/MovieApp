package com.thekktim.movieapp.data

import java.io.Serializable

data class Movie(
         val backdrop_path: String,
         val id: Long,
         val original_language: String,
         val original_title: String,
         val overview: String,
         val popularity: Double,
         val poster_path: String,
         val release_date: String,
         val title: String,
         val vote_average: Double
    ) : Serializable