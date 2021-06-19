package com.thekktim.movieapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thekktim.movieapp.data.Movie
import com.thekktim.movieapp.data.Response
import com.thekktim.movieapp.data.MovieRepository
import com.thekktim.movieapp.network.NetworkService
import com.thekktim.movieapp.network.Utils
import retrofit2.Call

class HomeViewModel : ViewModel() {

    private var liveDataPopularList = MutableLiveData<MutableList<Movie>>()
    private var liveDataRatedList = MutableLiveData<MutableList<Movie>>()
    private var liveDataUpcomingList = MutableLiveData<MutableList<Movie>>()
    private var liveData = MutableLiveData<String>()

    private var repository: MovieRepository = MovieRepository(NetworkService.getInstance())

    fun getPopularList(): MutableLiveData<MutableList<Movie>>{
        return liveDataPopularList
    }

    suspend fun getPopularMovieList(){
        repository.getMoviesPopular(Utils.API_KEY,"ru-RU","popularity.desc").enqueue(object : retrofit2.Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful){
                    val movies = response.body()
                    liveDataPopularList.value = movies?.results
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.i("MyTag", t.message.toString())
            }
        })
    }

    fun getRatedList(): MutableLiveData<MutableList<Movie>>{
        return liveDataRatedList
    }

    suspend fun getRatedMovieList(){
        repository.getMoviesRated(Utils.API_KEY,"ru-RU","vote_count.desc").enqueue(object : retrofit2.Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful){
                    val movies = response.body()
                    liveDataRatedList.value = movies?.results
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.i("MyTag", t.message.toString())
            }
        })
    }
    fun getUpcomingList(): MutableLiveData<MutableList<Movie>>{
        return liveDataUpcomingList
    }

    suspend fun getUpcomingMovieList(){
        repository.getMoviesUpcoming(Utils.API_KEY,"ru-RU","revenue.desc").enqueue(object : retrofit2.Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful){
                    val movies = response.body()
                    liveDataUpcomingList.value = movies?.results
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.i("MyTag", t.message.toString())
            }
        })
    }


    fun getSpecificString() : MutableLiveData<String>{
        return liveData
    }
}
