package com.example.exam_middle_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton.OnCheckedChangeListener
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import com.example.exam_middle_1.databinding.ActivityMainBinding
import com.example.exam_middle_1.enums.Foods
import com.example.exam_middle_1.enums.Options
import com.example.exam_middle_1.enums.SeatType

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "2023_MidExam_1"
        val reserve = Reserve()

        binding.run {

            // Livedata를 사용하여 데이터를 관찰하고, 데이터가 변경되면 UI를 업데이트
            reserve.total.observe(this@MainActivity) { tvTotal.text = it.toString() }
            reserve.peopleCnt.observe(this@MainActivity) { tvNumberOfPeople.text = it.toString() }
            reserve.foodType.observe(this@MainActivity) { ivFoodImage.setImageResource(it.image) }


            // 라디오버튼 / 인원수가 변경되면 데이터를 변경
            rgFoodType.setOnCheckedChangeListener { _, id -> reserve.foodType.value = getFoodType(id) }
            btnMinus.setOnClickListener { reserve.peopleCntMinus() }
            btnPlus.setOnClickListener { reserve.peopleCntPlus() }
            rgSeatType.setOnCheckedChangeListener { _, id -> reserve.seatType.value = getSeatType(id) }


            // 체크박스 공통 리스너
            val optionListener = OnCheckedChangeListener { v, isChecked ->
                reserve.optionPrice.run {
                    if (isChecked) add(getOption(v.id))
                    else remove(getOption(v.id))
                }
            }


            cbAppetizer.setOnCheckedChangeListener(optionListener)
            cbVegetarian.setOnCheckedChangeListener(optionListener)
            cbDessert.setOnCheckedChangeListener(optionListener)

            btnFinish.setOnClickListener {
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("예약 확인")
                    .setMessage(reserve.toString())
                    .setPositiveButton("확인") { dialog, _ ->
                        Toast.makeText(
                            applicationContext, "예약이 완료되었습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }

    private fun getFoodType(id: Int): Foods {
        return when (id) {
            R.id.rb_italian -> Foods.ITALIAN
            R.id.rb_korean -> Foods.KOREAN
            R.id.rb_japanese -> Foods.JAPANESE
            else -> Foods.NONE
        }
    }

    private fun getSeatType(id: Int): SeatType {
        return when (id) {
            R.id.rb_window -> SeatType.WINDOW
            R.id.rb_anywhere -> SeatType.ANYWHERE
            else -> SeatType.NONE
        }
    }

    private fun getOption(id: Int): Options {
        return when (id) {
            R.id.cb_appetizer -> Options.APPETIZER
            R.id.cb_vegetarian -> Options.VEGETARIAN
            R.id.cb_dessert -> Options.DESSERT
            else -> Options.NONE
        }
    }
}