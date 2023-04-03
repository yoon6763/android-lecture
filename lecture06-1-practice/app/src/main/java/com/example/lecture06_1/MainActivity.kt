package com.example.lecture06_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.lecture06_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.run {

            llPicker.visibility = View.GONE
            rgReverse.visibility = View.GONE
            llText.visibility = View.GONE

            chTimer.setOnClickListener {
                chTimer.start()
                llPicker.visibility = View.VISIBLE
                rgReverse.visibility = View.VISIBLE
                llText.visibility = View.VISIBLE

            }

            tvYear.setOnLongClickListener {
                chTimer.stop()
                true
            }
        }
    }
}