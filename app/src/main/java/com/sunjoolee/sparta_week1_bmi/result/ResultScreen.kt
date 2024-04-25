package com.sunjoolee.sparta_week1_bmi.result

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
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
    height: Float = 0.0F,
    weight: Float = 0.0F
) {
    val resultScreenStateHolder = remember {
        ResultScreenStateHolder(height, weight)
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
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        with(resultScreenStateHolder) {
            PulsingImage(getBmiInfoImageId())

            Text(text = getBmiValue())
            RotatingText(
                text = LocalContext.current.getString(getBmiInfoId()),
                textColor = LocalContext.current.getColor(getBmiInfoTextColorId())
            )

            ResultPanel(targetDegree = getBmiInfoArrowDegree())
        }
    }
}

enum class RotationState {
    BEFORE,
    AFTER
}

@Composable
fun RotatingText(
    text: String,
    textColor: Int
) {
    val textRotationState = remember { MutableTransitionState(RotationState.BEFORE) }
    LaunchedEffect(Unit) {
        textRotationState.targetState = RotationState.AFTER
    }

    val rotationTransition = updateTransition(
        transitionState = textRotationState,
        label = "rotate_text"
    )
    val rotation = rotationTransition.animateFloat(
        label = "rotate_text",
        transitionSpec = {
            tween(3000)
        },
        targetValueByState = { state ->
            when (state) {
                RotationState.BEFORE -> 360F
                RotationState.AFTER -> 0F
            }
        }
    )

    Text(
        text = text,
        color = Color(textColor),
        modifier = Modifier
            .graphicsLayer(
                rotationX = rotation.value,
                rotationY = rotation.value
            )
            .clickable {
                with(textRotationState) {
                    targetState =
                        if (currentState == RotationState.AFTER) RotationState.BEFORE
                        else RotationState.AFTER
                }
            }
    )
}

@Composable
fun PulsingImage(
    @DrawableRes imageId: Int
) {
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
) {
    Button(
        modifier = modifier.padding(top = 20.dp),
        onClick = onClick
    ) {
        Text(text = LocalContext.current.getString(R.string.result_btn_back))
    }
}


enum class ResultPanelState {
    BEFORE,
    AFTER
}

@Composable
fun ResultPanel(
    modifier: Modifier = Modifier,
    targetDegree: Float
) {
    val resultPanelState = remember { MutableTransitionState(ResultPanelState.BEFORE) }
    LaunchedEffect(Unit) {
        resultPanelState.targetState = ResultPanelState.AFTER
    }

    val panelTransition = updateTransition(
        transitionState = resultPanelState, label = "rotate_panel"
    )
    val panelDegree = panelTransition.animateFloat(
        label = "rotate_panel",
        transitionSpec = { tween(1500) },
        targetValueByState = { state ->
            when (state) {
                ResultPanelState.BEFORE -> -225F
                ResultPanelState.AFTER -> targetDegree
            }
        }
    )

    Box(
        modifier = modifier
            .width(150.dp)
            .height(150.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.result_panel),
            contentDescription = "",
            modifier = modifier.align(Alignment.BottomCenter)
        )
        Image(
            painter = painterResource(id = R.drawable.result_panel_arrow),
            contentDescription = "",
            modifier = modifier
                .align(Alignment.BottomCenter)
                .padding(start = 32.dp)
                .scale(0.5F)
                .graphicsLayer(
                    rotationZ = panelDegree.value,
                    transformOrigin = TransformOrigin(
                        pivotFractionX = 0.26F,
                        pivotFractionY = 0.44F
                    )
                )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    val resultScreenStateHolder = remember {
        ResultScreenStateHolder(170.0F, 60.0F)
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


