package com.example.exam_middle_02

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputFilter
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var firstName = ""
        var lastName = ""
        var birth = LocalDate.now()
        var gender = -1


        title = "Mobile Programming Class Registration"

        setContentView(LinearLayout(this@MainActivity).apply {
            orientation = LinearLayout.VERTICAL
            addView(LinearLayout(this@MainActivity).apply {
                orientation = LinearLayout.HORIZONTAL

                addView(TextView(this@MainActivity).apply {
                    text = "First Name"
                })
                addView(EditText(this@MainActivity).apply {
                    hint = "First Name"
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    // 최대 10자까지 입력 가능
                    filters = arrayOf(InputFilter.LengthFilter(10))

                    addTextChangedListener {
                        firstName = it.toString()
                    }
                })
            })

            addView(LinearLayout(this@MainActivity).apply {
                orientation = LinearLayout.HORIZONTAL
                addView(TextView(this@MainActivity).apply {
                    text = "Last Name"
                })
                addView(EditText(this@MainActivity).apply {
                    hint = "Last Name"
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )

                    // 최대 10자까지 입력 가능
                    filters = arrayOf(InputFilter.LengthFilter(10))

                    addTextChangedListener {
                        lastName = it.toString()
                    }
                })
            })

            addView(LinearLayout(this@MainActivity).apply {
                orientation = LinearLayout.HORIZONTAL

                addView(TextView(this@MainActivity).apply {
                    text = "Gender"
                })
                addView(RadioGroup(this@MainActivity).apply {
                    orientation = LinearLayout.HORIZONTAL

                    addView(RadioButton(this@MainActivity).apply {
                        id = 0
                        text = "Male"
                    })
                    addView(RadioButton(this@MainActivity).apply {
                        id = 1
                        text = "Female"
                    })

                    setOnCheckedChangeListener { _, checkedId ->
                        gender = if (checkedId == 0) 0 else 1
                    }
                })
            })

            addView(LinearLayout(this@MainActivity).apply {
                orientation = LinearLayout.HORIZONTAL
                addView(
                    TextView(this@MainActivity).apply {
                        text = "Birthday"
                    })
                addView(
                    CalendarView(this@MainActivity).apply {
                        setOnDateChangeListener { _, year, month, dayOfMonth ->
                            birth = LocalDate.of(year, month + 1, dayOfMonth)
                        }
                    })
            })

            addView(Button(this@MainActivity).apply {
                text = "SUBMIT"
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 50, 0, 0)
                    gravity = Gravity.CENTER
                }
                setOnClickListener {
                    if (firstName.isBlank() || firstName.isEmpty()) {
                        requireMoreInfo()
                        return@setOnClickListener
                    }
                    if (lastName.isEmpty() || lastName.isBlank()) {
                        requireMoreInfo()
                        return@setOnClickListener
                    }
                    if (gender == -1) {
                        requireMoreInfo()
                        return@setOnClickListener
                    }
                    if (birth == null) {
                        requireMoreInfo()
                        return@setOnClickListener
                    }

                    Toast.makeText(
                        applicationContext,
                        "${birth.format(DateTimeFormatter.ofPattern("yy-MM-dd"))} " +
                                "${lastName}${firstName}" +
                                "${if (gender == 0) "(남)" else "(여)"}" +
                                "님 가입을 환영합니다",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        })
    }

    private fun requireMoreInfo() {
        Toast.makeText(applicationContext, "정보가 부족합니다.", Toast.LENGTH_SHORT).show()
    }
}