package com.example.lecture14_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lecture14_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var intent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        intent = Intent(this, MusicService::class.java)

        binding.run {
            btnStart.setOnClickListener {
                startService(intent)
            }
            btnStop.setOnClickListener {

            }
        }
    }
}