package com.example.lecture13_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lecture13_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.run {
            btnIncrease.setOnClickListener {
                Thread {
                    for (i in 1..100) {
                        Thread.sleep(100)
                        sb1.progress = sb1.progress + 1

                        runOnUiThread {
                            Toast.makeText(this@MainActivity, "증가", Toast.LENGTH_SHORT).show()
                        }
                    }
                }.start()
            }
        }
    }
}