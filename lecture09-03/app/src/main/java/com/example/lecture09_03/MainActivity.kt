package com.example.lecture09_03

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.example.lecture09_03.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val myCanvas by lazy { MyCanvas(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "미니 포토샵"

        binding.run {
            pictureLayout.addView(myCanvas)

            btnZoomIn.setOnClickListener(this@MainActivity)
            btnZoomOut.setOnClickListener(this@MainActivity)
            btnRotate.setOnClickListener(this@MainActivity)
            btnBright.setOnClickListener(this@MainActivity)
            btnDark.setOnClickListener(this@MainActivity)
            btnEmboss.setOnClickListener(this@MainActivity)
            btnBlur.setOnClickListener(this@MainActivity)
        }
    }

    override fun onClick(v: View?) = with(binding) {
        when (v?.id) {
            btnZoomIn.id -> {
                myCanvas.scaleX += 0.2f
                myCanvas.scaleY += 0.2f
            }
            btnZoomOut.id -> {
                myCanvas.scaleX -= 0.2f
                myCanvas.scaleY -= 0.2f
            }
            btnRotate.id -> {
                myCanvas.rotate += 20f
            }
            btnDark.id -> {
                myCanvas.bright -= 0.2f
            }
            btnBright.id -> {
                myCanvas.bright += 0.2f
            }
            btnEmboss.id -> {
                myCanvas.emboss = !myCanvas.emboss
                myCanvas.blur = false
            }
            btnBlur.id -> {
                myCanvas.blur = !myCanvas.blur
                myCanvas.emboss = false
            }
        }

        myCanvas.invalidate()
    }
}

private class MyCanvas(context: Context) : View(context) {

    var startX = 1f
    var startY = 1f
    var rotate = 0f
    var bright = 1f
    var emboss = false
    var blur = false

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val picture = BitmapFactory.decodeResource(resources, R.drawable.lena256)

        val picX = (width - picture.width) / 2f
        val picY = (height - picture.height) / 2f


        val brightMatrix = arrayOf(
            bright, 0f, 0f, 0f, 0f,
            0f, bright, 0f, 0f, 0f,
            0f, 0f, bright, 0f, 0f,
            0f, 0f, 0f, 1f, 0f
        )

        val embossMaskFilter = android.graphics.EmbossMaskFilter(
            floatArrayOf(
                3f, 3f, 3f
            ), 0.5f, 5f, 10f
        )

        val blurMaskFilter =
            android.graphics.BlurMaskFilter(30f, android.graphics.BlurMaskFilter.Blur.NORMAL)

        val cm = ColorMatrix(brightMatrix.toFloatArray())
        val paint = Paint()
        paint.colorFilter = android.graphics.ColorMatrixColorFilter(cm)


        if(!emboss && !blur) {
            paint.maskFilter = null
        } else if (emboss) {
            paint.maskFilter = embossMaskFilter
        } else {
            paint.maskFilter = blurMaskFilter
        }


        canvas?.scale(startX, startY)
        canvas?.rotate(rotate, width / 2f, height / 2f)

        canvas?.drawBitmap(picture, picX, picY, paint)
        picture.recycle()
    }
}