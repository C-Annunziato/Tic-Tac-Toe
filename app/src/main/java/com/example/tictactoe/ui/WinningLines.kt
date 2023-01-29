package com.example.tictactoe.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun HorizontalLine(modifier: Modifier = Modifier) {

    Canvas(modifier = modifier.fillMaxSize()) {

        val canvasWidth = size.width
        val canvasHeight = size.height

        drawLine(
            color = Color.Black,
            start = Offset(x = canvasWidth, y = 0f),
            end = Offset(x = 0f, y = canvasHeight),
            strokeWidth = 4f
        )

    }

}