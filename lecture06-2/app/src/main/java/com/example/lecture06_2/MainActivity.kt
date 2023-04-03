package com.example.lecture06_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lecture06_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.run {

            vf.flipInterval = 1000

            btnStart.setOnClickListener {
                vf.startFlipping()
            }
            btnStop.setOnClickListener {
                vf.stopFlipping()
            }
        }
    }
}