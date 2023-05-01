package com.example.exam_middle_1.enums

import com.example.exam_middle_1.R

enum class Foods(val price: Int, val image: Int, val type: String) {
    ITALIAN(10000, R.drawable.img_italian_food, "양식"),
    KOREAN(15000, R.drawable.img_korean_food, "한식"),
    JAPANESE(18000, R.drawable.img_japanese_food, "일식"),
    NONE(0, 0, "")
}