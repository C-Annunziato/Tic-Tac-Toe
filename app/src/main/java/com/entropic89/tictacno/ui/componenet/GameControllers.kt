package com.entropic89.tictacno.ui.componenet

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
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
import androidx.compose.ui.unit.dp
import com.entropic89.tictacno.ui.model.Action
import com.entropic89.tictacno.ui.model.Direction
import com.entropic89.tictacno.ui.theme.retroDarkGrey
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
                modifier = Modifier.padding(start = 24.dp, end = 30.dp, top = 25.dp, bottom = 25.dp)
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


































