package com.example.tictactoe.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tictactoe.ui.theme.*


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
fun CircleOfSquares(
    numberOfSquares: Int = 32,
) {
    Canvas(
        modifier = Modifier.size(70.dp).padding(end = 6.dp)
    ) {

        val radius = (size.width * .5f -20f) - (.25f * (size.width * .5f - 20f))
        val lineDegree = (360f * 2) / numberOfSquares

        for (squareNumber in 0..numberOfSquares) {

            val angleInDegrees = lineDegree * (squareNumber - 90f)
            val angleRad = Math.toRadians(angleInDegrees.toDouble()).toFloat()

            drawRect(
                color = Color(69, 157, 172, 255), topLeft = Offset(
                    x = (radius ) * kotlin.math.cos(angleRad) + size.center.x,
                    y = (radius ) * kotlin.math.sin(angleRad) + size.center.y -20f
                ), size = Size(22f, 22f))


            drawRect(
                color = Color(134, 105, 213, 255), topLeft = Offset(
                    x = (radius ) * kotlin.math.cos(angleRad) + size.center.x,
                    y = (radius ) * kotlin.math.sin(angleRad) + size.center.y
                ), size = Size(22f, 22f))


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