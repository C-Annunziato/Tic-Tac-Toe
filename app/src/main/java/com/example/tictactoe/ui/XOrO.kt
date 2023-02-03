package com.example.tictactoe.ui

import android.R.attr.endX
import android.R.attr.endY
import android.R.attr.startX
import android.R.attr.startY
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun XX(modifier: Modifier = Modifier) {
    Canvas(
        modifier = Modifier
            .size(60.dp)
            .padding(18.dp)
            .padding(top = 8.dp)

    ) {
        //top left to bottom right
        drawLine(
            color = Color(96, 164, 224, 255),
            strokeWidth = 12f,
            cap = StrokeCap.Butt,
            start = Offset(x = 10f, y = -30f),
            end = Offset(x = size.width, y = size.height + 25),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(17f, 17f))
        )

        drawLine(
            color = Color(96, 164, 224, 255),
            strokeWidth = 12f,
            cap = StrokeCap.Butt,
            start = Offset(x = 20f, y = -30f),
            end = Offset(x = size.width + 10, y = size.height + 25),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(17f, 17f))
        )
        //top right to bottom left
        drawLine(
            color = Color(96, 164, 224, 255),
            strokeWidth = 12f,
            cap = StrokeCap.Butt,
            start = Offset(x = size.width, y = -45f),
            end = Offset(x = 5f, y = size.height + 10),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(16f, 16f))
        )

        drawLine(
            color = Color(96, 164, 224, 255),
            strokeWidth = 12f,
            cap = StrokeCap.Butt,
            start = Offset(x = size.width + 10, y = -45f),
            end = Offset(x = 15f, y = size.height + 10),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(16f, 16f))
        )
    }
}


@Composable
fun OO(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {

        val canvasWidth = size.width
        val canvasHeight = size.height

        drawCircle(
            radius = 50f,
            center = Offset(
                x = canvasWidth / 2, y = canvasHeight / 2
            ),
            color = Color.Black,
            style = Stroke(width = 11f),

        )
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun Prev() {
    XX()
}