package com.example.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tictactoe.Data.BoardState
import com.example.tictactoe.Data.T3ViewModel
import com.example.tictactoe.ui.theme.Shapes

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
        Column(modifier = modifier
            .weight(0.6f, true)
            .background(Color.Blue)) {
            Board(boardState = viewModel.boardState)
            //this is for some controls
        }
        Column(
            modifier = Modifier
                .weight(0.4f, true)
                .background(Color.Gray)
                .fillMaxSize()
        ) {
            //Controls
            GameController()
        }
    }
}

@Composable
fun Board(
    modifier: Modifier = Modifier,
    boardState: LiveData<BoardState>
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
                horizontalArrangement = Arrangement.spacedBy(14.dp), modifier = Modifier.padding(
                    10.dp
                )
            ) {
                for (j in 1..3) {
                    Tiles(onChooseTile = {} )
                }
            }
        }
    }
}


@Composable
fun Tiles(
    modifier: Modifier = Modifier,
    onChooseTile: () -> Unit
) {
    Column(
    ) {
        Box(modifier = modifier
            .border(4.dp, Color.Black, shape = RoundedCornerShape(8.dp))
            .size(80.dp)
            .clickable { onChooseTile },
        contentAlignment = Alignment.Center
            ){
            XX()
//            OO()
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
            radius = 50f, center = Offset(
                x = canvasWidth / 2, y = canvasHeight / 2
            ), color = Color.Black, style = Stroke(width = 10f)
        )
    }
}


@Composable
fun GameController(modifier: Modifier = Modifier){
    
    Column(modifier = modifier){
        Button(onClick = { }, modifier = Modifier) {
            Icon(Icons.Filled.Refresh, contentDescription = "rewind")
        }
    }
    
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}













