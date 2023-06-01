package com.example.lecture11_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.example.lecture11_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.run {
            val posterIds = arrayOf(
                R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04,
                R.drawable.mov05, R.drawable.mov06, R.drawable.mov07, R.drawable.mov08,
                R.drawable.mov09, R.drawable.mov10, R.drawable.mov11, R.drawable.mov12,
                R.drawable.mov13, R.drawable.mov14, R.drawable.mov15, R.drawable.mov16,
                R.drawable.mov17, R.drawable.mov18, R.drawable.mov19, R.drawable.mov20,
                R.drawable.mov21, R.drawable.mov22, R.drawable.mov23, R.drawable.mov24,
                R.drawable.mov25
            )

            gridView.adapter = MyGridAdapter(this@MainActivity, posterIds)

            gridView.setOnItemClickListener { parent, view, position, id ->
                val contentView = View.inflate(this@MainActivity, R.layout.dialog, null)

                val dlgImageView = contentView.findViewById<ImageView>(R.id.imageView)
                dlgImageView.setImageResource(posterIds[position])

                AlertDialog.Builder(this@MainActivity)
                    .setView(contentView)
                    .setTitle("포스터")
                    .setNegativeButton("닫기", null)
                    .show()
            }
        }
    }
}