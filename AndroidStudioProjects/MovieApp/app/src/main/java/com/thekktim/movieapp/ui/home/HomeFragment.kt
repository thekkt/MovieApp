package com.thekktim.movieapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.thekktim.movieapp.R
import com.thekktim.movieapp.adapters.MovieAdapter
import com.thekktim.movieapp.data.Movie
import com.thekktim.movieapp.databinding.FragmentHomeBinding
import com.thekktim.movieapp.ui.details.DetailsFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), MovieAdapter.OnMovieClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            viewModel.getPopularMovieList()
            viewModel.getRatedMovieList()
            viewModel.getUpcomingMovieList()
        }


            viewModel.getPopularList().observe(viewLifecycleOwner, {
                configurePopularList(it)
            })

            viewModel.getRatedList().observe(viewLifecycleOwner, {
                configureRatedList(it)
            })

            viewModel.getUpcomingList().observe(viewLifecycleOwner, {
                configureUpcomingList(it)
        })
        }

    private fun configurePopularList(movieList: MutableList<Movie>) {
        binding.movieListPopular.layoutManager = LinearLayoutManager(
            view?.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.movieListPopular.adapter =
            MovieAdapter(requireContext(),
                movieList,
                this)
    }

    private fun configureRatedList(movieList: MutableList<Movie>) {
        binding.movieListRated.layoutManager = LinearLayoutManager(
            view?.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.movieListRated.adapter =
            MovieAdapter(requireContext(),
                movieList,
                this)
    }
    private fun configureUpcomingList(movieList: MutableList<Movie>) {
        binding.movieListUpcoming.layoutManager = LinearLayoutManager(
            view?.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.movieListUpcoming.adapter =
            MovieAdapter(requireContext(),
                movieList,
                this)
    }

    override fun onItemClick(movie: Movie) {
        viewModel.apply {
            getSpecificString().observe(viewLifecycleOwner, {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })
        }
        parentFragmentManager.beginTransaction().apply {
            val bundle = Bundle()
            bundle.putSerializable("movie", movie)
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}