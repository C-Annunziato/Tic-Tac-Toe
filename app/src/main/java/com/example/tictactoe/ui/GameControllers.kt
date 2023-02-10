package com.example.tictactoe.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.dp
import com.example.tictactoe.ui.theme.*


@Composable
fun FullController(modifier: Modifier = Modifier) {
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
                GameControlsLeft(modifier = Modifier.weight(1.2f))
                GameControlsRight(modifier = Modifier.weight(1f))
            }
        }

    }
}

@Composable
fun GameControlsLeft(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ArrowButton(onClick = { /*TODO*/ }, iconRotation = 180f)
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                ArrowButton(onClick = { /*TODO*/ }, iconRotation = -90f)
                ArrowButton(onClick = { /*TODO*/ }, iconRotation = 90f)
            }
            ArrowButton(onClick = { /*TODO*/ }, iconRotation = 0f)
        }
    }
}

@Composable
fun GameControlsRight(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            ActionButtons(onClick = { /*TODO*/ }, icon = Icons.Filled.AdsClick)
            ActionButtons(onClick = { /*TODO*/ }, icon = Icons.Filled.Bolt)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            ActionButtons(onClick = { /*TODO*/ }, icon = Icons.Filled.Lock)
            ActionButtons(onClick = { /*TODO*/ }, icon = Icons.Filled.QuestionMark)
        }
    }
}


































