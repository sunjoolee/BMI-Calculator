package com.sunjoolee.sparta_week1_bmi

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.sunjoolee.sparta_week1_bmi.BMIDestinationArgs.HEIGHT_ARG
import com.sunjoolee.sparta_week1_bmi.BMIDestinationArgs.WEIGHT_ARG
import com.sunjoolee.sparta_week1_bmi.BMIScreens.FIRST_SCREEN
import com.sunjoolee.sparta_week1_bmi.BMIScreens.RESULT_SCREEN

private object BMIScreens{
    const val FIRST_SCREEN = "first_screen"
    const val RESULT_SCREEN = "result_screen"
}

object BMIDestinationArgs{
    const val HEIGHT_ARG = "height"
    const val WEIGHT_ARG = "weight"
}

object BMIDestinations{
    const val FIRST_ROUTE = FIRST_SCREEN
    const val RESULT_ROUTE = "$RESULT_SCREEN/{$HEIGHT_ARG}/{$WEIGHT_ARG}"
}

class BMINavigationActions(private val navController: NavHostController) {
    fun navigateToFirst(){
        navController.navigate(FIRST_SCREEN){
            popUpTo(navController.graph.findStartDestination().id){
                inclusive = true
            }
        }
    }

    fun navigateToResult(height:Float, weight:Float){
        navController.navigate("$RESULT_SCREEN/${height.toString()}/${weight.toString()}")
    }
}