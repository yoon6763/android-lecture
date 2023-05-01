package com.example.exam_middle_1

import androidx.lifecycle.LiveData

/*
    LiveData는 add / remove 메소드 시 이벤트를 발생시키지 않으므로
    add / remove 시 이벤트를 발생시키기 위해 만든 커스텀 클래스
 */

class LiveDataList<T> : LiveData<List<T>>() {
    private val list = mutableListOf<T>()

    init {
        value = list
    }

    fun add(item: T) {
        list.add(item)
        value = list
    }

    fun remove(item: T) {
        list.remove(item)
        value = list
    }
}