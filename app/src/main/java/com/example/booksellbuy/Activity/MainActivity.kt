package com.example.booksellbuy.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.booksellbuy.R
import com.example.booksellbuy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup BottomNavigationView with NavController
        val navController = findNavController(R.id.fragmentContainer)
        binding.bottomNavigation.setupWithNavController(navController)

        // Function call to handle toolbar visibility
        setupToolbarVisibility(navController)
    }

    private fun setupToolbarVisibility(navController: NavController) {
        val materialToolbar2: Toolbar = findViewById(R.id.materialToolbar2)
        setSupportActionBar(materialToolbar2)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.accountFragment, R.id.signup_Fragment -> {
                    materialToolbar2.visibility = View.GONE
                    binding.bottomNavigation.visibility =
                        View.GONE // Optionally hide bottom navigation
                }

                else -> {
                    materialToolbar2.visibility = View.VISIBLE
                    binding.bottomNavigation.visibility =
                        View.VISIBLE // Show bottom navigation for other fragments
                }
            }
        }
    }
}