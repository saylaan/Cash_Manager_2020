package com.cash_manager_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityConnected : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_connected)
        val navView: BottomNavigationView = findViewById(R.id.nav_view_connected)

        val navController = findNavController(R.id.nav_host_fragment_connected)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_payement, R.id.navigation_settings_connected))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }
}