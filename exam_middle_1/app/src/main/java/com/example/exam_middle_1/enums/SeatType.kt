package com.example.exam_middle_1.enums

enum class SeatType(val price: Int, val type: String) {
    WINDOW(5000, "창가측"),
    ANYWHERE(0, "아무데나"),
    NONE(0, "")
}