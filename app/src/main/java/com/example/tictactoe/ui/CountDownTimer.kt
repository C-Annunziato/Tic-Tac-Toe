package com.example.tictactoe.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.playerTextFont3
import com.example.tictactoe.ui.theme.retroDarkBlue
import kotlinx.coroutines.delay

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




