package com.akash.heroescv3

import android.os.Bundle
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.akash.heroescv3.adapter.HeroAdapter
import com.akash.heroescv3.databinding.ActivityMainBinding
import com.akash.heroescv3.viewModel.HeroViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController

        val popUpMenu =PopupMenu(this,null)
        popUpMenu.inflate(R.menu.bottom_navigation_menu)
        binding.bottomNavigationView.setupWithNavController(navController)
        binding.toolBar.setupWithNavController(navController)
    }

}