package com.sunjoolee.sparta_week1_bmi.ui.first

import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.googlefonts.isAvailableOnDevice
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sunjoolee.sparta_week1_bmi.R
import com.sunjoolee.sparta_week1_bmi.ui.theme.AppTheme
import com.sunjoolee.sparta_week1_bmi.ui.theme.AppTypography
import com.sunjoolee.sparta_week1_bmi.ui.theme.fontFamily
import com.sunjoolee.sparta_week1_bmi.ui.theme.provider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun FirstScreen(
    modifier: Modifier = Modifier,
    navToResult: (Float, Float) -> Unit
) {
    val firstScreenStateHolder = remember {
        FirstScreenStateHolder()
    }

    // test if google font provider is available
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        if (provider.isAvailableOnDevice(context)) {
            Log.d("FirstScreen", "Google Font Provider Success!")
        }
    }

    // pre-load downloadable fonts
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            fontFamilyResolver.preload(fontFamily)
        }
    }

    AppTheme {
        Surface(
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainTitle()
                MainContent(
                    checkIfInputValid = firstScreenStateHolder::isInputValid,
                    onHeightChange = firstScreenStateHolder::setHeightState,
                    onWeightChange = firstScreenStateHolder::setWeightState
                )
                CalculateButton(
                    checkIfEnabled = firstScreenStateHolder::isCalculateButtonEnabled,
                    onClick = {
                        navToResult(
                            firstScreenStateHolder.heightState.value.toFloat(),
                            firstScreenStateHolder.weightState.value.toFloat()
                        )
                    }
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
        modifier = modifier
            .padding(top = 80.dp)
            .paddingFromBaseline(bottom = 20.dp),
        text = LocalContext.current.getString(R.string.main_tv_title),
        style = AppTypography.headlineLarge
    )
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    checkIfInputValid: (String) -> Boolean,
    onHeightChange: (String) -> Unit,
    onWeightChange: (String) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeightInput(modifier, checkIfInputValid, onHeightChange)
        Spacer(modifier = Modifier.padding(8.dp))
        WeightInput(modifier, checkIfInputValid, onWeightChange)
    }
}

@Composable
fun HeightInput(
    modifier: Modifier = Modifier,
    checkIfInputValid: (String) -> Boolean,
    onHeightChange: (String) -> Unit
) {
    MeasureInput(
        modifier = modifier,
        measureTitleId = R.string.main_tv_height,
        measureDigitId = R.string.main_tv_height_digit,
        checkIfInputValid = checkIfInputValid,
        onValueChange = onHeightChange
    )
}

@Composable
fun WeightInput(
    modifier: Modifier = Modifier,
    checkIfInputValid: (String) -> Boolean,
    onWeightChange: (String) -> Unit
) {
    MeasureInput(
        modifier = modifier,
        measureTitleId = R.string.main_tv_weight,
        measureDigitId = R.string.main_tv_weight_digit,
        checkIfInputValid = checkIfInputValid,
        onValueChange = onWeightChange
    )
}

@Composable
fun MeasureInput(
    modifier: Modifier = Modifier,
    @StringRes measureTitleId: Int,
    @StringRes measureDigitId: Int,
    checkIfInputValid: (String) -> Boolean,
    onValueChange: (String) -> Unit
) {
    val context = LocalContext.current

    val inputState = rememberSaveable { mutableStateOf("") }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = context.getString(measureTitleId),
            style = AppTypography.headlineSmall,
            modifier = modifier
                .padding(end = 10.dp)
                .width(50.dp)
        )
        TextField(
            modifier = modifier
                .width(160.dp)
                .height(60.dp),
            value = inputState.value,
            onValueChange = {
                if (checkIfInputValid(it)) {
                    inputState.value = it
                    onValueChange.invoke(it)
                } else {
                    Toast.makeText(context, R.string.main_tf_not_valid, Toast.LENGTH_SHORT).show()
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
        Text(
            text = context.getString(measureDigitId),
            style = AppTypography.headlineSmall,
            modifier = modifier
                .padding(start = 10.dp)
                .width(50.dp)
        )
    }
}

@Composable
fun CalculateButton(
    modifier: Modifier = Modifier,
    checkIfEnabled: () -> Boolean,
    onClick: () -> Unit
) {
    val isEnabled = remember {
        derivedStateOf { checkIfEnabled() }
    }

    Button(
        modifier = modifier.padding(top = 20.dp),
        enabled = isEnabled.value,
        onClick = onClick
    ) {
        Text(
            text = LocalContext.current.getString(R.string.main_btn_calculate),
            fontFamily = fontFamily
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(
    modifier: Modifier = Modifier
) {
    AppTheme {
        Surface(
            tonalElevation = 2.dp,
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainTitle()
                MainContent(
                    checkIfInputValid = { true },
                    onHeightChange = {},
                    onWeightChange = {}
                )
                CalculateButton(
                    checkIfEnabled = { true },
                    onClick = {}
                )
            }
        }
    }
}