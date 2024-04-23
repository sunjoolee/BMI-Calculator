package com.sunjoolee.sparta_week1_bmi.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sunjoolee.sparta_week1_bmi.MainViewModel
import com.sunjoolee.sparta_week1_bmi.R


@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    mainViewModel: MainViewModel
) {
    val resultScreenStateHolder = remember{
        ResultScreenStateHolder(
            mainViewModel.height.value?: 0.0,
            mainViewModel.weight.value?: 0.0
        )
    }
    MaterialTheme {
        Surface(
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ResultTitle()
                ResultContent(
                    modifier = modifier,
                    resultScreenStateHolder = resultScreenStateHolder
                )
                BackButton(
                    onClick = {navController.navigate("first_screen")}
                )
            }
        }
    }
}

@Composable
fun ResultTitle(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(top = 80.dp).paddingFromBaseline(bottom = 20.dp),
        text = LocalContext.current.getString(R.string.result_tv_title),
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.headlineLarge
    )
}

@Composable
fun ResultContent(
    modifier: Modifier = Modifier,
    resultScreenStateHolder: ResultScreenStateHolder
){
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(text = resultScreenStateHolder.getBmiValue())
        Text(text = LocalContext.current.getString(resultScreenStateHolder.bmiInfoId))
        Image(
            painter = painterResource(id = resultScreenStateHolder.bmiEmojiId),
            contentDescription = "",
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
                .clipToBounds()
        )
    }
}

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Button(
        modifier =  modifier.padding(top = 20.dp),
        onClick = onClick
    ) {
        Text(text = LocalContext.current.getString(R.string.result_btn_back))
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    val resultScreenStateHolder = remember{
        ResultScreenStateHolder(170.0,60.0)
    }
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                ResultTitle()
                ResultContent(
                    resultScreenStateHolder = resultScreenStateHolder
                )
                BackButton(
                    onClick = {}
                )
            }
        }
    }
}


