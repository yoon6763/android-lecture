package com.example.lecture04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val iv_pet = findViewById<ImageView>(R.id.iv_pet)
        val ck_start = findViewById<CheckBox>(R.id.ck_start)
        val btn_select_finish = findViewById<Button>(R.id.btn_select_finish)
        val rg_pet = findViewById<RadioGroup>(R.id.rg_pet)
        val ll_select = findViewById<LinearLayout>(R.id.ll_select)

        ck_start.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                ll_select.visibility = View.VISIBLE
            } else {
                ll_select.visibility = View.GONE
            }
        }

        btn_select_finish.setOnClickListener {
            if (!ck_start.isChecked) {
                Toast.makeText(applicationContext, "시작함 체크박스를 클릭해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            iv_pet.visibility = View.VISIBLE

            when (rg_pet.checkedRadioButtonId) {
                R.id.rb_cat -> iv_pet.setImageResource(R.drawable.cat)
                R.id.rb_dog -> iv_pet.setImageResource(R.drawable.dog)
                R.id.rb_rabbit -> iv_pet.setImageResource(R.drawable.rabit)
            }
        }
    }
}