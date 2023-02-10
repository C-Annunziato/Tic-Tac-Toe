package com.example.tictactoe.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.dp


@Composable
fun DrawCross(modifier: Modifier = Modifier) {

    val square = Path().apply {
        lineTo(20f, 0f)
        lineTo(20f, 20f)
        lineTo(0f, 20f)
        close()
    }
    Canvas(
        modifier = Modifier
            .size(60.dp)
            .padding(20.dp)
            .padding(end = 10.dp)

    ) {
        //top left to bottom right

        drawLine(
            color = Color(59, 93, 194, 255),
            strokeWidth = 12f,
            cap = StrokeCap.Butt,
            start = Offset(x = 10f, y = -25f),
            end = Offset(x = size.width, y = size.height + 25),
            pathEffect = PathEffect.stampedPathEffect(
                shape = square, style = StampedPathEffectStyle.Translate, phase = 0f, advance = 20f
            )
        )

        //top right to bottom left
        drawLine(
            color = Color(59, 93, 194, 255),
            strokeWidth = 12f,
            cap = StrokeCap.Butt,
            start = Offset(x = size.width, y = -35f),
            end = Offset(x = 5f, y = size.height + 10),
            pathEffect = PathEffect.stampedPathEffect(
                shape = square, style = StampedPathEffectStyle.Translate, phase = 0f, advance = 20f
            )
        )
//        FF4F5253
        drawLine(
            color = Color(142, 194, 59, 255),
            strokeWidth = 12f,
            cap = StrokeCap.Butt,
            start = Offset(x = 20f, y = -25f),
            end = Offset(x = size.width + 10, y = size.height + 25),
            pathEffect = PathEffect.stampedPathEffect(
                shape = square, style = StampedPathEffectStyle.Translate, phase = 0f, advance = 20f
            )
        )

        drawLine(
            color = Color(142, 194, 59, 255),
            strokeWidth = 12f,
            cap = StrokeCap.Butt,
            start = Offset(x = size.width + 10, y = -35f),
            end = Offset(x = 15f, y = size.height + 10),
            pathEffect = PathEffect.stampedPathEffect(
                shape = square, style = StampedPathEffectStyle.Translate, phase = 0f, advance = 20f
            )
        )

    }
}

@Composable
fun CircleOfSquares(
    numberOfSquares: Int = 32,
) {
    Canvas(
        modifier = Modifier
            .size(70.dp)
            .padding(end = 6.dp)
    ) {

        val radius = (size.width * .5f - 20f) - (.25f * (size.width * .5f - 20f))
        val lineDegree = (360f * 2) / numberOfSquares

        for (squareNumber in 0..numberOfSquares) {

            val angleInDegrees = lineDegree * (squareNumber - 90f)
            val angleRad = Math.toRadians(angleInDegrees.toDouble()).toFloat()

            drawRect(
                color = Color(69, 157, 172, 255), topLeft = Offset(
                    x = (radius) * kotlin.math.cos(angleRad) + size.center.x,
                    y = (radius) * kotlin.math.sin(angleRad) + size.center.y - 20f
                ), size = Size(22f, 22f)
            )

            drawRect(
                color = Color(134, 105, 213, 255), topLeft = Offset(
                    x = (radius) * kotlin.math.cos(angleRad) + size.center.x,
                    y = (radius) * kotlin.math.sin(angleRad) + size.center.y
                ), size = Size(22f, 22f)
            )


        }
    }
}


