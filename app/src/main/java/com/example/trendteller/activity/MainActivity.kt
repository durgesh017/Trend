package com.example.trendteller.activity

import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.trendteller.R
import com.example.trendteller.databinding.ActivityMainBinding
import com.example.trendteller.fragment.InfoFragment
import com.example.trendteller.fragment.NewsFragment
import com.example.trendteller.networking.NetworkChecker

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val networkChecker by lazy {
        NetworkChecker(getSystemService(ConnectivityManager::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(NewsFragment())

        val newsFragment = NewsFragment()
        val infoFragment = InfoFragment()


        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.News -> loadFragment(newsFragment)
                R.id.Info -> loadFragment(infoFragment)
            }
            true
        }


    }

    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.viewPager, fragment)
            commit()
        }
    }
}