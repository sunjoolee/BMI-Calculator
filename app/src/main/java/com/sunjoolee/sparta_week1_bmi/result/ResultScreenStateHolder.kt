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

    var resultPanelTargetDegree:Float = 0.0F

    init {
        Log.d("ResultScreenStateHolder", "height: $height, weight: $weight")
        bmi = (weight / (height * height) * 10000F).run { round(this * 10.0F) / 10.0F }
        Log.d("ResultScreenStateHolder", "bmi: $bmi")

        when (bmi) {
            in 0.0..18.4 -> {
                bmiInfoId = R.string.result_level_under
                bmiInfoTextColorId = R.color.level1_text_color
                bmiEmojiId = R.drawable.result_bmi_under
                resultPanelTargetDegree = -225F
            }

            in 18.5..24.9 -> {
                bmiInfoId = R.string.result_level_normal
                bmiInfoTextColorId = R.color.level2_text_color
                bmiEmojiId = R.drawable.result_bmi_normal
                resultPanelTargetDegree = -180F
            }

            in 25.0..29.9 -> {
                bmiInfoId = R.string.result_level_over
                bmiInfoTextColorId = R.color.level3_text_color
                bmiEmojiId = R.drawable.result_bmi_over
                resultPanelTargetDegree = -135F
            }

            in 30.0..39.9 -> {
                bmiInfoId = R.string.result_level_obese
                bmiInfoTextColorId = R.color.level4_text_color
                bmiEmojiId = R.drawable.result_bmi_obese
                resultPanelTargetDegree = -90F
            }

            in 40.0F .. Float.MAX_VALUE -> {
                bmiInfoId = R.string.result_level_extreme
                bmiInfoTextColorId = R.color.level5_text_color
                bmiEmojiId = R.drawable.result_bmi_extreme
                resultPanelTargetDegree = -45F
            }

            else -> { // not reached
                bmiInfoId = R.string.result_level_wrong
                bmiInfoTextColorId = R.color.level5_text_color
                bmiEmojiId = R.drawable.result_bmi_extreme
                resultPanelTargetDegree = 0.0F
            }
        }
    }

    fun getBmiValue() = if (isBmiValid(bmi)) bmi.toString() else ""

    private fun isBmiValid(bmi: Float): Boolean {
        Log.d("ResultScreenStateHolder", "isNan: ${bmi.isNaN()}")
        return !((bmi.isNaN()) || (bmi < 12.0) || (bmi > 42.0))
    }
}