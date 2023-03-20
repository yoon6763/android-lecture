package com.example.lecture04_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et_num1 = findViewById<EditText>(R.id.et_num1)
        val et_num2 = findViewById<EditText>(R.id.et_num2)

        val tv_result = findViewById<TextView>(R.id.tv_result)

        val btn_plus = findViewById<TextView>(R.id.btn_plus)
        val btn_minus = findViewById<TextView>(R.id.btn_minus)
        val btn_multi = findViewById<TextView>(R.id.btn_multi)
        val btn_divide = findViewById<TextView>(R.id.btn_divide)
        val btn_mod = findViewById<TextView>(R.id.btn_mod)

        btn_plus.setOnClickListener(this)
        btn_minus.setOnClickListener(this)
        btn_multi.setOnClickListener(this)
        btn_divide.setOnClickListener(this)
        btn_mod.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val tv_result = findViewById<TextView>(R.id.tv_result)
        var result = 0.0

        when (v!!.id) {
            R.id.btn_plus -> {
                val num1 = findViewById<EditText>(R.id.et_num1).text.toString().toDouble()
                val num2 = findViewById<EditText>(R.id.et_num2).text.toString().toDouble()
                result = num1 + num2
            }
            R.id.btn_minus -> {
                val num1 = findViewById<EditText>(R.id.et_num1).text.toString().toDouble()
                val num2 = findViewById<EditText>(R.id.et_num2).text.toString().toDouble()
                result = num1 - num2
            }
            R.id.btn_multi -> {
                val num1 = findViewById<EditText>(R.id.et_num1).text.toString().toDouble()
                val num2 = findViewById<EditText>(R.id.et_num2).text.toString().toDouble()
                result = num1 * num2
            }
            R.id.btn_divide -> {
                val num1 = findViewById<EditText>(R.id.et_num1).text.toString().toDouble()
                val num2 = findViewById<EditText>(R.id.et_num2).text.toString().toDouble()
                result = num1 / num2
            }
            R.id.btn_mod -> {
                val num1 = findViewById<EditText>(R.id.et_num1).text.toString().toDouble()
                val num2 = findViewById<EditText>(R.id.et_num2).text.toString().toDouble()
                result = num1 % num2
            }
        }

        tv_result.text = "계산결과 : $result"
    }
}