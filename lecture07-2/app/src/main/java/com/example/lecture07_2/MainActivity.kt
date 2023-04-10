package com.example.lecture07_2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.marginTop
import com.example.lecture07_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        registerForContextMenu(binding.btnChangeBackground)
        registerForContextMenu(binding.btnChangeButton)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        when (v?.id) {
            R.id.btn_change_background -> {
                menu?.add(0, 0, 0, "빨강색")
                menu?.add(0, 1, 0, "초록색")
                menu?.add(0, 2, 0, "파랑색")
            }
            R.id.btn_change_button -> {
                menu?.add(1, 3, 0, "버튼 45도 회전")
                menu?.add(1, 4, 0, "버튼 2배 확대")
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.groupId) {
            0 -> when (item.itemId) {
                0 -> binding.root.setBackgroundColor(Color.RED)
                1 -> binding.root.setBackgroundColor(Color.GREEN)
                2 -> binding.root.setBackgroundColor(Color.BLUE)
            }

            1 -> {
                when (item.itemId) {
                    3 -> binding.btnChangeButton.rotation = 45f
                    4 -> binding.btnChangeButton.scaleX = 2f
                }
            }
        }
        return false
    }
}