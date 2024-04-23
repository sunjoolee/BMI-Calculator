package com.sunjoolee.sparta_week1_bmi.result

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.sunjoolee.sparta_week1_bmi.R
import kotlin.math.round

class ResultScreenStateHolder(height: Double, weight: Double) {
    val bmiValue: Double
    @StringRes var bmiInfoId: Int = 0
    @ColorRes var bmiInfoTextColorId: Int = 0
    @DrawableRes var bmiEmojiId: Int = 0

    init{
        bmiValue = (weight / (height * height)).apply {
            round(this * 100.0) / 100.0
        }
        val bmiLevel = when (bmiValue) {
            in 0.0..18.5 -> 1
            in 18.5..24.9 -> 2 //정상
            in 24.9..29.9 -> 3 //과체중
            in 29.9..39.9 -> 4 //비만
            else -> 5
        }
        when(bmiLevel) {
            1->{
                bmiInfoId = R.string.level1_info
                bmiInfoTextColorId = R.color.level1_text_color
                bmiEmojiId = R.drawable.bmi1
            }
            2->{
                bmiInfoId = R.string.level2_info
                bmiInfoTextColorId = R.color.level2_text_color
                bmiEmojiId = R.drawable.bmi2
            }
            3->{
                bmiInfoId = R.string.level3_info
                bmiInfoTextColorId = R.color.level3_text_color
                bmiEmojiId = R.drawable.bmi3
            }
            4->{
                bmiInfoId = R.string.level4_info
                bmiInfoTextColorId = R.color.level4_text_color
                bmiEmojiId = R.drawable.bmi4
            }
            5->{
                bmiInfoId = R.string.level5_info
                bmiInfoTextColorId = R.color.level5_text_color
                bmiEmojiId = R.drawable.bmi5
            }
            else ->{}
        }
    }
}