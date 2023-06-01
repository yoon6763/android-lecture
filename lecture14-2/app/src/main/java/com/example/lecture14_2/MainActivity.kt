package com.example.lecture14_2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lecture14_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val br = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent?.action

            if (action == Intent.ACTION_BATTERY_CHANGED) {
                val remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
                binding.editText.setText("현재 충전량: $remain")

                when {
                    remain >= 90 -> binding.imageView.setImageResource(R.drawable.battery_100)
                    remain >= 70 -> binding.imageView.setImageResource(R.drawable.battery_80)
                    remain >= 50 -> binding.imageView.setImageResource(R.drawable.battery_60)
                    remain >= 10 -> binding.imageView.setImageResource(R.drawable.battery_20)
                    else -> binding.imageView.setImageResource(R.drawable.battery_0)
                }

                when (intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0)) {
                    0 -> binding.editText.append("\n전원 연결: 안됨")
                    BatteryManager.BATTERY_PLUGGED_AC -> binding.editText.append("\n전원 연결: 어댑터 연결됨")
                    BatteryManager.BATTERY_PLUGGED_USB -> binding.editText.append("\n전원 연결: USB 연결됨")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(br, filter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(br)
    }
}