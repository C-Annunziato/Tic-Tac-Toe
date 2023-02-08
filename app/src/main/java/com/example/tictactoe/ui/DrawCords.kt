package com.example.tictactoe.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope


fun DrawScope.drawStraightConnectorLines(
    color: Color = Color.Black,
    stroke: Float = 10f,
    xStart: Float,
    yStart: Float,
    xEnd: Float,
    yEnd: Float,
    adjXStart: Float = 0f,
    adjYStart: Float = 0f,
    adjXEnd: Float = 0f,
    adjYEnd: Float = 0f,
) {
    val canvasWidth = size.width
    val canvasHeight = size.height
    drawLine(
        color = color, strokeWidth = stroke, start = Offset(
            x = canvasWidth * xStart + adjXStart, y = canvasHeight * yStart + adjYStart
        ), end = Offset(x = canvasWidth * xEnd + adjXEnd, y = canvasHeight * yEnd + adjYEnd)
    )
}
