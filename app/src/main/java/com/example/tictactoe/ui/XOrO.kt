package com.example.tictactoe.ui

import android.R.attr.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.tictactoe.ui.theme.*
import java.lang.Math.cos
import java.lang.Math.sin
import java.util.*


@Composable
fun XX(modifier: Modifier = Modifier) {

    val square = Path().apply {
        lineTo(20f, 0f)
        lineTo(20f, 20f)
        lineTo(0f, 20f)
        close()
    }
    Canvas(
        modifier = Modifier
            .size(60.dp)
            .padding(20.dp)
            .padding(end = 10.dp)


    ) {
        //top left to bottom right

        drawLine(
            color = Color(59, 93, 194, 255),
            strokeWidth = 12f,
            cap = StrokeCap.Butt,
            start = Offset(x = 10f, y = -25f),
            end = Offset(x = size.width, y = size.height + 25),
            pathEffect = PathEffect.stampedPathEffect(
                shape = square, style = StampedPathEffectStyle.Translate, phase = 0f, advance = 20f
            )
        )

        //top right to bottom left
        drawLine(
            color = Color(59, 93, 194, 255),
            strokeWidth = 12f,
            cap = StrokeCap.Butt,
            start = Offset(x = size.width, y = -35f),
            end = Offset(x = 5f, y = size.height + 10),
            pathEffect = PathEffect.stampedPathEffect(
                shape = square, style = StampedPathEffectStyle.Translate, phase = 0f, advance = 20f
            )
        )
//        FF4F5253
        drawLine(
            color = Color(142, 194, 59, 255),
            strokeWidth = 12f,
            cap = StrokeCap.Butt,
            start = Offset(x = 20f, y = -25f),
            end = Offset(x = size.width + 10, y = size.height + 25),
            pathEffect = PathEffect.stampedPathEffect(
                shape = square, style = StampedPathEffectStyle.Translate, phase = 0f, advance = 20f
            )
        )

        drawLine(
            color = Color(142, 194, 59, 255),
            strokeWidth = 12f,
            cap = StrokeCap.Butt,
            start = Offset(x = size.width + 10, y = -35f),
            end = Offset(x = 15f, y = size.height + 10),
            pathEffect = PathEffect.stampedPathEffect(
                shape = square, style = StampedPathEffectStyle.Translate, phase = 0f, advance = 20f
            )
        )

    }
}

@Composable
fun CircleOfSquares(radius: Float, squaresCount: Int) {
    val step = (2 * Math.PI) / squaresCount
    for (i in 0 until squaresCount) {
        val angle = step * i
        val x = (radius * cos(angle)).toFloat() + radius
        val y = (radius * sin(angle)).toFloat() + radius
        Canvas(modifier = Modifier.offset(x = x.dp, y = y.dp)) {
            drawRect(color = Color.Black, style = Fill)
        }
    }
}



@Composable
fun OO(modifier: Modifier = Modifier) {

    Canvas(modifier = modifier.drawBehind {
        drawCircle(
            color = retroBrown, radius = 75f
        )
    }) {

        val transparentMask = createStripeBrush()

        val canvasWidth = size.width
        val canvasHeight = size.height

//        val colorStops = arrayOf(
//            0.0f to retroTan,
//            0.2f to retroTeal,
//            0.4f to retroBlue,
//            0.6f to retroPink,
//            0.9f to retroOrange,
//        )

        val colorStops = arrayOf(
            0.0f to retroYellow,
            0.5f to retroBlack,
            0.8f to retroCustomRed,
        )




        drawCircle(
            brush = Brush.verticalGradient(colorStops = colorStops),
            radius = 70f,
            center = Offset(
                x = canvasWidth / 2, y = canvasHeight / 2
            ),

            )

        drawCircle(
            brush = transparentMask,
            radius = 70f,
            center = Offset(
                x = canvasWidth / 2, y = canvasHeight / 2
            ),
        )

        drawCircle(
            color = retroBrown,
            radius = 58f,
            center = Offset(
                x = canvasWidth / 2, y = canvasHeight / 2
            ),
        )
        drawCircle(
            color = Color.White,
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
    CircleOfSquares(radius = 0.5f, squaresCount = 100)
}

private fun createStripeBrush(
): Brush {

    val colorStops = arrayOf(
        0.0f to Color.White,
        0.3f to Color.Transparent,
    )

    return Brush.verticalGradient(
        colorStops = colorStops, tileMode = TileMode.Repeated, startY = 0f, endY = 12f
    )
}