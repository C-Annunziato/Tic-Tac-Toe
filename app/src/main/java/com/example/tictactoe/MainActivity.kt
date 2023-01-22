package com.example.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToe()
        }
    }
}

@Composable
fun TicTacToe(modifier: Modifier = Modifier) {
    Board()
//    BoxBoard()
}

//@Composable
//fun BoxBoard(modifier: Modifier = Modifier) {
//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = modifier.fillMaxSize()
//    ) {
//        Box(
//            modifier = modifier.border(
//                4.dp, Color.Black
//            ).size(60.dp)
//        )
//    }
//}

@Composable
fun Board(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
//        Divider(
//            modifier = Modifier.width(100.dp),
//            thickness = 4.dp,
//            color = Color.Black
//        )
//        XX()
        OO()
    }
}

@Composable
fun XX (modifier: Modifier = Modifier) {
    Divider(
        modifier = Modifier
            .width(60.dp)
            .rotate(60f),
        thickness = 4.dp,
        color = Color.Black,
        startIndent = 10.dp
    )
    Divider(
        modifier = Modifier
            .width(50.dp)
            .rotate(120f),
        thickness = 4.dp,
        color = Color.Black,

        )
}

@Composable
fun OO (modifier: Modifier = Modifier) {
   Canvas(modifier = modifier){

       val canvasWidth = size.width
       val canvasHeight = size.height

       drawCircle(radius = 50f, center = Offset(
           x = canvasWidth / 2,
           y = canvasHeight / 2
       ), color = Color.Black,
       style = Stroke(width = 10f,)
           )

   }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}












