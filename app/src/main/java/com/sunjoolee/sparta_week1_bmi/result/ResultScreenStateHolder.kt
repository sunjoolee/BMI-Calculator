package com.sunjoolee.sparta_week1_bmi.result

import android.util.Log
import kotlin.math.round

class ResultScreenStateHolder(height: Float, weight: Float) {
    private val bmi: Float
    private val bmiResult: BMIResult

    init {
        Log.d("ResultScreenStateHolder", "height: $height, weight: $weight")
        bmi = (weight / (height * height) * 10000F).run { round(this * 10.0F) / 10.0F }
        Log.d("ResultScreenStateHolder", "bmi: $bmi")

        bmiResult = when (bmi) {
            in 0.0..18.4 -> BMIResult.UNDER
            in 18.5..24.9 -> BMIResult.NORMAL
            in 25.0..29.9 -> BMIResult.OVER
            in 30.0..39.9 -> BMIResult.OBESE
            in 40.0F .. Float.MAX_VALUE -> BMIResult.EXTREME
            else -> BMIResult.UNKNOWN // not reached
        }
    }

    fun getBmiValue() = if (isBmiValid(bmi)) bmi.toString() else ""

    private fun isBmiValid(bmi: Float): Boolean {
        Log.d("ResultScreenStateHolder", "isNan: ${bmi.isNaN()}")
        return !((bmi.isNaN()) || (bmi < 12.0) || (bmi > 42.0))
    }

    fun getBmiInfoId() = bmiResult.bmiInfoId
    fun getBmiInfoTextColorId() = bmiResult.bmiInfoTextColorId
    fun getBmiInfoImageId() = bmiResult.bmiInfoImageId
    fun getBmiInfoArrowDegree() = bmiResult.bmiInfoArrowDegree
}