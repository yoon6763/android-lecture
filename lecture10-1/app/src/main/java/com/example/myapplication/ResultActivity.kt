package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.get
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

        val titles = arrayOf(
            "독서하는 소녀",
            "꽃장식 모자 소녀",
            "부채를 든 소녀",
            "이레느깡 단 베르양",
            "잠자는 소녀",
            "테라스의 두 자매",
            "피아노 레슨",
            "피아노 앞의 소녀들",
            "해변에서",
        )

        binding.run {
            val maxIdx = counts.indexOf(counts.maxOrNull())

            tvTitle.text = titles[maxIdx]
            ivImage.setImageDrawable(getDrawable(imageIds[maxIdx]))

            repeat(9) { idx ->
                ((llRating[idx] as LinearLayout)[0] as TextView).text = titles[idx]
                ((llRating[idx] as LinearLayout)[1] as RatingBar).rating = counts[idx].toFloat()
            }
        }
    }
}