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

fun DrawScope.drawCableUI(){

    drawBezierCables(
        stroke = 12f,
        startx = 0.5f,
        starty = 0.565f,
        endx = 0.5f,
        endy = 0.4f,
        firstControlPointx = 0.5f,
        adjFirstControlPointx = - 100f,
        firstControlPointy = 0.49f,
        secondControlPointx = 0.5f,
        adjSecondControlPointx = 100f,
        secondControlPointy = 0.48f
    )

    drawBezierCables(
        startx = 0.178f,
        starty = 0.37f,
        endx = 0.178f,
        endy = 0.20f,
        firstControlPointx = 0.166f,
        adjFirstControlPointx = -20f,
        firstControlPointy = 0.33f,
        secondControlPointx = 0.166f,
        adjSecondControlPointx = -70f,
        secondControlPointy = 0.25f
    )

    drawBezierCables(
        startx = 0.178f,
        starty = 0.23f,
        endx = 0.178f,
        endy = 0.1f,
        firstControlPointx = 0.166f,
        adjFirstControlPointx = -20f,
        firstControlPointy = 0.26f,
        secondControlPointx = 0.166f,
        adjSecondControlPointx = - 30f,
        secondControlPointy = 0.18f
    )

    drawBezierCables(
        startx = 0.178f,
        starty = 0.28f,
        endx = 0.178f,
        endy = 0.15f,
        firstControlPointx = 0.166f,
        adjFirstControlPointx = -70f,
        firstControlPointy = 0.26f,
        secondControlPointx = 0.166f,
        adjSecondControlPointx = - 30f,
        secondControlPointy = 0.18f
    )

    drawBezierCables(
        startx = 0.83f,
        starty = 0.37f,
        endx = 0.83f,
        endy = 0.23f,
        firstControlPointx = 0.83f,
        adjFirstControlPointx = 50f,
        firstControlPointy = 0.33f,
        secondControlPointx = 0.83f,
        adjSecondControlPointx = + 80f,
        secondControlPointy = 0.25f

    )

    drawBezierCables(
        startx = 0.83f,
        starty = 0.26f,
        endx = 0.83f,
        endy = 0.12f,
        firstControlPointx = 0.83f,
        adjFirstControlPointx = 65f,
        firstControlPointy = 0.23f,
        secondControlPointx = 0.83f,
        adjSecondControlPointx = + 25f,
        secondControlPointy = 0.16f
    )

    drawBezierCables(
        startx = 0.80f,
        starty = 0.39f,
        endx = 0.83f,
        endy = 0.27f,
        firstControlPointx = 0.83f,
        adjFirstControlPointx = 90f,
        firstControlPointy = 0.40f,
        secondControlPointx = 0.83f,
        adjSecondControlPointx = + 5f,
        secondControlPointy = 0.26f
    )

    drawStraightConnectorLines(xStart = 0.475f, yStart = 0.35f, xEnd = 0.475f, yEnd =  0.25f)
    drawStraightConnectorLines(xStart = 0.53f, yStart = 0.24f, xEnd = 0.53f, yEnd = 0.14f)
    drawStraightConnectorLines(xStart = 0.2f, yStart = 0.14f, xEnd = 0.5f, yEnd = 0.14f)
    drawStraightConnectorLines(xStart = 0.5f, yStart = 0.12f, xEnd = 0.83f, yEnd = 0.12f)
    drawStraightConnectorLines(xStart = 0.2f, yStart = 0.34f, xEnd = 0.5f, yEnd = 0.34f)
    drawStraightConnectorLines(xStart = 0.71f, yStart = 0.23f, xEnd = 0.71f, yEnd = 0.15f)
    drawStraightConnectorLines(xStart = 0.77f, yStart = 0.38f, xEnd = 0.77f, yEnd = 0.25f)
    drawStraightConnectorLines(xStart = 0.5f, yStart = 0.38f, xEnd = 0.8f, yEnd = 0.38f)
    drawStraightConnectorLines(xStart = 0.3f, yStart = 0.38f, xEnd = 0.3f, yEnd = 0.28f)
    drawStraightConnectorLines(xStart = 0.238f, yStart = 0.28f, xEnd = 0.238f, yEnd = 0.15f)
}
