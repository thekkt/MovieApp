package com.thekktim.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import com.thekktim.movieapp.ui.ratings.RatingsFragment
import com.thekktim.movieapp.R
import com.thekktim.movieapp.databinding.ActivityMainBinding
import com.thekktim.movieapp.ui.home.HomeFragment
import com.thekktim.movieapp.ui.favorite.FavoriteFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment(HomeFragment())

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.btnHome -> setFragment(HomeFragment())
                R.id.btnFavorite -> setFragment(FavoriteFragment())
                R.id.btnRatings -> setFragment(RatingsFragment())
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainer.id, fragment)
            commit()
        }
    }
}