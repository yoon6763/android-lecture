package com.example.lecture11_2

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Gallery
import android.widget.ImageView

public class MyGalleryAdapter(private val context: Context, private val posterIds: Array<Int>) :
    BaseAdapter() {

    lateinit var galleryItemTouchListener: GalleryItemTouchListener

    override fun getCount(): Int {
        return posterIds.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val posterView = ImageView(context)
        posterView.layoutParams = Gallery.LayoutParams(200, 300)
        posterView.scaleType = ImageView.ScaleType.FIT_CENTER
        posterView.setPadding(5, 5, 5, 5)
        posterView.setImageResource(posterIds[position])

        posterView.setOnTouchListener { v, event ->
            galleryItemTouchListener.onItemTouch(position)
            false
        }

        return posterView
    }
}