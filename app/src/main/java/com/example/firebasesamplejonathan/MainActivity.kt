package com.example.firebasesamplejonathan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.firebasesamplejonathan.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var analytics: FirebaseAnalytics
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        analytics = Firebase.analytics

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_login,
                R.id.navigation_github
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        LogEvents.logEvent(analytics, "MainActivity", "Created")
    }



    override fun onStart() {
        super.onStart()
        LogEvents.logEvent(analytics, "MainActivity", "Started")
    }

    override fun onResume() {
        super.onResume()
        LogEvents.logEvent(analytics,"MainActivity", "Resumed")
    }

    object LogEvents {
        fun logEvent(analytics: FirebaseAnalytics, id: String, name: String) {
            analytics.logEvent(
                FirebaseAnalytics.Event.SELECT_CONTENT, bundleOf(
                    Pair(FirebaseAnalytics.Param.ITEM_ID, id),
                    Pair(FirebaseAnalytics.Param.ITEM_NAME, name),
                    Pair(FirebaseAnalytics.Param.CONTENT_TYPE, "text")
                )
            )
        }
    }
}