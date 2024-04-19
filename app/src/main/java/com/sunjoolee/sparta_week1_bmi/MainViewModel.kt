package com.sunjoolee.sparta_week1_bmi

import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
//    private val _showResultScreen = MutableLiveData<Boolean>(false)
//    val showResultScreen: LiveData<Boolean> get() = _showResultScreen

    private val _height = MutableLiveData<Double>()
    val height: LiveData<Double> get() = _height
    private val _weight = MutableLiveData<Double>()
    val weight: LiveData<Double> get() = _weight

    fun setHeight(heightInput: String){
        if(heightInput.isNotBlank())
            _height.value = heightInput.toDouble()
    }
    fun setWeight(weightInput:String){
        if(weightInput.isNotBlank())
            _weight.value = weightInput.toDouble()
    }


//    fun showResultScreen(){
//        _showResultScreen.value = true
//    }

//    fun closeResultScreen(){
//        _showResultScreen.value = false
//    }
}