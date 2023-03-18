package com.entropic89.tictacno.ui.componenet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdsClick
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.entropic89.tictacno.ui.model.Action
import com.entropic89.tictacno.ui.model.Direction
import com.entropic89.tictacno.ui.model.Player
import com.entropic89.tictacno.ui.theme.playerTextFont3
import com.entropic89.tictacno.ui.theme.retroDarkGrey
import com.entropic89.tictacno.ui.theme.retroPurple
import kotlin.math.ceil

const val TAG = "controller"

@Composable
fun FullController(

    modifier: Modifier = Modifier,
    arrowOnClick: (direction: Direction) -> Unit,
    actionOnClick: (action: Action) -> Unit,
    destroyButtonOnCooldown: () -> Boolean,
    destroyCooldownLeft: () -> Int,
    lockButtonOnCooldown: () -> Boolean,
    lockCooldownLeft: () -> Int,
    transposeButtonOnCooldown: () -> Boolean,
    transposeCooldownLeft: () -> Int,
    buttonBorderColor: Color
) {
    Column(
        modifier = modifier,
    ) {
        Surface(
            modifier = Modifier
                .border(width = 5.dp, color = Color.Black, shape = CircleShape)
                .shadow(5.dp, shape = CircleShape), elevation = 5.dp, color = retroDarkGrey
        ) {
            Row(
                modifier = Modifier.padding(start = 24.dp, end =30.dp, top = 25.dp, bottom = 25.dp)
            ) {
                GameControlsLeft(modifier = Modifier.weight(1.3f), arrowOnClick)
                GameControlsRight(
                    modifier = Modifier.weight(1f),
                    actionOnClick,
                    destroyButtonOnCooldown,
                    destroyCooldownLeft,
                    lockButtonOnCooldown,
                    lockCooldownLeft,
                    transposeButtonOnCooldown,
                    transposeCooldownLeft,
                    buttonBorderColor
                )
            }
        }

    }
}

@Composable
fun GameControlsLeft(
    modifier: Modifier = Modifier, arrowOnClick: (direction: Direction) -> Unit
) {
    Column(
        modifier = modifier
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            ArrowButton(onClick = { arrowOnClick(Direction.LEFT) }, iconRotation = 180f)
            Column(
                verticalArrangement = Arrangement.spacedBy(13.dp),
            ) {
                ArrowButton(onClick = { arrowOnClick(Direction.UP) }, iconRotation = -90f)
                ArrowButton(onClick = { arrowOnClick(Direction.DOWN) }, iconRotation = 90f)
            }
            ArrowButton(onClick = { arrowOnClick(Direction.RIGHT) }, iconRotation = 0f)
        }
    }
}

@Composable
fun GameControlsRight(
    modifier: Modifier = Modifier,
    actionOnClick: (action: Action) -> Unit,
    destroyButtonOnCooldown: () -> Boolean,
    destroyCooldownLeft: () -> Int,
    lockButtonOnCooldown: () -> Boolean,
    lockCooldownLeft: () -> Int,
    transposeButtonOnCooldown: () -> Boolean,
    transposeCooldownLeft: () -> Int,
    buttonBorderColor: Color
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            ActionButtons(
                onClick = { actionOnClick(Action.PLACE) },
                icon = Icons.Filled.AdsClick,
                borderColor = buttonBorderColor
            )
            if (destroyButtonOnCooldown()) {
                DeadButton(ceil(destroyCooldownLeft().toDouble() / 2).toInt())
            } else {
                ActionButtons(
                    onClick = { actionOnClick(Action.DESTROY) },
                    icon = Icons.Filled.Bolt,
                    borderColor = buttonBorderColor
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            if (lockButtonOnCooldown()) {
                DeadButton(lockCooldownLeft())
            } else {
                ActionButtons(
                    onClick = { actionOnClick(Action.LOCK) },
                    icon = Icons.Filled.Lock,
                    borderColor = buttonBorderColor
                )
            }
            if (transposeButtonOnCooldown()) {
                //fixes displayed numbers to be representative of the number of turns til CD ready
                //since each subtraction happens on both turns numbers jump by 2, this fixes it
                DeadButton(ceil(transposeCooldownLeft().toDouble() / 2).toInt())
            } else {
                ActionButtons(
                    onClick = { actionOnClick(Action.TRANSPOSE) },
                    icon = Icons.Filled.Shuffle,
                    borderColor = buttonBorderColor
                )
            }
        }
    }
}


