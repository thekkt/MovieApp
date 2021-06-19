package com.thekktim.movieapp.network

import com.thekktim.movieapp.data.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("movie")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
    ): Call<Response>

    companion object {
        private var retrofitService: NetworkService? = null
        fun getInstance(): NetworkService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Utils.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                retrofitService = retrofit.create(NetworkService::class.java)
            }
            return retrofitService!!
        }
    }
}