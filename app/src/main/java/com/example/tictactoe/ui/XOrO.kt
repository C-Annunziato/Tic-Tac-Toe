package com.example.tictactoe.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp


@Composable
fun XX(modifier: Modifier = Modifier) {
    Divider(
        modifier = Modifier
            .width(60.dp)
            .rotate(60f),
        thickness = 4.dp,
        color = Color.Black,
        startIndent = 10.dp
    )
    Divider(
        modifier = Modifier
            .width(50.dp)
            .rotate(120f),
        thickness = 4.dp,
        color = Color.Black,

        )
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
            style = Stroke(width = 10f),
        )
    }
}