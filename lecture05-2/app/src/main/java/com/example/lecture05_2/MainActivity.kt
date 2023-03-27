package com.example.lecture05_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = arrayOfNulls<Button>(10)
        val et_num_1 = findViewById<EditText>(R.id.et_num_1)
        val et_num_2 = findViewById<EditText>(R.id.et_num_2)

        for (i in 0..9) {
            button[i] =
                findViewById(resources.getIdentifier("btn_$i", "id", "com.example.lecture05_2"))

            button[i]?.setOnClickListener {
                if (et_num_1.isFocused) {
                    et_num_1.append(i.toString())
                } else if (et_num_2.isFocused) {
                    et_num_2.append(i.toString())
                }
            }
        }

        val tv_result = findViewById<TextView>(R.id.tv_result)

        var result = 0.0

        val calcBtnClickListener = OnClickListener {
            val num1 = et_num_1.text.toString().toDouble()
            val num2 = et_num_2.text.toString().toDouble()

            when (it.id) {
                R.id.btn_plus -> result = num1 + num2
                R.id.btn_minus -> result = num1 - num2
                R.id.btn_multiply -> result = num1 * num2
                R.id.btn_divide -> result = num1 / num2
            }
            tv_result.text = "계산 결과 : $result"
        }

        findViewById<Button>(R.id.btn_plus).setOnClickListener(calcBtnClickListener)
        findViewById<Button>(R.id.btn_minus).setOnClickListener(calcBtnClickListener)
        findViewById<Button>(R.id.btn_multiply).setOnClickListener(calcBtnClickListener)
        findViewById<Button>(R.id.btn_divide).setOnClickListener(calcBtnClickListener)
    }
}