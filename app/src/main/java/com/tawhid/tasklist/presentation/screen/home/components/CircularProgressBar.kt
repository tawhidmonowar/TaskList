package com.tawhid.tasklist.presentation.screen.home.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier,
    progress: Float,
    size: Dp = 100.dp,
    strokeWidth: Dp = 10.dp,
    progressColor: Color,
    backgroundColor: Color,
    textColor: Color
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 1000)
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(size)
    ) {
        Canvas(modifier = Modifier.size(size)) {
            drawCircle(
                color = backgroundColor,
                radius = (size / 2).toPx(),
                style = Stroke(width = strokeWidth.toPx())
            )
            drawArc(
                color = progressColor,
                startAngle = 270f,
                sweepAngle = progress * 360f,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
        Text(
            text = "${(animatedProgress * 100).toInt()}%",
            color = textColor
        )
    }
}