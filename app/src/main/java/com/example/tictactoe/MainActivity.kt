package com.example.tictactoe

import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.*
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
import com.example.tictactoe.ui.GameControlsLeft
import com.example.tictactoe.ui.GameControlsRight
import com.example.tictactoe.ui.OO
import com.example.tictactoe.ui.XX

const val TAG = "main"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Log.i(TAG, "oncreatecalled")
            val vm = ViewModelProvider(this)[T3ViewModel::class.java]
            TicTacToe(viewModel = vm, boardState = vm.boardState)
        }
    }
}

@Composable
fun TicTacToe(
    modifier: Modifier = Modifier,
    viewModel: T3ViewModel,
    boardState: LiveData<BoardState>
) {

//    val tileState = viewModel.boardState.observeAsState().value
//    val xtileState by remember {
//        mutableStateOf(boardState)
//    }

    val liveBoardstate = boardState.observeAsState().value

//
    Log.i(TAG, "${liveBoardstate!!.isOTurn} observed value")



    Column(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier.weight(0.5f)

        ) {
            Board(
                boardState = liveBoardstate, viewModel = viewModel
            )
            //this is for some controls
        }
        Row(
            modifier = Modifier
                .weight(0.5f)

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
    modifier: Modifier = Modifier, boardState: BoardState?, viewModel: T3ViewModel
) {

//    val tileState = boardState.observeAsState().value
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize(),
    ) {
        Text("Complete a row, diagonal or column")
        for (i in 1..3) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.padding(10.dp)
            ) {
                for (j in 1..3) {

                    Tiles(
                        onChooseTile = { viewModel.updateBoardState(true) },
                        state = boardState,
                        )
                }
            }
        }
    }
}


@Composable
fun Tiles(
    modifier: Modifier = Modifier, onChooseTile: () -> Unit, state: BoardState?,
    ) {
    Column(
    ) {
        Box(
            modifier = modifier
                .border(4.dp, Color.Black, shape = RoundedCornerShape(8.dp))
                .size(80.dp)
                .clickable(onClick = { onChooseTile() }), contentAlignment = Alignment.Center
        ) {
            Log.i(TAG, "${state!!.isXTurn} XXX")
            Log.i(TAG, "${state!!.isOTurn} OOO")

            if (state!!.isXTurn) {
                XX()
            } else if (state!!.isOTurn) {
                OO()
            }
//            Text("hey")
        }
    }
}





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}













