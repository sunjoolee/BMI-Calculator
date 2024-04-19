package com.sunjoolee.sparta_week1_bmi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BMIApp (mainViewModel:MainViewModel){
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "main_screen"
    ){
        composable("main_screen"){
            MainScreen(navController = navController, mainViewModel = mainViewModel)
        }
        composable("result_screen"){
            ResultScreen(navController = navController, mainViewModel = mainViewModel)
        }
    }

//    val showResultScreen = rememberSaveable {
//        mutableStateOf(mainViewModel.showResultScreen.value!!)
//    }
//    if(showResultScreen.value) ResultScreen(mainViewModel = mainViewModel)
//    else MainScreen(mainViewModel = mainViewModel)
}