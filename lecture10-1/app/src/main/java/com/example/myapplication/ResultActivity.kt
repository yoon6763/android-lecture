package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import com.example.myapplication.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private val binding by lazy { ActivityResultBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val images = intent.getStringArrayListExtra("images")!!
        val counts = intent.getIntegerArrayListExtra("counts")!!
        val imageIds = arrayOf(
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6,
            R.drawable.pic7,
            R.drawable.pic8,
            R.drawable.pic9,
        )

        binding.run {
            val maxIdx = counts.indexOf(counts.maxOrNull())

            tvTitle.text = images[maxIdx]
            ivImage.setImageDrawable(getDrawable(maxIdx))

            repeat(9) { idx ->
                llImageTitles.addView(TextView(this@ResultActivity).apply {
                    text = images[idx]
                    textSize = 20f
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                    ).apply { weight = 1f }
                })

                llRatingBar.addView(RatingBar(this@ResultActivity).apply {
                    numStars = 5
                    rating = counts[idx].toFloat()
                    stepSize = 1f
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                })

            }
        }
    }
}