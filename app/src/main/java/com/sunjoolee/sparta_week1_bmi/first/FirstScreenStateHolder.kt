package com.sunjoolee.sparta_week1_bmi.first

class FirstScreenStateHolder{
    private var height:Float = 0.0F
    private var weight:Float = 0.0F

    fun getHeight() = height
    fun setHeight(heightInput: String){
        if(heightInput.isNotBlank())
            height= heightInput.toFloat()
    }
    fun getWeight() = weight
    fun setWeight(weightInput:String){
        if(weightInput.isNotBlank())
            weight = weightInput.toFloat()
    }
}