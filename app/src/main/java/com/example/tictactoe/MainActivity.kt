package com.example.tictactoe

import android.os.Bundle
import android.view.animation.AnticipateOvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
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
import com.example.tictactoe.ui.theme.playerTextFont2
import com.example.tictactoe.ui.theme.playerTextFont3
import com.example.tictactoe.ui.theme.playerTextFont4

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

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(196, 196, 196))
    ) {
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
            FullController()
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

            Text(
                "Complete a row, diagonal or column",
                fontFamily = playerTextFont4,
                color = Color.DarkGray
            )
            for (i in 1..3) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    modifier = Modifier.padding(10.dp)
                ) {
                    for (j in 1..3) {
                        val currentIndex = (i - 1) * 3 + (j - 1)

                        listOfTileStates?.getOrNull(currentIndex).let { tileState ->
                            Tile(
                                onChooseTile = { bool ->
                                    //only flip state if the tile is not occupied
                                    if (!tileState?.tileIsOccupied!!) {
                                        viewModel.updatePlayerState(
                                            listOfStateIndex = currentIndex, bool = bool
                                        )
                                    }
                                },
                                state = tileState,
                                currentIndex = currentIndex,
                                viewModel = viewModel
                            )
                        }
                    }
                }
            }
        }
        Text(
            "${if (listOfTileStates?.first()?.isPlayer1Turn == true) "Player 1" else "Player 2"} Turn",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = playerTextFont3,
            color = Color.Blue,
            modifier = Modifier.padding(5.dp)
        )
    }


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Tile(
    modifier: Modifier = Modifier,
    onChooseTile: (Boolean) -> Unit,
    state: TileState?,
    currentIndex: Int,
    viewModel: T3ViewModel,
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Card(modifier = Modifier
            .border(
                4.dp, Color.Black, shape = RoundedCornerShape(8.dp)
            )
            .size(80.dp)
            .clickable(onClick = {
                when (state?.isPlayer1Turn) {
                    true -> onChooseTile(true)
                    false -> onChooseTile(false)
                }
            })
            .drawBehind {
                drawRoundRect(
                    color = Color.Black,
                    size = Size(width = 84.dp.toPx(), height = 84.dp.toPx()),
                    cornerRadius = CornerRadius(x = 30f, y = 30f)
                )
            }, elevation = 5.dp, shape = RoundedCornerShape(8.dp)
        ) {

            AnimatedVisibility(
                visible = viewModel.tileState.value?.get(currentIndex)?.currentTileSymbolState != TileValue.NONE,
                enter = scaleIn(tween(150))
            ) {
                when (state?.currentTileSymbolState?.ordinal) {
                    TileValue.NONE.ordinal -> {}
                    TileValue.CROSS.ordinal -> XX()
                    TileValue.CIRCLE.ordinal -> CircleOfSquares()
                }
            }
        }
    }
}

@Preview
@Composable

fun Preview() {
}














