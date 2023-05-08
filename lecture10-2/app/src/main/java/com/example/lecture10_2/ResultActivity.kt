package com.example.lecture10_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lecture10_2.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private val binding by lazy { ActivityResultBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnFinish.setOnClickListener {
            val outIntent = Intent(this@ResultActivity, MainActivity::class.java)
            outIntent.putExtra("result", intent.getDoubleExtra("result", 0.0))
            setResult(RESULT_OK, outIntent)
            finish()
        }
    }
}