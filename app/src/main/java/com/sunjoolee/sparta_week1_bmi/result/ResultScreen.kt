package com.sunjoolee.sparta_week1_bmi.result

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sunjoolee.sparta_week1_bmi.R


@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    navToFirst: () -> Unit,
    height:Float = 0.0F,
    weight:Float = 0.0F
) {
    val resultScreenStateHolder = remember{
        ResultScreenStateHolder(height,weight)
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
                    onClick = navToFirst
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
        modifier = modifier
            .padding(top = 80.dp)
            .paddingFromBaseline(bottom = 20.dp),
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
        with(resultScreenStateHolder.getBmiValue()){
            if(this.isNotBlank()) Text(text = this)
        }
        Text(text = LocalContext.current.getString(resultScreenStateHolder.bmiInfoId))
        PulsingImage(resultScreenStateHolder.bmiEmojiId)
    }
}

@Composable
fun PulsingImage(
    @DrawableRes imageId: Int
){
    val infiniteTransition = rememberInfiniteTransition(label = "pulse_image")
    val scale = infiniteTransition.animateFloat(
        label = "pulse_image",
        initialValue = 0.3F,
        targetValue = 0.6F,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )
    
    Image(
        painter = painterResource(id = imageId),
        contentDescription = "",
        modifier = Modifier
            .graphicsLayer(
                scaleX = scale.value,
                scaleY = scale.value
            )
            .clipToBounds()
    )
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
        ResultScreenStateHolder(170.0F,60.0F)
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


