package com.example.tictactoe.ui

import androidx.compose.foundation.Canvas
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
fun DiagonalLineTopRightToBottomLeft() {

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
fun DiagonalLineTopLeftToBottomRight() {

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


@Composable
fun WinHorizontalLine1() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(y = size.height * 1 / 6, x = 0f),
            end = Offset(y = size.height * 1 / 6, x = size.width)
        )
    }
}


@Composable
fun WinHorizontalLine2() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(y = size.height * 3 / 6, x = 0f),
            end = Offset(y = size.height * 3 / 6, x = size.width)
        )
    }
}


@Composable
fun WinHorizontalLine3() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(y = size.height * 5 / 6, x = 0f),
            end = Offset(y = size.height * 5 / 6, x = size.width)
        )
    }
}

@Composable
fun WinVerticalLine1() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(y = 0f, x = size.width * 1 / 6),
            end = Offset(y = size.height, x = size.width * 1 / 6)
        )
    }
}

@Composable
fun WinVerticalLine2() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(y = 0f, x = size.width * 3 / 6),
            end = Offset(y = size.height, x = size.width * 3 / 6)
        )
    }
}

@Composable
fun WinVerticalLine3() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(y = 0f, x = size.width * 5 / 6),
            end = Offset(y = size.height, x = size.width * 5 / 6)
        )
    }
}

@Composable
fun WinDiagonalLine1() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(y = 0f, x = size.width),
            end = Offset(y = size.height, x = 0f)
        )
    }
}


@Composable
fun WinDiagonalLine2() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(y = 0f, x = 0f),
            end = Offset(y = size.width, x = size.height)
        )
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
    DiagonalLineTopLeftToBottomRight()
}