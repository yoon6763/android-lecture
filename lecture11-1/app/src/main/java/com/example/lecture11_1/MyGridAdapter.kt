package com.example.lecture11_1

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class MyGridAdapter(val context: Context, val posterIds: Array<Int>) : BaseAdapter() {
    override fun getCount(): Int {
        return posterIds.size
    }

    override fun getItem(position: Int): Any?{
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val posterView = ImageView(context)
        posterView.layoutParams = ViewGroup.LayoutParams(200, 300)
        posterView.scaleType = ImageView.ScaleType.FIT_CENTER
        posterView.setPadding(5, 5, 5, 5)
        posterView.setImageResource(posterIds[position])
        return posterView
    }
}