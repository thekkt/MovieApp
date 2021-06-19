package com.thekktim.movieapp.data

import retrofit2.Call

interface Repo {
   suspend fun getMoviesPopular(api_key:String,
                                language:String,
                                sort_by:String
   ): Call<Response>

   suspend fun getMoviesRated(api_key:String,
                              language:String,
                              sort_by:String
   ) : Call<Response>

    suspend fun getMoviesUpcoming(api_key:String,
                               language:String,
                               sort_by:String
    ) : Call<Response>
}