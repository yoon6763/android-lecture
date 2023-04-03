package com.example.lecture06_3

import android.app.TabActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TabHost.TabSpec
import com.example.lecture06_3.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : TabActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.run {
            val tabSpec1 = tabHost.newTabSpec("고양이")
            tabSpec1.setContent(R.id.iv1)
            tabSpec1.setIndicator("고양이")

            val tabSpec2 = tabHost.newTabSpec("애용이")
            tabSpec2.setContent(R.id.iv2)
            tabSpec2.setIndicator("애용이")

            val tabSpec3 = tabHost.newTabSpec("냥냥이")
            tabSpec3.setContent(R.id.iv3)
            tabSpec3.setIndicator("냥냥이")

            val tabSpec4 = tabHost.newTabSpec("재드래곤")
            tabSpec4.setContent(R.id.iv4)
            tabSpec4.setIndicator("재드래곤")

            tabHost.addTab(tabSpec1)
            tabHost.addTab(tabSpec2)
            tabHost.addTab(tabSpec3)
            tabHost.addTab(tabSpec4)
        }
    }
}