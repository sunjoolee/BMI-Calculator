package com.sunjoolee.sparta_week1_bmi.result

import android.util.Log
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.sunjoolee.sparta_week1_bmi.R
import kotlin.math.round

class ResultScreenStateHolder(height: Float, weight: Float) {
    private val bmi: Float

    @StringRes
    var bmiInfoId: Int = 0

    @ColorRes
    var bmiInfoTextColorId: Int = 0

    @DrawableRes
    var bmiEmojiId: Int = 0

    init {
        Log.d("ResultScreenStateHolder", "height: $height, weight: $weight")
        bmi = (weight / (height * height) * 10000F).run { round(this * 10.0F) / 10.0F }
        Log.d("ResultScreenStateHolder", "bmi: $bmi")

        when (bmi) {
            in 12.0..18.4 -> {
                bmiInfoId = R.string.result_level_under
                bmiInfoTextColorId = R.color.level1_text_color
                bmiEmojiId = R.drawable.bmi1
            }

            in 18.5..24.9 -> {
                bmiInfoId = R.string.result_level_normal
                bmiInfoTextColorId = R.color.level2_text_color
                bmiEmojiId = R.drawable.bmi2
            }

            in 25.0..29.9 -> {
                bmiInfoId = R.string.result_level_over
                bmiInfoTextColorId = R.color.level3_text_color
                bmiEmojiId = R.drawable.bmi3
            }

            in 30.0..39.9 -> {
                bmiInfoId = R.string.result_level_obese
                bmiInfoTextColorId = R.color.level4_text_color
                bmiEmojiId = R.drawable.bmi4
            }

            in 40.0..42.0 -> {
                bmiInfoId = R.string.result_level_extreme
                bmiInfoTextColorId = R.color.level5_text_color
                bmiEmojiId = R.drawable.bmi5
            }

            else -> {
                bmiInfoId = R.string.result_level_wrong
                bmiInfoTextColorId = R.color.level5_text_color
                bmiEmojiId = R.drawable.bmi5
            }
        }
    }

    fun getBmiValue() = if (isBmiValid(bmi)) bmi.toString() else ""

    private fun isBmiValid(bmi: Float): Boolean {
        Log.d("ResultScreenStateHolder", "isNan: ${bmi.isNaN()}")
        return !((bmi.isNaN()) || (bmi < 12.0) || (bmi > 42.0))
    }
}