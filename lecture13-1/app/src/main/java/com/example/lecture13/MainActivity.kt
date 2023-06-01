package com.example.lecture13

import android.content.ContentResolver
import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.annotation.AnyRes
import androidx.appcompat.app.AppCompatActivity
import com.example.lecture13.databinding.ActivityMainBinding
import java.io.File


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var selectedIdx = 0

        binding.run {
            val mp3Files = mutableListOf<Int>()

            mp3Files.add(R.raw.song1)
            mp3Files.add(R.raw.song2)
            mp3Files.add(R.raw.song3)
            mp3Files.add(R.raw.song4)

            var mediaPlayer = MediaPlayer.create(this@MainActivity, mp3Files[0])

            listView.adapter = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_list_item_single_choice,
                "song1 song2 song3 song4".split(" ")
            )
            listView.choiceMode = android.widget.ListView.CHOICE_MODE_SINGLE

            listView.setOnItemClickListener { parent, view, position, id ->
                selectedIdx = position
            }

            btnPlay.setOnClickListener {
                mediaPlayer = MediaPlayer.create(this@MainActivity, mp3Files[selectedIdx])
                mediaPlayer.reset()
                mediaPlayer.setDataSource(this@MainActivity, getResourceUri(mp3Files[selectedIdx]))
                mediaPlayer.prepare()
                mediaPlayer.start()
            }

            btnStop.setOnClickListener {
                mediaPlayer.stop()
                mediaPlayer.reset()
            }
        }
    }

    private fun getResourceUri(@AnyRes resourceId: Int): Uri = Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .authority(packageName)
        .path(resourceId.toString())
        .build()
}