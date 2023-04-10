package com.example.lecture07_1_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.lecture07_1_practice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_cat_1 -> binding.ivImage.setImageResource(R.drawable.img_cat1)
            R.id.menu_cat_2 -> binding.ivImage.setImageResource(R.drawable.img_cat2)
            R.id.menu_cat_3 -> binding.ivImage.setImageResource(R.drawable.img_cat3)
            R.id.menu_rotate ->
                binding.ivImage.rotation += binding.etRotate.text.toString().toFloat()
        }

        return false
    }
}