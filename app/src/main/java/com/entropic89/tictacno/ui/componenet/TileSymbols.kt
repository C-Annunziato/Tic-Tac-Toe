package com.entropic89.tictacno.ui.componenet

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DisabledByDefault
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.lang.Math.cos
import java.lang.Math.sin


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
    numberOfSquares: Int = 38,
) {
    Canvas(
        modifier = Modifier
            .size(70.dp)
            .padding(end = 6.dp)
    ) {
        val squareSize = 25f

        val radius = (size.width * .5f - 20f) - (.25f * (size.width * .5f - 20f))
        val lineDegree = (360f * 2) / numberOfSquares

        for (squareNumber in 0..numberOfSquares) {

            val angleInDegrees = lineDegree * (squareNumber - 90f)
            val angleRad = Math.toRadians(angleInDegrees.toDouble()).toFloat()

            drawRect(
                color = Color(69, 157, 172, 255), topLeft = Offset(
                    x = (radius) * kotlin.math.cos(angleRad) + size.center.x,
                    y = (radius) * kotlin.math.sin(angleRad) + size.center.y - 20f
                ), size = Size(squareSize, squareSize)
            )

            drawRect(
                color = Color(134, 105, 213, 255), topLeft = Offset(
                    x = (radius) * kotlin.math.cos(angleRad) + size.center.x,
                    y = (radius) * kotlin.math.sin(angleRad) + size.center.y
                ), size = Size(squareSize, squareSize)
            )


        }
    }
}


@Composable
fun Star() {

    Canvas(
        modifier = Modifier
            .size(50.dp)
            .padding(8.dp)
            .offset((-4).dp, (-5).dp)
    ) {
        val starRadius = size.minDimension / 2f
        val outerRadius = starRadius
        val innerRadius = starRadius / 2f
        val numPoints = 5

        val points = mutableListOf<Offset>()
        for (i in 0 until numPoints * 2) {
            val radius = if (i % 2 == 0) outerRadius else innerRadius
            val x = size.width / 2 + radius * cos(i * Math.PI / numPoints)
            val y = size.height / 2 + radius * sin(i * Math.PI / numPoints)
            points.add(Offset(x.toFloat(), y.toFloat()))
        }
        val square = Path().apply {
            lineTo(17f, 0f)
            lineTo(17f, 17f)
            lineTo(0f, 17f)
            close()
        }


        drawPath(
            path = Path().apply {
                moveTo(points.first().x, points.first().y)
                points.forEachIndexed { index, point ->
                    if (index == 0) {
                        return@forEachIndexed
                    }
                    lineTo(point.x, point.y + 15)
                }
                close()
            }, color = Color(189, 45, 153, 255), style = Stroke(
                pathEffect = PathEffect.stampedPathEffect(
                    shape = square,
                    style = StampedPathEffectStyle.Translate,
                    phase = 0f,
                    advance = 15f
                )
            )
        )

        drawPath(
            path = Path().apply {
                moveTo(points.first().x, points.first().y)
                points.forEachIndexed { index, point ->
                    if (index == 0) {
                        return@forEachIndexed
                    }
                    lineTo(point.x, point.y)
                }
                close()
            }, color = Color(224, 199, 77, 255), style = Stroke(
                pathEffect = PathEffect.stampedPathEffect(
                    shape = square,
                    style = StampedPathEffectStyle.Translate,
                    phase = 0f,
                    advance = 15f
                )
            )
        )
    }
}

@Composable
fun Destroyed() {
    Icon(
        imageVector = Icons.Filled.ThumbDown,
        contentDescription = "destroyed icon",
        modifier = Modifier.size(20.dp).padding(20.dp)
    )
}

@Composable
fun Locked(){
    Icon(
        imageVector = Icons.Filled.DisabledByDefault,
        contentDescription = "disabled icon",
        modifier = Modifier.size(20.dp).padding(20.dp)
    )
}


@Preview
@Composable
fun Prev() {
    Destroyed()
}


