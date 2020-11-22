package com.said.homework.news.presentation.view

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.ViewBinding
import com.said.homework.R
import com.said.homework.base.presentation.view.BaseActivity
import com.said.homework.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var mainActivityViewBinding: ActivityMainBinding

    override fun getViewBinding(): ViewBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun releaseComponent() {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewBinding = baseActivityViewBinding as ActivityMainBinding
        initializeViews()
    }

    private fun initializeViews() {
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_favorite))
        setupActionBarWithNavController(navController, appBarConfiguration)
        mainActivityViewBinding.navView.setupWithNavController(navController)
    }

}