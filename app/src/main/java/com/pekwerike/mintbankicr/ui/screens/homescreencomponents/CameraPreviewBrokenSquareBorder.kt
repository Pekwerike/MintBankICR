package com.pekwerike.mintbankicr.ui.screens.homescreencomponents

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


@Composable
fun CameraPreviewBrokenSquareBorder(modifier: Modifier) {
    val context = LocalContext.current

    Canvas(modifier = modifier.padding(16.dp), onDraw = {
        val size = this.size
        val scaledDensity = context.resources.displayMetrics.scaledDensity
        drawPath(
            path = Path().apply {
                drawLine(
                    color = Color.White,
                    start = Offset(0f, 0f),
                    end = Offset(15f * scaledDensity, 0f),
                    strokeWidth = 6f,
                    pathEffect = PathEffect.cornerPathEffect(10f * scaledDensity),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color.White,
                    start = Offset(0f, 0f),
                    end = Offset(0f, 15f * scaledDensity),
                    strokeWidth = 6f,
                    pathEffect = PathEffect.cornerPathEffect(10f * scaledDensity),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color.White,
                    start = Offset(size.width, 0f),
                    end = Offset(size.width, 15f * scaledDensity),
                    strokeWidth = 6f,
                    pathEffect = PathEffect.cornerPathEffect(10f * scaledDensity),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color.White,
                    start = Offset(size.width, 0f),
                    end = Offset(size.width - (15f * scaledDensity), 0f),
                    strokeWidth = 6f,
                    pathEffect = PathEffect.cornerPathEffect(10f * scaledDensity),
                    cap = StrokeCap.Round
                )

                drawLine(
                    color = Color.White,
                    start = Offset(size.width, size.height),
                    end = Offset(size.width, size.height - 15f * scaledDensity),
                    strokeWidth = 6f,
                    pathEffect = PathEffect.cornerPathEffect(10f * scaledDensity),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color.White,
                    start = Offset(size.width, size.height),
                    end = Offset(size.width - (15f * scaledDensity), size.height),
                    strokeWidth = 6f,
                    pathEffect = PathEffect.cornerPathEffect(10f * scaledDensity),
                    cap = StrokeCap.Round
                )

                drawLine(
                    color = Color.White,
                    start = Offset(0f, size.height),
                    end = Offset(0f, size.height - 15f * scaledDensity),
                    strokeWidth = 6f,
                    pathEffect = PathEffect.cornerPathEffect(10f * scaledDensity),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color.White,
                    start = Offset(0f, size.height),
                    end = Offset(15f * scaledDensity, size.height),
                    strokeWidth = 6f,
                    pathEffect = PathEffect.cornerPathEffect(10f * scaledDensity),
                    cap = StrokeCap.Round
                )
            },
            color = Color.White, style = Stroke(
                width = 6f * scaledDensity,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round,
                pathEffect = PathEffect.cornerPathEffect(10 * scaledDensity)
            )
        )
    })
}
