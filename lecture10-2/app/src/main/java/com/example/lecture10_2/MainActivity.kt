package com.example.lecture10_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lecture10_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val REQUEST_CODE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.run {

            btnCalc.setOnClickListener {
                val n1 = etNum1.text.toString().toDouble()
                val n2 = etNum2.text.toString().toDouble()

                val result = when (rgOperator.checkedRadioButtonId) {
                    R.id.rb_add -> n1 + n2
                    R.id.rb_sub -> n1 - n2
                    R.id.rb_mul -> n1 * n2
                    R.id.rb_div -> n1 / n2
                    else -> {
                        Toast.makeText(this@MainActivity, "연산자를 선택하세요.", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                }

                val intent = Intent(this@MainActivity, ResultActivity::class.java)
                intent.putExtra("result", result)
                startActivityForResult(intent, REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val result = data?.getDoubleExtra("result", 0.0)
            Toast.makeText(this, "결과: $result", Toast.LENGTH_SHORT).show()
        }
    }
}