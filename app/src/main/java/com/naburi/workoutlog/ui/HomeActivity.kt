package com.naburi.workoutlog.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.naburi.workoutlog.R
import com.naburi.workoutlog.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNav()

    }
    fun setupBottomNav(){
        binding.bnvHome.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.plan ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, PlanFragment()).commit()
                    true

                }
                R.id.track ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, TrackFragment()).commit()
                    true
                }
                R.id.profile ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fcvHome,
                        ProfileFragment()
                    ).commit()
                    true
                }
                else->false
            }
        }
    }
}