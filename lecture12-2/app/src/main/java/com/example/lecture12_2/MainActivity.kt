package com.example.lecture12_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lecture12_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val myDBHelper = MyDBHelper(this)

        binding.run {
            btnInit.setOnClickListener {
                val sqlDB = myDBHelper.writableDatabase
                myDBHelper.onUpgrade(sqlDB, 1, 2)
                sqlDB.close()
            }

            btnInsert.setOnClickListener {
                val sqlDB = myDBHelper.writableDatabase
                sqlDB.execSQL("insert into groupTBL values ('${etName.text}', ${etNumber.text})")
                sqlDB.close()

                etName.setText("")
                etNumber.setText("")

                Toast.makeText(applicationContext, "입력됨", Toast.LENGTH_SHORT).show()
            }

            btnSelect.setOnClickListener {
                val sqlDB = myDBHelper.readableDatabase
                val cursor = sqlDB.rawQuery("select * from groupTBL", null)

                val sbNames = StringBuffer()
                val sbNumbers = StringBuffer()

                while (cursor.moveToNext()) {
                    sbNames.append(cursor.getString(0)).append("\n")
                    sbNumbers.append(cursor.getString(1)).append("\n")
                }

                tvGroup.text = sbNames.toString()
                tvNumber.text = sbNumbers.toString()

                cursor.close()
                sqlDB.close()
            }

            btnDelete.setOnClickListener {
                val sqlDB = myDBHelper.writableDatabase
                sqlDB.execSQL("delete from groupTBL where gName = '${etName.text}'")
                sqlDB.close()

                etName.setText("")
                etNumber.setText("")

                Toast.makeText(applicationContext, "삭제됨", Toast.LENGTH_SHORT).show()
            }

            btnUpdate.setOnClickListener {
                val sqlDB = myDBHelper.writableDatabase
                sqlDB.execSQL("update groupTBL set gNumber = ${etNumber.text} where gName = '${etName.text}'")
                sqlDB.close()

                etName.setText("")
                etNumber.setText("")

                Toast.makeText(applicationContext, "수정됨", Toast.LENGTH_SHORT).show()
            }
        }
    }
}