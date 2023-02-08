package com.example.tictactoe.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke


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

fun DrawScope.drawBezierCables(
    color: Color = Color.Black,
    stroke: Float = 10f,
    startx: Float,
    starty: Float,
    endx: Float,
    endy: Float,
    firstControlPointx: Float,
    firstControlPointy: Float,
    secondControlPointx: Float,
    secondControlPointy: Float,
    adjFirstControlPointx: Float = 0f,
    adjFirstControlPointy: Float = 0f,
    adjSecondControlPointx: Float = 0f,
    adjSecondControlPointy: Float = 0f,
){

    val canvasWidth = size.width
    val canvasHeight = size.height

    val moveToX = canvasWidth * startx
    val moveToY = canvasHeight * starty

    val x3 = canvasWidth * endx
    val y3 = canvasHeight * endy

    val x1 = canvasWidth * firstControlPointx + adjFirstControlPointx
    val y1 = canvasHeight * firstControlPointy + adjFirstControlPointy

    val x2 = canvasWidth * secondControlPointx + adjSecondControlPointx
    val y2 = canvasHeight * secondControlPointy + adjSecondControlPointy


    val bezierCurve = Path().apply {
        reset()
        moveTo(
            x = moveToX,
            y = moveToY
        )
        cubicTo(
            x1 = x1,
            y1 = y1,
            x2 = x2,
            y2 = y2,
            x3 = x3,
            y3 = y3
        )
    }

    drawPath(
        bezierCurve, color = color, style = Stroke(
            width = stroke,
        )
    )

}
