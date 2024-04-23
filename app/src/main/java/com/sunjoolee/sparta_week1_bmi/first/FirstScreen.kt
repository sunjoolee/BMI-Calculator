package com.sunjoolee.sparta_week1_bmi.first

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sunjoolee.sparta_week1_bmi.MainViewModel
import com.sunjoolee.sparta_week1_bmi.R

@Composable
fun FirstScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    mainViewModel: MainViewModel
) {
    MaterialTheme {
        Surface(
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainTitle()
                MainContent(
                    onHeightChange = mainViewModel::setHeight,
                    onWeightChange = mainViewModel::setWeight
                )
                CalculateButton(
                    onClick = {navController.navigate("result_screen")}
                )
            }
        }
    }
}

@Composable
fun MainTitle(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(top = 80.dp).paddingFromBaseline(bottom = 20.dp),
        text = LocalContext.current.getString(R.string.main_tv_title),
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.headlineLarge
    )
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    onHeightChange: (String) -> Unit,
    onWeightChange: (String) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeightInput(modifier, onHeightChange)
        WeightInput(modifier, onWeightChange)
    }
}

@Composable
fun HeightInput(
    modifier: Modifier = Modifier,
    onHeightChange: (String) -> Unit
){
    MeasureInput(
        modifier = modifier,
        measureTitleId = R.string.main_tv_height,
        measureDigitId = R.string.main_tv_height_digit,
        onValueChange = onHeightChange
    )
}
@Composable
fun WeightInput(
    modifier: Modifier = Modifier,
    onWeightChange: (String) -> Unit
){
    MeasureInput(
        modifier = modifier,
        measureTitleId = R.string.main_tv_weight,
        measureDigitId = R.string.main_tv_weight_digit,
        onValueChange = onWeightChange
    )
}
@Composable
fun MeasureInput(
    modifier: Modifier = Modifier,
    @StringRes measureTitleId: Int,
    @StringRes measureDigitId: Int,
    onValueChange: (String) -> Unit
){
    val context = LocalContext.current

    val inputState = rememberSaveable{ mutableStateOf("") }
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom) {
        Text(
            text = context.getString(measureTitleId),
            style = MaterialTheme.typography.bodyLarge
        )
        TextField(
            modifier = modifier.width(200.dp).height(120.dp),
            value = inputState.value,
            onValueChange = {
                inputState.value = it
                onValueChange.invoke(it)
            }
        )
        Text(
            text = context.getString(measureDigitId),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun CalculateButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.padding(top = 20.dp),
        onClick = onClick
    ) {
        Text(text = LocalContext.current.getString(R.string.main_btn_calculate))
    }
}
@Composable
fun StatelessCalculateButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(text = LocalContext.current.getString(R.string.main_btn_calculate))
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(
    modifier: Modifier = Modifier
) {
    MaterialTheme {
        Surface(
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainTitle()
                MainContent(
                    onHeightChange = {},
                    onWeightChange = {}
                )
                CalculateButton(
                    onClick = {}
                )
            }
        }
    }
}