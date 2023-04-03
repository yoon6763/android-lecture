package com.example.lecture06_1_practice

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import com.example.lecture06_1_practice.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dateTimeFormat = SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분")
        val cal = Calendar.getInstance()

        binding.run {
            cvDate.visibility = View.GONE
            tpTime.visibility = View.GONE

            btnStart.setOnClickListener {
                crTimer.base = SystemClock.elapsedRealtime()
                crTimer.start()
                crTimer.setTextColor(Color.RED)
                rgTime.visibility = View.VISIBLE
            }

            rbTime.setOnClickListener { setPickerVisibility(true) }
            rbDate.setOnClickListener { setPickerVisibility(false) }

            cvDate.setOnDateChangeListener { view, year, month, dayOfMonth ->
                cal[Calendar.YEAR] = year
                cal[Calendar.MONTH] = month
                cal[Calendar.DAY_OF_MONTH] = dayOfMonth
            }

            btnFinish.setOnClickListener {
                crTimer.stop()
                crTimer.setTextColor(Color.BLUE)
                cal[Calendar.HOUR_OF_DAY] = tpTime.hour
                cal[Calendar.MINUTE] = tpTime.minute

                tvReversed.text = dateTimeFormat.format(cal.time)
            }
        }
    }

    private fun setPickerVisibility(isTime: Boolean) = with(binding) {
        if (isTime) {
            cvDate.visibility = View.GONE
            tpTime.visibility = View.VISIBLE
        } else {
            cvDate.visibility = View.VISIBLE
            tpTime.visibility = View.GONE
        }
    }
}