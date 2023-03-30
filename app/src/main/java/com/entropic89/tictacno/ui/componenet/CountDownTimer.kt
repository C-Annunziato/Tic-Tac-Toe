package com.entropic89.tictacno.ui.componenet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.entropic89.tictacno.ui.theme.playerTextFont3
import com.entropic89.tictacno.ui.theme.retroDarkBlue
import kotlinx.coroutines.delay

@Composable

fun CountdownTimer(modifier: Modifier, turnOver: () -> Unit, gameIsComplete: Boolean = false) {

    var timeLeftForTurn by remember { mutableStateOf(20) }
    LaunchedEffect(Unit) {
        for (i in 0 until timeLeftForTurn + 1) {
            delay(1000)
            timeLeftForTurn--
        }
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        if (gameIsComplete) {
            timeLeftForTurn = -1
        }

        Text(
            text = when (timeLeftForTurn) {
                20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7 -> "T I M E"
                6, 5, 4, 3, 2, 1, 0 -> "T I M E"
                -1 -> {
                    "Hit Reset"
                }
                else -> ""
            },

            color = retroDarkBlue,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(5.dp)
                .alpha(alpha = if (timeLeftForTurn == 0) 0.8f else 0.6f),
            textAlign = TextAlign.Center,
            fontFamily = playerTextFont3,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = when (timeLeftForTurn) {
                20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7 -> "$timeLeftForTurn"
                6, 5, 4, 3, 2, 1, 0 -> "$timeLeftForTurn"
                -1 -> {
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


    if (timeLeftForTurn == 0) {
        turnOver()
    }


}




