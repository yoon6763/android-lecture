package com.example.lecture07_1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.lecture07_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        title = "배경색 바꾸기"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu1, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemRed -> {
                binding.baseLayout.setBackgroundColor(Color.RED)
                true
            }
            R.id.itemGreen -> {
                binding.baseLayout.setBackgroundColor(Color.GREEN)
                true
            }
            R.id.itemBlue -> {
                binding.baseLayout.setBackgroundColor(Color.BLUE)
                true
            }
            else -> false
        }
    }
}