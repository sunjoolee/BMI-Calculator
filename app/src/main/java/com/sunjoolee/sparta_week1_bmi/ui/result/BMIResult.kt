package com.sunjoolee.sparta_week1_bmi.ui.result

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.sunjoolee.sparta_week1_bmi.R

enum class BMIResult(
    @StringRes val bmiInfoId: Int,
    @ColorRes val bmiInfoTextColorId: Int,
    @DrawableRes val bmiInfoImageId: Int,
    val bmiInfoArrowDegree: Float
) {
    UNDER(
        bmiInfoId = R.string.result_level_under,
        bmiInfoTextColorId = R.color.level1_text_color,
        bmiInfoImageId = R.drawable.result_bmi_under,
        bmiInfoArrowDegree = -225F
    ),
    NORMAL(
        bmiInfoId = R.string.result_level_normal,
        bmiInfoTextColorId = R.color.level2_text_color,
        bmiInfoImageId = R.drawable.result_bmi_normal,
        bmiInfoArrowDegree = -180F,
    ),
    OVER(
        bmiInfoId = R.string.result_level_over,
        bmiInfoTextColorId = R.color.level3_text_color,
        bmiInfoImageId = R.drawable.result_bmi_over,
        bmiInfoArrowDegree = -135F
    ),
    OBESE(
        bmiInfoId = R.string.result_level_obese,
        bmiInfoTextColorId = R.color.level4_text_color,
        bmiInfoImageId = R.drawable.result_bmi_obese,
        bmiInfoArrowDegree = -90F
    ),
    EXTREME(
        bmiInfoId = R.string.result_level_extreme,
        bmiInfoTextColorId = R.color.level5_text_color,
        bmiInfoImageId = R.drawable.result_bmi_extreme,
        bmiInfoArrowDegree = -45F
    ),
    UNKNOWN(
        bmiInfoId = R.string.result_level_wrong,
        bmiInfoTextColorId = R.color.level5_text_color,
        bmiInfoImageId = R.drawable.result_bmi_extreme,
        bmiInfoArrowDegree = 0.0F
    )
}