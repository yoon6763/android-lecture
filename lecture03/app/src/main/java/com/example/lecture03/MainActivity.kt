package com.example.lecture03

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var etNum1: EditText
    private lateinit var etNum2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNum1 = findViewById(R.id.et_num1)
        etNum2 = findViewById(R.id.et_num2)

        findViewById<Button>(R.id.btn_add).setOnClickListener(this)
        findViewById<Button>(R.id.btn_sub).setOnClickListener(this)
        findViewById<Button>(R.id.btn_multi).setOnClickListener(this)
        findViewById<Button>(R.id.btn_div).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val n1 = etNum1.text.toString().toInt()
        val n2 = etNum2.text.toString().toInt()

        findViewById<TextView>(R.id.et_result).text =
            "계산결과 : ${
                when (v!!.id) {
                    R.id.btn_add -> n1 + n2
                    R.id.btn_sub -> n1 - n2
                    R.id.btn_multi -> n1 * n2
                    R.id.btn_div -> n1 / n2
                    else -> "0"
                }
            }"
    }
}