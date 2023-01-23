package com.example.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.tictactoe.Data.BoardState
import com.example.tictactoe.Data.T3ViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val vm = ViewModelProvider(this)[T3ViewModel::class.java]
            TicTacToe(viewModel = vm)
        }
    }
}

@Composable
fun TicTacToe(modifier: Modifier = Modifier, viewModel: T3ViewModel) {

    Column(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier.weight(0.5f, true)

        ) {
            Board(boardState = viewModel.boardState)
            //this is for some controls
        }
        Row(
            modifier = Modifier
                .weight(0.5f, true)
                .background(Color.Gray)
                .fillMaxSize()
        ) {
            //Controls
            GameControlsLeft(modifier = Modifier.weight(1f))
            GameControlsRight(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun Board(
    modifier: Modifier = Modifier, boardState: LiveData<BoardState>
) {

    val tileState = boardState.observeAsState().value

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize(),
    ) {
        for (i in 1..3) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.padding(
                    10.dp
                )
            ) {
                for (j in 1..3) {
                    Tiles(onChooseTile = {})
                }
            }
        }
    }
}


@Composable
fun Tiles(
    modifier: Modifier = Modifier, onChooseTile: () -> Unit
) {
    Column(
    ) {
        Box(
            modifier = modifier
                .border(4.dp, Color.Black, shape = RoundedCornerShape(8.dp))
                .size(80.dp)
                .clickable { onChooseTile }, contentAlignment = Alignment.Center
        ) {
//            XX()
            OO()
        }

    }
}

@Composable
fun XX(modifier: Modifier = Modifier) {
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
            style = Stroke(width = 10f),
        )
    }
}


@Composable
fun GameControlsLeft(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.padding(start = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = { }, shape = CircleShape, modifier = Modifier.size(80.dp)
        ) {
            Icon(
                Icons.Filled.Refresh, contentDescription = "rewind", modifier = Modifier.size(50.dp)
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            Button(
                onClick = { },
                shape = CircleShape,
                modifier = Modifier.size(80.dp),
//            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondaryVariant)
            ) {
                ClockIcon()
            }
            Button(
                onClick = { }, shape = CircleShape, modifier = Modifier.size(80.dp)
            ) {
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = "remove",
                    modifier = Modifier.size(50.dp)
                )
            }
        }
        Text("Player 1", fontSize = 30.sp,)
    }
}


@Composable
fun GameControlsRight(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { }, shape = CircleShape, modifier = Modifier.size(80.dp)
        ) {
            Icon(
                Icons.Filled.Refresh, contentDescription = "rewind", modifier = Modifier.size(50.dp)
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                Button(
                    onClick = { }, shape = CircleShape, modifier = Modifier.size(80.dp)
                ) {
                    Icon(
                        Icons.Filled.Clear,
                        contentDescription = "remove",
                        modifier = Modifier.size(50.dp)
                    )
                }
            Button(
                onClick = { },
                shape = CircleShape,
                modifier = Modifier.size(80.dp),
//            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondaryVariant)
            ) {
                ClockIcon()
            }
            }
        Text("Player 2", fontSize = 30.sp,)
    }
}

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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}













