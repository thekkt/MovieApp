package com.thekktim.movieapp.data

import com.thekktim.movieapp.network.NetworkService
import retrofit2.Call

class MovieRepository(private val retrofitService: NetworkService) : Repo {

    override suspend fun getMoviesPopular(
        api_key: String,
        language: String,
        sort_by: String
    ): Call<Response> {
        return retrofitService.getMovies(api_key,language,sort_by)
    }
    override suspend fun getMoviesRated(
        api_key: String,
        language: String,
        sort_by: String
    ): Call<Response> {
        return retrofitService.getMovies(api_key,language,sort_by)
    }
    override suspend fun getMoviesUpcoming(
        api_key: String,
        language: String,
        sort_by: String
    ): Call<Response> {
        return retrofitService.getMovies(api_key,language,sort_by)
    }
}