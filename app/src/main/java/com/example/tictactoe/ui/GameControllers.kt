package com.example.tictactoe.ui

import android.graphics.drawable.ClipDrawable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Stream
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun GameControlsLeft(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
           arrowButton(onClick = { /*TODO*/ }, iconRotation =180f )
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                arrowButton(onClick = { /*TODO*/ }, iconRotation = -90f )
                arrowButton(onClick = { /*TODO*/ }, iconRotation = 90f )
            }
            arrowButton(onClick = { /*TODO*/ }, iconRotation =0f )
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
                actionButtons(onClick = { /*TODO*/ }, icon =Icons.Filled.AdsClick )
                actionButtons(onClick = { /*TODO*/ }, icon =Icons.Filled.Bolt )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                actionButtons(onClick = { /*TODO*/ }, icon = Icons.Filled.Lock)
                actionButtons(onClick = { /*TODO*/ }, icon = Icons.Filled.QuestionMark)
            }
    }
}


@Composable
fun FullController() {


    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .border(width = 5.dp, color = Color.Black, shape = CircleShape)
                .shadow(5.dp, shape = CircleShape), elevation = 5.dp, color = retroDarkGrey
        ) {
            Row(
                modifier = Modifier.padding(start = 35.dp, end = 35.dp, top = 25.dp, bottom = 25.dp)
            ) {
                GameControlsLeft(modifier = Modifier.weight(1.2f))
                GameControlsRight(modifier = Modifier.weight(1f))
            }
        }

    }
}


@Composable
fun CountdownTimer(modifier: Modifier) {

    var timeLeftForTurn by remember { mutableStateOf(12) }
    LaunchedEffect(Unit) {
        for (i in 0 until timeLeftForTurn) {
            delay(1000)
            timeLeftForTurn--
        }
    }
    Text(
        text = when (timeLeftForTurn) {
            12, 11, 10, 9, 8, 7 -> "    $timeLeftForTurn s"
            6, 5, 4, 3, 2, 1 -> "Hurry! $timeLeftForTurn s"
            0 -> {
                "Fatality"
            }
            else -> ""
        },

        color = if (timeLeftForTurn == 0) Color.Red else retroDarkBlue,
        fontSize = 25.sp,
        modifier = modifier.padding(5.dp),
       textAlign = TextAlign.Center,
        fontFamily = playerTextFont3,
        fontWeight = FontWeight.Bold
    )
}


































