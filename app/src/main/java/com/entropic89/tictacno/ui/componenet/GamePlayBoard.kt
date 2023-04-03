package com.entropic89.tictacno.ui.componenet

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.entropic89.tictacno.ui.*
import com.entropic89.tictacno.ui.model.TileAndGameState
import com.entropic89.tictacno.ui.model.TileValue
import com.entropic89.tictacno.ui.theme.*
import com.entropic89.tictacno.ui.viewmodel.T3ViewModel
import kotlinx.coroutines.delay

const val LOG = "gameplay"

@Composable
fun TicTacToeBoard(
    modifier: Modifier = Modifier,
    listOfTileAndGameStates: List<TileAndGameState>?,
    viewModel: T3ViewModel,
    turnOver: () -> Unit,
//    onTileSelect: () -> Unit
) {
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
                modifier = Modifier.padding(10.dp)
            ) {
                for (j in 1..3) {
                    val currentIndex = (i - 1) * 3 + (j - 1)

                    val haptic = LocalHapticFeedback.current
                    Tile(
                        state = listOfTileAndGameStates?.getOrNull(currentIndex),
                        currentIndex = currentIndex,
                        viewModel = viewModel,
                        onTileSelect = {
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            viewModel.updateTileOnTouchSelected(currentIndex, doubleTap = false)},
                        onDoubleTap = {
//                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            viewModel.updateTileOnTouchSelected(currentIndex, doubleTap = true)},
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
                BlinkingText1(listOfTileAndGameStates = listOfTileAndGameStates)
                BlinkingText2(listOfTileAndGameStates = listOfTileAndGameStates)

            }

            if (listOfTileAndGameStates?.first()?.disableCountDown == false) {
                if (viewModel.tileAndGameState.value!!.first().gameIsComplete) {
                    CountdownTimer(modifier.weight(1.2f), turnOver = turnOver, true)
                    //alternate countdown based on turn
                } else if (viewModel.tileAndGameState.value?.first()?.isPlayer1Turn == true) {
                    //alternate countdown based on turn
                    CountdownTimer(modifier.weight(1.2f), turnOver = turnOver)
                } else {
                    CountdownTimer(modifier.weight(1.2f), turnOver = turnOver)
                }
            } else if (listOfTileAndGameStates?.first()?.disableCountDown == true) {
                //prevent the space from collapsing
                Column(modifier = Modifier.weight(1.2f)) {
                    Text(
                        "Space Holder",
                        fontSize = 16.sp,
                        fontFamily = playerTextFont3,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier.padding(5.dp),
                        color = Color.Transparent
                    )
                    Text(
                        "Space Holder",
                        fontSize = 17.sp,
                        fontFamily = playerTextFont3,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 5.dp, start = 5.dp, end = 5.dp),
                        color = Color.Transparent
                    )
                }
            }
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
    onTileSelect: () -> Unit,
    onDoubleTap: () -> Unit,
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
                }
                .pointerInput(Unit) {
                    detectTapGestures(onDoubleTap = { onDoubleTap() }, onPress = { onTileSelect()})
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


@Composable
fun BlinkingText1(listOfTileAndGameStates: List<TileAndGameState>?) {
    var isVisible by remember { mutableStateOf(true) }

    if (listOfTileAndGameStates != null) {
        LaunchedEffect(listOfTileAndGameStates.first().gameIsComplete) {
            while (listOfTileAndGameStates.first().gameIsComplete) {
                delay(1250)
                isVisible = !isVisible
            }
        }
    }

    if (listOfTileAndGameStates?.first()?.gameIsComplete == false) {
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible, enter = fadeIn(), exit = fadeOut()
    ) {
        Text(
            text = if (listOfTileAndGameStates?.first()?.gameIsComplete == true) {
                "GAME"
            } else if (listOfTileAndGameStates?.first()?.isPlayer1Turn == true) "Player 2" else "Player 1",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = playerTextFont3,
            color = if (listOfTileAndGameStates?.first()?.gameIsComplete == true) retroDarkBlue else if (listOfTileAndGameStates?.first()?.isPlayer1Turn == true) retroGreen else Color.Blue,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 33.dp, 5.dp)
                .alpha(
                    alpha = if (listOfTileAndGameStates?.first()?.gameIsComplete == true) 1.0f else if (listOfTileAndGameStates?.first()?.isPlayer1Turn == true) 1.0f else 0.40f
                )
        )

    }
}


@Composable
fun BlinkingText2(listOfTileAndGameStates: List<TileAndGameState>?) {
    var isVisible by remember { mutableStateOf(true) }

    if (listOfTileAndGameStates != null) {
        LaunchedEffect(listOfTileAndGameStates.first().gameIsComplete) {
            while (listOfTileAndGameStates.first().gameIsComplete) {
                delay(1250)
                isVisible = !isVisible
            }
        }
    }

    if (listOfTileAndGameStates?.first()?.gameIsComplete == false) {
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible, enter = fadeIn(), exit = fadeOut()
    ) {

        Text(
            text = if (listOfTileAndGameStates?.first()?.gameIsComplete == true) {
                "OVER"
            } else if (listOfTileAndGameStates?.first()?.isPlayer1Turn == true) "Player 1" else "Player 2",
            fontSize = if (listOfTileAndGameStates?.first()?.gameIsComplete == true) 20.sp else 21.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = playerTextFont3,
            color = if (listOfTileAndGameStates?.first()?.gameIsComplete == true) retroDarkBlue else if (listOfTileAndGameStates?.first()?.isPlayer1Turn == true) Color.Blue else retroGreen,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 33.dp, 5.dp)
                .alpha(alpha = if (listOfTileAndGameStates?.first()?.isPlayer1Turn == true) 0.40f else 1.0f)
        )

    }
}


