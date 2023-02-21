package com.example.tictactoe.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.playerTextFont3
import com.example.tictactoe.ui.theme.retroDarkBlue
import com.example.tictactoe.ui.theme.retroRed
import kotlinx.coroutines.delay

@Composable

fun CountdownTimer(modifier: Modifier) {

    var timeLeftForTurn by remember { mutableStateOf(10) }
    LaunchedEffect(Unit) {
        for (i in 0 until timeLeftForTurn) {
            delay(1000)
            timeLeftForTurn--
        }
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(
            text = when (timeLeftForTurn) {
                12, 11, 10, 9, 8, 7 -> "T I M E"
                6, 5, 4, 3, 2, 1 -> "T I M E"
                0 -> {
                    "Insert Coins"
                }
                else -> ""
            },

            color = retroDarkBlue,
            fontSize = 15.sp,
            modifier = Modifier.padding(5.dp) .alpha(alpha = if(timeLeftForTurn == 0) 0.8f else 0.4f),
            textAlign = TextAlign.Center,
            fontFamily = playerTextFont3,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = when (timeLeftForTurn) {
                12, 11, 10, 9, 8, 7 -> "$timeLeftForTurn"
                6, 5, 4, 3, 2, 1 -> "$timeLeftForTurn"
                0 -> {
                    "To Play Again"
                }
                else -> ""
            },

            color = retroDarkBlue,
            fontSize = 17.sp,
            modifier = Modifier.padding(top = 5.dp, start = 5.dp, end = 5.dp),
            textAlign = TextAlign.Center,
            fontFamily = playerTextFont3,
            fontWeight = FontWeight.Bold
        )
    }

}