@Composable
fun FullController(
    modifier: Modifier = Modifier,
    player: Player,
    onUpdateArrowButtonClick: (Direction) -> Unit,
    onUpdateActionButtonClick: (Action) -> Unit,
    onResetBoard: () -> Unit
){
    Column(
        modifier = modifier
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
//        FullController(
//                arrowOnClick = onUpdateArrowButtonClick,
//                actionOnClick = onUpdateActionButtonClick,
//                destroyButtonOnCooldown = { player.controllerState?.destroyButtonIsOnCooldownP2!! },
//                destroyCooldownLeft = { player.controllerState?.destroyCooldownLeftP2 ?: 0 },
//                lockButtonOnCooldown = { player.controllerState?.lockButtonIsOnCooldownP2!! },
//                lockCooldownLeft = { player.controllerState?.lockButtonCooldownLeftP2 ?: 0 },
//                transposeButtonOnCooldown = { player.controllerState?.transposeButtonIsOnCooldownP2!! },
//                transposeCooldownLeft = {player.controllerState?.transposeCooldownLeftP2 ?: 0 },
//                buttonBorderColor = retroGreen,
//                modifier = Modifier.padding(bottom = 40.dp)
//            )



//        if (liveBoardState.value?.get(0)?.isPlayer1Turn == true) {
//
//            FullController(
//                arrowOnClick = { viewModel.updateArrowButtonState(direction = it) },
//                actionOnClick = { viewModel.updateActionButtonState(action = it) },
//                destroyButtonOnCooldown = { controllerState.value?.destroyButtonIsOnCooldownP1!! },
//                destroyCooldownLeft = { controllerState.value?.destroyCooldownLeftP1 ?: 0 },
//                lockButtonOnCooldown = { controllerState.value?.lockButtonIsOnCooldownP1!! },
//                lockCooldownLeft = { controllerState.value?.lockButtonCooldownLeftP1 ?: 0 },
//                transposeButtonOnCooldown = { controllerState.value?.transposeButtonIsOnCooldownP1!! },
//                transposeCooldownLeft = { controllerState.value?.transposeCooldownLeftP1 ?: 0 },
//                buttonBorderColor = retroPurple,
//                modifier = Modifier.padding(bottom = 40.dp)
//            )
//        } else {
//
//            FullController(
//                arrowOnClick = { viewModel.updateArrowButtonState(direction = it) },
//                actionOnClick = { viewModel.updateActionButtonState(action = it) },
//                destroyButtonOnCooldown = { controllerState.value?.destroyButtonIsOnCooldownP2!! },
//                destroyCooldownLeft = { controllerState.value?.destroyCooldownLeftP2 ?: 0 },
//                lockButtonOnCooldown = { controllerState.value?.lockButtonIsOnCooldownP2!! },
//                lockCooldownLeft = { controllerState.value?.lockButtonCooldownLeftP2 ?: 0 },
//                transposeButtonOnCooldown = { controllerState.value?.transposeButtonIsOnCooldownP2!! },
//                transposeCooldownLeft = { controllerState.value?.transposeCooldownLeftP2 ?: 0 },
//                buttonBorderColor = retroGreen,
//                modifier = Modifier.padding(bottom = 40.dp)
//            )
//        }
        OutlinedButton(
            onClick = onResetBoard,
            shape = CutCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                retroPurple
            ),
            elevation = ButtonDefaults.elevation(defaultElevation = 5.dp),
            border = BorderStroke(5.dp, color = Color.Black),
            modifier = Modifier.size(145.dp, 60.dp)
        ) {
            Text(
                "Reset",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                color = Color.White,
                fontFamily = playerTextFont3,
            )
        }
    }
}


































