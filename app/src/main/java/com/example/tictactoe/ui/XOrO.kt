package com.example.tictactoe.ui

import androidx.annotation.ColorRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun XX(modifier: Modifier = Modifier) {
    Canvas(
        modifier = Modifier
            .size(65.dp)
            .padding(18.dp)
            .padding(top = 6.dp)

    ) {
        //top left to bottom right
        drawLine(
            color = Color.Black,
            strokeWidth = 11f,
            cap = StrokeCap.Butt,
            start = Offset(x = 10f, y = -30f),
            end = Offset(x = size.width , y = size.height + 25)
        )

        drawLine(
            color = Color.Black,
            strokeWidth = 11f,
            cap = StrokeCap.Butt,
            start = Offset(x = size.width, y = -45f),
            end = Offset(x = 0f, y = size.height + 10)
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
fun Prev(){
    XX()

}
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun Prev2(){
Box(
    Modifier
        .aspectRatio(1f)
        .size(60.dp),
    contentAlignment = Alignment.Center
) {
    XX1()
}
}