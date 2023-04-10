package com.example.lecture07_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.lecture07_3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        title = "시용자 정보 입력"

        binding.run {
            btnClickHere.setOnClickListener {
                val dialogView = layoutInflater.inflate(R.layout.dialog1, null)
                val dialogEtName = dialogView.findViewById<EditText>(R.id.et_user_name)
                val dialogEtEmail = dialogView.findViewById<EditText>(R.id.et_user_email)

                AlertDialog.Builder(this@MainActivity).apply {

                    dialogEtEmail.setText(etEmail.text.toString())
                    dialogEtName.setText(etName.text.toString())

                    title = "사용자 정보 입력"

                    setView(dialogView)
                    setPositiveButton("확인") { _, _ ->
                        binding.etName.setText(dialogEtName.text.toString())
                        binding.etEmail.setText(dialogEtEmail.text.toString())
                    }
                    setNegativeButton("취소") { _, _ ->
                        val toast = Toast(this@MainActivity)
                        val toastView = layoutInflater.inflate(R.layout.toast1, null)
                        toastView.findViewById<TextView>(R.id.tv_toast_msg).text = "취소했습니다."

                        toast.view = toastView
                        toast.show()
                    }
                }.show()
            }
        }
    }
}