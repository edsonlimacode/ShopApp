package com.edsonlimadev.shopapp.presenter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.GravityCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.edsonlimadev.shopapp.R
import com.edsonlimadev.shopapp.databinding.ActivityMainBinding
import com.edsonlimadev.shopapp.presenter.home.HomeFragment
import com.edsonlimadev.shopapp.presenter.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var actionToggle: ActionBarDrawerToggle

    private val homeViewModel: HomeViewModel by viewModels()

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

       // setSupportActionBar(binding.tbMain)
       // supportActionBar?.setDisplayShowTitleEnabled(false)

       // val drawerToggle = binding.drawerLayout
        val navView = binding.navigationView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainNavHost) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupWithNavController(navView, navController)
        //NavigationUI.setupActionBarWithNavController(this, navController, drawerToggle)

       /* actionToggle =
            ActionBarDrawerToggle(this, drawerToggle, binding.tbMain, R.string.open, R.string.close)
        drawerToggle.addDrawerListener(actionToggle)
        actionToggle.syncState()*/

        homeViewModel.homeButtonClickEvent.observe(this){
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

    }

}