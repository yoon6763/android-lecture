package com.example.exam_middle_1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.exam_middle_1.enums.Foods
import com.example.exam_middle_1.enums.Options
import com.example.exam_middle_1.enums.SeatType

data class Reserve(
    val peopleCnt: MutableLiveData<Int> = MutableLiveData(1),
    val foodType: MutableLiveData<Foods> = MutableLiveData(Foods.NONE),
    val optionPrice: LiveDataList<Options> = LiveDataList(),
    val seatType: MutableLiveData<SeatType> = MutableLiveData(SeatType.NONE),
    val total: MutableLiveData<Int> = MutableLiveData(0)
) {

    private val observer = Observer<Any> { total.value = calc() }

    init {
        /*
            Reserve 클래스의 각 프로퍼티는 LiveData 이므로,
            observer를 등록하여 값이 변경될 때마다 total을 계산하도록 한다.
         */

        peopleCnt.observeForever(observer)
        foodType.observeForever(observer)
        optionPrice.observeForever(observer)
        seatType.observeForever(observer)
    }

    fun peopleCntPlus() {
        peopleCnt.value = peopleCnt.value!! + 1
    }

    fun peopleCntMinus() {
        if (peopleCnt.value!! > 0) peopleCnt.value = peopleCnt.value!! - 1
    }

    private fun calc(): Int {
        return (foodType.value!!.price +
                optionPrice.value!!.count() * 10000 +
                seatType.value!!.price) *
                peopleCnt.value!!
    }

    override fun toString(): String {
        return "Food Type : ${foodType.value!!.type}\n" +
                "Option : ${
                    if (optionPrice.value!!.isEmpty()) "옵션이 없습니다"
                    else optionPrice.value!!.joinToString(
                        ", ",
                        transform = { it.type }
                    )
                }\n" +
                "Seat Type : ${seatType.value!!.type}\n" +
                "People : ${peopleCnt.value}명\n" +
                "\n" +
                "Total Price : ${String.format("%,d", total.value)}원"
    }
}
