package com.example.lecture09_02

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import com.example.lecture09_02.databinding.ActivityMainBinding
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private val myCanvas by lazy { MyCanvas(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(myCanvas)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onOptionsMenuClosed(menu)
        menu?.add(0, 1, 0, "선 그리기")
        menu?.add(0, 2, 0, "원 그리기")
        menu?.add(0, 3, 0, "사각형 그리기")
        menu?.addSubMenu(1, 0, 0, "색상 변경>>")?.apply {
            add(1, 4, 0, "파랑")
            add(1, 5, 0, "빨강")
            add(1, 6, 0, "초록")
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            1 -> myCanvas.type = Type.LINE
            2 -> myCanvas.type = Type.CIRCLE
            3 -> myCanvas.type = Type.RECT

            4 -> myCanvas.color = Color.BLUE
            5 -> myCanvas.color = Color.RED
            6 -> myCanvas.color = Color.GREEN
        }
        return super.onOptionsItemSelected(item)
    }
}

private class MyCanvas(context: Context) : View(context) {

    private var startX = 0f
    private var startY = 0f
    private var stopX = 0f
    private var stopY = 0f
    var color = Color.RED
    var type = Type.LINE


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint = android.graphics.Paint()
        paint.color = color
        paint.strokeWidth = 10f
        when (type) {
            Type.LINE -> canvas?.drawLine(startX, startY, stopX, stopY, paint)
            Type.RECT -> canvas?.drawRect(startX, startY, stopX, stopY, paint)
            Type.CIRCLE -> {
                val radius = getRadius()
                canvas?.drawCircle(
                    startX + if (startX > stopX) -radius else radius,
                    startY + if (startY > stopY) -radius else radius,
                    radius,
                    paint
                )
            }
        }
    }

    fun getRadius(): Float {
        return sqrt(
            (stopX - startX).toDouble().pow(2.0) + (stopY - startY).toDouble().pow(2.0)
        ).toFloat() / 2
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                startY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                stopX = event.x
                stopY = event.y
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                stopX = event.x
                stopY = event.y
                invalidate()
            }
        }
        return true
    }
}