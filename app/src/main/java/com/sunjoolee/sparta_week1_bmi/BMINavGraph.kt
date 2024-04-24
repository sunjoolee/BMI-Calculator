package com.sunjoolee.sparta_week1_bmi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sunjoolee.sparta_week1_bmi.BMIDestinationArgs.HEIGHT_ARG
import com.sunjoolee.sparta_week1_bmi.BMIDestinationArgs.WEIGHT_ARG
import com.sunjoolee.sparta_week1_bmi.first.FirstScreen
import com.sunjoolee.sparta_week1_bmi.result.ResultScreen

@Composable
fun BMINavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = BMIDestinations.FIRST_ROUTE,
    navActions: BMINavigationActions = remember(navController) {
        BMINavigationActions(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(BMIDestinations.FIRST_ROUTE) {
            FirstScreen(
                navToResult = { height:Float, weight:Float ->
                    navActions.navigateToResult(height, weight)
                }
            )
        }
        composable(
            BMIDestinations.RESULT_ROUTE,
            arguments = listOf(
                navArgument(HEIGHT_ARG) {
                    type = NavType.FloatType; defaultValue = 0.0
                },
                navArgument(WEIGHT_ARG) {
                    type = NavType.FloatType; defaultValue = 0.0
                },
            )
        ) {
            ResultScreen(
                navToFirst = { navActions.navigateToFirst() },
                height = it.arguments?.getFloat(HEIGHT_ARG)?:0.0F,
                weight = it.arguments?.getFloat(WEIGHT_ARG)?:0.0F
            )
        }
    }
}