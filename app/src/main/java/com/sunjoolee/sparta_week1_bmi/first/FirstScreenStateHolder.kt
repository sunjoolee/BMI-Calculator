package com.sunjoolee.sparta_week1_bmi.first

import android.util.Log
import androidx.compose.runtime.mutableStateOf

class FirstScreenStateHolder{
    val heightState = mutableStateOf("")
    val weightState = mutableStateOf("")

    fun setHeightState(heightInput: String){
        heightState.value = heightInput
    }
    fun setWeightState(weightInput:String){
        weightState.value = weightInput
    }

    fun isInputValid(input:String):Boolean =
        (input.isEmpty() || input.matches(Regex("""^[1-9]+[0-9]*[.]?[0-9]*$""")))

    fun isCalculateButtonEnabled(): Boolean {
        Log.d("FirstScreenStateHolder", "isCalculateButtonEnabled) called" )
        return (heightState.value.isNotBlank() && weightState.value.isNotBlank())
    }
}