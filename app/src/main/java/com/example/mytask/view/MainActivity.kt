package com.example.mytask.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mytask.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(ProductListFragment())
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.list -> {
                    loadFragment(ProductListFragment())
                    true
                }
                R.id.favorite -> {
                    loadFragment(FavoriteListFragment())
                    true
                }

                else -> {
                    true
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }


}