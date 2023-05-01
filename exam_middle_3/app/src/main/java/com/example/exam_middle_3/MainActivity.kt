package com.example.exam_middle_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.exam_middle_3.databinding.ActivityMainBinding
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.sign

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var nowNumber = ""
    private val expressionList = arrayOf("", "", "", "", "")
    private var expressionIndex = 0
    private val operatorPriority = listOf("^", "%", "*", "/", "+", "-")
    private val operation = arrayOf(
        { a: BigDecimal, b: BigDecimal -> a.pow(b.toInt()) },
        { a: BigDecimal, b: BigDecimal -> a % b },
        { a: BigDecimal, b: BigDecimal -> a * b },
        { a: BigDecimal, b: BigDecimal -> a.divide(b, 10, RoundingMode.HALF_UP) },
        { a: BigDecimal, b: BigDecimal -> a + b },
        { a: BigDecimal, b: BigDecimal -> a - b }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.run {
            val numberBtnList = arrayOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
            val operBtnList = arrayOf(
                Pair(btnAdd, "+"),
                Pair(btnSub, "-"),
                Pair(btnMul, "*"),
                Pair(btnMod, "%"),
                Pair(btnDiv, "/"),
                Pair(btnSquare, "^")
            )

            cbAdvanced.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    btnMod.visibility = android.view.View.VISIBLE
                    btnSquare.visibility = android.view.View.VISIBLE
                } else {
                    btnMod.visibility = android.view.View.INVISIBLE
                    btnSquare.visibility = android.view.View.INVISIBLE
                }
            }

            numberBtnList.forEachIndexed { index, button ->
                button.setOnClickListener {

                    if (expressionIndex > 0 &&
                        expressionList[expressionIndex - 1] == "/" &&
                        index == 0 &&
                        nowNumber.isEmpty()
                    ) {
                        showToast("0으로 나눌 수 없습니다")
                        return@setOnClickListener
                    }

                    if (expressionIndex > 0 &&
                        expressionList[expressionIndex - 1] == "^" &&
                        nowNumber.isNotEmpty()
                    ) {
                        showToast("제곱은 1자리 숫자만 사용할 수 있습니다")
                        return@setOnClickListener
                    }

                    nowNumber += index.toString()
                    textViewUpdate()

                    // 01, 02 등 앞에 0이 붙어있으면 제거. 단 0.1234 등 소숫점이 있는 경우는 제외
                    if (nowNumber.length > 1 && nowNumber[0] == '0' && nowNumber[1] != '.') {
                        nowNumber = nowNumber.substring(1)
                        textViewUpdate()
                    }
                }
            }


            btnDot.setOnClickListener {
                if (nowNumber.isEmpty()) {
                    nowNumber += "0."
                } else if (!nowNumber.contains(".")) {
                    nowNumber += "."
                }
                textViewUpdate()
            }

            operBtnList.forEach { (btn, oper) ->
                btn.setOnClickListener {
                    if (expressionIndex >= 3) {
                        showToast("연산자는 3개까지만 사용할 수 있습니다")
                        return@setOnClickListener
                    }

                    if (nowNumber.isNotEmpty()) {
                        expressionList[expressionIndex++] = nowNumber
                        nowNumber = ""
                        expressionList[expressionIndex++] = oper
                        textViewUpdate()
                    } else {
                        textViewUpdate()
                    }
                }
            }


            btnDelete.setOnClickListener {
                if (nowNumber != "") {
                    nowNumber = nowNumber.substring(0, nowNumber.length - 1)

                    if (nowNumber.isEmpty()) {
                        if (expressionIndex > 0) {
                            expressionIndex--
                            nowNumber = expressionList[expressionIndex]
                            expressionList[expressionIndex] = ""
                        }
                    }
                } else {
                    if (expressionIndex > 0) {
                        expressionIndex--
                        nowNumber = expressionList[expressionIndex]
                        expressionList[expressionIndex] = ""
                    }
                }

                textViewUpdate()
            }

            btnEqual.setOnClickListener {
                if (expressionIndex == 0) {
                    return@setOnClickListener
                }
                if (nowNumber.isNotEmpty()) {
                    expressionList[expressionIndex] = nowNumber
                    expressionIndex++
                    nowNumber = ""
                    textViewUpdate()
                }

                try {
                    calculate()
                } catch (e: Exception) {
                    showToast("잘못된 수식입니다")
                    expressionIndex = 0
                    expressionList.fill("")
                }
            }

        }
    }

    private fun calculate() {
        val oper1 = operatorPriority.indexOf(expressionList[1])
        val oper2 = operatorPriority.indexOf(expressionList[3])

        var result = BigDecimal(0)

        if (oper2 == -1) {
            // 두 번째 연산자가 없다면 -> 2개의 피연산자와 1개의 연산자만 존재
            result = operation[oper1](
                expressionList[0].toBigDecimal(),
                expressionList[2].toBigDecimal()
            )
        } else {
            if (oper1 < oper2) {
                // 고차함수 사용
                result = operation[oper2](
                    operation[oper1](
                        expressionList[0].toBigDecimal(),
                        expressionList[2].toBigDecimal()
                    ),
                    expressionList[4].toBigDecimal()
                )
            } else {
                result = operation[oper1](
                    expressionList[0].toBigDecimal(),
                    operation[oper2](
                        expressionList[2].toBigDecimal(),
                        expressionList[4].toBigDecimal()
                    )
                )
            }
        }

        expressionIndex = 0
        expressionList.fill("")

        binding.tvResult.text = DecimalFormat("#.##########").format(result)
    }


    private fun textViewUpdate() {
        var string = ""
        for (i in 0 until expressionIndex) string += expressionList[i]
        string += nowNumber

        binding.tvResult.text = string
    }

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}