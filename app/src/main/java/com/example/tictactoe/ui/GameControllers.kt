package com.example.tictactoe.ui

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.indication
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.dp
import com.example.tictactoe.Data.Action
import com.example.tictactoe.Data.Direction
import com.example.tictactoe.TAG
import com.example.tictactoe.ui.theme.*


@Composable
fun FullController(modifier: Modifier = Modifier, arrowOnClick: (direction: Direction) -> Unit, actionOnClick: (action: Action) -> Unit) {
    Column(
        modifier = modifier,
    ) {
        Surface(
            modifier = Modifier
                .border(width = 5.dp, color = Color.Black, shape = CircleShape)
                .shadow(5.dp, shape = CircleShape), elevation = 5.dp, color = retroDarkGrey
        ) {
            Row(
                modifier = Modifier.padding(start = 25.dp, end = 35.dp, top = 25.dp, bottom = 25.dp)
            ) {
                //right arrow button distorts on pixel 4a if not at 1.2f
                GameControlsLeft(modifier = Modifier.weight(1.2f), arrowOnClick)
                GameControlsRight(modifier = Modifier.weight(1f), actionOnClick)
            }
        }

    }
}

@Composable
fun GameControlsLeft(modifier: Modifier = Modifier, arrowOnClick: (direction: Direction) -> Unit) {
    Column(
        modifier = modifier
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            ArrowButton(onClick = { arrowOnClick(Direction.LEFT) }, iconRotation = 180f)
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                ArrowButton(onClick = { arrowOnClick(Direction.UP) }, iconRotation = -90f)
                ArrowButton(onClick = { arrowOnClick(Direction.DOWN) }, iconRotation = 90f)
            }
            ArrowButton(onClick = { arrowOnClick(Direction.RIGHT) }, iconRotation = 0f)
        }
    }
}

@Composable
fun GameControlsRight(modifier: Modifier = Modifier, actionOnClick: (action: Action) -> Unit) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            ActionButtons(onClick = { actionOnClick(Action.PLACE) }, icon = Icons.Filled.AdsClick)
            ActionButtons(onClick = { actionOnClick (Action.DESTROY)}, icon = Icons.Filled.Bolt)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            ActionButtons(onClick = { actionOnClick (Action.LOCK)}, icon = Icons.Filled.Lock)
            ActionButtons(onClick = { actionOnClick (Action.RANDOM)}, icon = Icons.Filled.QuestionMark)
        }
    }
}


































