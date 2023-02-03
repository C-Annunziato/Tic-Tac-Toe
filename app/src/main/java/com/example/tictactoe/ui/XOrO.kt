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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.tictactoe.ui.theme.*
import java.util.*


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
            color = Color(73, 71, 58, 255),
            strokeWidth = 12f,
            cap = StrokeCap.Butt,
            start = Offset(x = 10f, y = -30f),
            end = Offset(x = size.width, y = size.height + 25),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(17f, 17f))
        )

        //top right to bottom left
        drawLine(
            color = Color(73, 71, 58, 255),
            strokeWidth = 12f,
            cap = StrokeCap.Butt,
            start = Offset(x = size.width, y = -45f),
            end = Offset(x = 5f, y = size.height + 10),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(16f, 16f))
        )

        drawLine(
            color = Color(41, 83, 83, 255),
            strokeWidth = 12f,
            cap = StrokeCap.Butt,
            start = Offset(x = 20f, y = -30f),
            end = Offset(x = size.width + 10, y = size.height + 25),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(17f, 17f))
        )

        drawLine(
            color = Color(41, 83, 83, 255),
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
    Canvas(modifier = modifier.drawBehind {
        drawCircle(
            color = retroTan, radius = 55f
        )
    }) {

        val transparentMask = createStripeBrush()

        val canvasWidth = size.width
        val canvasHeight = size.height

        val colorStops = arrayOf(
            0.0f to retroTan,
            0.2f to retroTeal,
            0.4f to retroBlue,
            0.6f to retroPink,
            0.9f to retroOrange,
        )


        drawCircle(
            brush = Brush.verticalGradient(colorStops = colorStops),
            radius = 50f,
            center = Offset(
                x = canvasWidth / 2, y = canvasHeight / 2
            ),
        )

        drawCircle(
            brush = transparentMask,
            radius = 50f,
            center = Offset(
                x = canvasWidth / 2, y = canvasHeight / 2
            ),
        )


    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun Prev() {
    XX()
}

private fun createStripeBrush(
): Brush {

    val colorStops = arrayOf(
        0.0f to Color.White,
        0.3f to Color.Transparent,
    )

    return Brush.verticalGradient(
        colorStops = colorStops, tileMode = TileMode.Repeated, startY = 0f, endY = 18f
    )
}