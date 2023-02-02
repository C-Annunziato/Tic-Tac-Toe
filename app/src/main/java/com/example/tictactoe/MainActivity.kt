package com.example.tictactoe

import android.os.Bundle
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
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.tictactoe.Data.TileState
import com.example.tictactoe.Data.T3ViewModel
import com.example.tictactoe.Data.TileValue
import com.example.tictactoe.Data.listOfState
import com.example.tictactoe.ui.*
import com.example.tictactoe.ui.theme.playerTextFont

const val TAG = "main"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm = ViewModelProvider(this)[T3ViewModel::class.java]
            TicTacToe(viewModel = vm, liveDataListOfTileStates = vm.tileState)
        }
    }
}

@Composable
fun TicTacToe(
    modifier: Modifier = Modifier,
    viewModel: T3ViewModel,
    liveDataListOfTileStates: LiveData<List<TileState>?>
) {

    val liveBoardstate = liveDataListOfTileStates.observeAsState()

    Column(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier.weight(0.5f)
        ) {
            Board(
                listOfTileStates = liveBoardstate.value ?: listOfState, viewModel = viewModel
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
    modifier: Modifier = Modifier, listOfTileStates: List<TileState>?, viewModel: T3ViewModel
) {

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
                    val currentIndex = (i - 1) * 3 + (j - 1)
                    listOfTileStates?.getOrNull(currentIndex).let { tileState ->
                        Tile(onChooseTile = { bool ->
                            //only flip state if the tile is not occupied
                            if (!tileState?.tileIsOccupied!!) {
                                viewModel.updatePlayerState(
                                    listOfStateIndex = currentIndex, bool = bool
                                )
                            }
                        }, state = tileState, currentIndex = currentIndex, viewModel = viewModel)
                    }
                }
            }
        }
        Text(
            "${if (listOfTileStates?.first()?.isPlayer1Turn == true) "Player 1" else "Player 2"} Turn",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = playerTextFont,
            color = Color.Red
        )
    }
}

@Composable
fun Tile(
    modifier: Modifier = Modifier,
    onChooseTile: (Boolean) -> Unit,
    state: TileState?,
    currentIndex: Int,
    viewModel: T3ViewModel,
) {

    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .border(4.dp, Color.Black, shape = RoundedCornerShape(8.dp))
                .size(80.dp)
                .clickable(onClick = {
                    when (state?.isPlayer1Turn) {
                        true -> onChooseTile(true)
                        false -> onChooseTile(false)
                    }
                }), contentAlignment = Alignment.Center
        ) {

            when (state?.currentTileSymbolState?.ordinal) {
                TileValue.NONE.ordinal -> {}
                TileValue.CROSS.ordinal -> XX()
                TileValue.CIRCLE.ordinal -> OO()
            }
        }
    }
}

@Preview
@Composable

fun Preview() {
}














