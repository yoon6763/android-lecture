package com.example.lecture05_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )


        val baseLayout = LinearLayout(this)
        baseLayout.orientation = LinearLayout.VERTICAL
        baseLayout.layoutParams = params

        val etText = EditText(this)
        baseLayout.addView(etText)

        val button = Button(this)
        button.text = "버튼입니다"
        baseLayout.addView(button)

        val tvText = TextView(this)
        baseLayout.addView(tvText)



        button.setOnClickListener { tvText.text = etText.text.toString() }


        setContentView(baseLayout)
    }
}