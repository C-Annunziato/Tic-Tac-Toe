package com.example.tictactoe

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.tictactoe.Data.TileState
import com.example.tictactoe.Data.T3ViewModel
import com.example.tictactoe.Data.listOfState
import com.example.tictactoe.ui.GameControlsLeft
import com.example.tictactoe.ui.GameControlsRight
import com.example.tictactoe.ui.OO
import com.example.tictactoe.ui.XX

const val TAG = "main"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            log.i(tag, "oncreatecalled")
            val vm = ViewModelProvider(this)[T3ViewModel::class.java]
            TicTacToe(viewModel = vm, tileState = vm.tileState)
        }
    }
}

@Composable
fun TicTacToe(
    modifier: Modifier = Modifier, viewModel: T3ViewModel, tileState: LiveData<List<TileState>?>
) {

    val liveBoardstate = tileState.observeAsState()

    Column(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier.weight(0.5f)

        ) {
            Board(
                tileState = liveBoardstate.value ?: listOfState, viewModel = viewModel
            )
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
    modifier: Modifier = Modifier, tileState: List<TileState>?, viewModel: T3ViewModel
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
                    if (tileState != null) {
                        Tile(onChooseTile = {
                            //pass and boolean and update specific tile with some logic
                            viewModel.updateBoardState(it)
                            //figure out how to individually deliver each tilestate to this parameter
                            // turn list of 9 items in 3x3 grid
                        }, state = tileState.)
                    }
                }

            }
        }
    }
}


@Composable
fun Tile(
    modifier: Modifier = Modifier, onChooseTile: (Boolean) -> Unit, state: TileState?
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .border(4.dp, Color.Black, shape = RoundedCornerShape(8.dp))
                .size(80.dp)
                .clickable(onClick = { onChooseTile(true) }),
            contentAlignment = Alignment.Center
        ) {

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













