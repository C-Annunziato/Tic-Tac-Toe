package com.example.tictactoe.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.Data.*
import com.example.tictactoe.ui.theme.*

@Composable
fun TicTacToeBoard(
    modifier: Modifier = Modifier,
    listOfTileAndGameStates: List<TileAndGameState>?,
    viewModel: T3ViewModel,
    arrowState: State<ControllerState?>,
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
                    Tile(
                        state = listOfTileAndGameStates?.getOrNull(currentIndex),
                        currentIndex = currentIndex,
                        viewModel = viewModel
                    )
                }
            }
        }
        Row(
            modifier = Modifier,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "${if (listOfTileAndGameStates?.first()?.isPlayer1Turn == true) "Player 2" else "Player 1"}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = playerTextFont3,
                    color = if (listOfTileAndGameStates?.first()?.isPlayer1Turn == true) retroGreen else Color.Blue,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 33.dp, 5.dp)
                        .alpha(alpha = if (listOfTileAndGameStates?.first()?.isPlayer1Turn == true) 1.0f else 0.40f)
                )

                Text(
                    text = "${if (listOfTileAndGameStates?.first()?.isPlayer1Turn == true) "Player 1" else "Player 2"}",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = playerTextFont3,
                    color = if (listOfTileAndGameStates?.first()?.isPlayer1Turn == true) Color.Blue else retroGreen,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 33.dp, 5.dp)
                        .alpha(alpha = if (listOfTileAndGameStates?.first()?.isPlayer1Turn == true) 0.40f else 1.0f)
                )
            }
            CountdownTimer(modifier.weight(1.2f))
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Tile(
    modifier: Modifier = Modifier,
    state: TileAndGameState?,
    currentIndex: Int,
    viewModel: T3ViewModel,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier = Modifier
                .border(
                    4.dp,
                    color = if (state?.isSelected == true) Color.Magenta else Color.Black,
                    shape = RoundedCornerShape(8.dp)
                )
                .size(80.dp)
                .drawBehind {
                    drawRoundRect(
                        //if selectedState draw pink else black
                        color = if (state?.isSelected == true) Color.Magenta else Color.Black,
                        size = Size(width = 84.dp.toPx(), height = 84.dp.toPx()),
                        cornerRadius = CornerRadius(x = 30f, y = 30f)
                    )
                },
            elevation = 5.dp,
            shape = RoundedCornerShape(8.dp),
            backgroundColor = retroNearWhite
        ) {
            AnimatedVisibility(
                visible = viewModel.tileAndGameState.value?.get(currentIndex)?.symbolInTile != TileValue.NONE,
                enter = scaleIn(tween(150)),
            ) {
                when (state?.symbolInTile?.ordinal) {

                    TileValue.NONE.ordinal -> {}
                    TileValue.CROSS.ordinal -> DrawCross()
                    TileValue.CIRCLE.ordinal -> CircleOfSquares()
                    TileValue.STAR.ordinal -> Star()
                    TileValue.DESTROYED.ordinal -> Destroyed()
                    TileValue.LOCKED.ordinal -> Locked()
                }
            }
        }
    }
}



