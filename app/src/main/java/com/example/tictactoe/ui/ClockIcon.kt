package com.example.tictactoe.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp


@Composable
fun ClockIcon(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.size(80.dp), contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = modifier) {
                val canvasWidth = size.width
                val canvasHeight = size.height
                drawCircle(
                    radius = 85f,
                    center = Offset(
                        x = canvasWidth / 2, y = canvasHeight / 2
                    ),
                    color = Color.White,
                    style = Stroke(
                        width = 10f, pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(20f, 20f), 20f
                        )
                    ),
                )
            }
            Divider(
                modifier = Modifier
                    .width(50.dp)
                    .rotate(270f),
                thickness = 4.dp,
                color = Color.White,
                startIndent = 23.dp
            )

            Divider(
                modifier = Modifier
                    .rotate(-20f)
                    .width(50.dp),
                thickness = 4.dp,
                color = Color.White,
                startIndent = 23.dp
            )
        }
    }
}