package com.sunjoolee.sparta_week1_bmi

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sunjoolee.sparta_week1_bmi.first.FirstScreen
import com.sunjoolee.sparta_week1_bmi.result.ResultScreen

@Composable
fun BMIApp (mainViewModel:MainViewModel){
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "first_screen"
    ){
        composable("first_screen"){
            FirstScreen(navController = navController, mainViewModel = mainViewModel)
        }
        composable("result_screen"){
            ResultScreen(navController = navController, mainViewModel = mainViewModel)
        }
    }
}