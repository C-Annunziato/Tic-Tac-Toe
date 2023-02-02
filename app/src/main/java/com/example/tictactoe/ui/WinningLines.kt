package com.example.tictactoe.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalLineTopRightToBottomLeft() {

    Canvas(modifier = Modifier.size(300.dp).padding(10.dp)) {

        val canvasWidth = size.width
        val canvasHeight = size.height

        drawLine(
            cap = StrokeCap.Butt,
            color = Color.Black,
            start = Offset(x = canvasWidth, y = 0f),
            end = Offset(x = 0f, y = canvasHeight),
            strokeWidth = 8f,)
    }
}

@Composable
fun HorizontalLineTopLeftToBottomRight() {

    Canvas(modifier = Modifier.size(300.dp).padding(10.dp)) {

        val canvasWidth = size.width
        val canvasHeight = size.height

        drawLine(
            cap = StrokeCap.Butt,
            color = Color.Black,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = canvasWidth,y = canvasHeight),
            strokeWidth = 8f,)
    }
}
@Preview(
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
    heightDp = 300,
    widthDp = 300
)
@Composable
fun Preview(){
    HorizontalLineTopLeftToBottomRight()
}