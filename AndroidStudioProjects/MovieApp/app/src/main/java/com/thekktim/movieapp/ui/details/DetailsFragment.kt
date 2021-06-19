package com.thekktim.movieapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.thekktim.movieapp.data.Movie
import com.thekktim.movieapp.databinding.FragmentDetailsBinding
import com.thekktim.movieapp.network.Utils

class DetailsFragment : Fragment() {

    private var _binding : FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        val movie = bundle?.getSerializable("movie") as Movie
        binding.apply {
            movieTitle.text = movie.title
            movieOverview.text = movie.overview
            movieRelease.text = movie.release_date
            movieRatingItem.text = movie.vote_average.toString()
            context?.let { Glide.with(it).load(Utils.IMAGE_URL+movie.backdrop_path).into(moviePoster) }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}