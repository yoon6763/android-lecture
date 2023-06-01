package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.get
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val images = arrayOf(
            ImageData(R.drawable.pic1, "그림1"),
            ImageData(R.drawable.pic2, "그림2"),
            ImageData(R.drawable.pic3, "그림3"),
            ImageData(R.drawable.pic4, "그림4"),
            ImageData(R.drawable.pic5, "그림5"),
            ImageData(R.drawable.pic6, "그림6"),
            ImageData(R.drawable.pic7, "그림7"),
            ImageData(R.drawable.pic8, "그림8"),
            ImageData(R.drawable.pic9, "그림9"),
        )

        binding.run {
            repeat(9) { idx ->
                if (idx % 3 == 0) llImage.addView(LinearLayout(this@MainActivity).apply {
                    orientation = LinearLayout.HORIZONTAL
                    weightSum = 3f
                })

                (llImage[llImage.childCount - 1] as LinearLayout).addView(ImageView(this@MainActivity).apply {
                    setImageDrawable(getDrawable(images[idx].image))

                    scaleType = ImageView.ScaleType.CENTER_CROP
                    layoutParams =
                        LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT)
                            .apply { weight = 1f }

                    setOnClickListener {
                        images[idx].count++
                        Toast.makeText(
                            this@MainActivity,
                            "${images[idx].title} ${images[idx].count}번 클릭",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }

            btnFinish.setOnClickListener {
                startActivity(Intent(this@MainActivity, ResultActivity::class.java).apply {
                    putStringArrayListExtra("images", ArrayList(images.map { it.title }))
                    putIntegerArrayListExtra("counts", ArrayList(images.map { it.count }))
                })
            }
        }
    }
}