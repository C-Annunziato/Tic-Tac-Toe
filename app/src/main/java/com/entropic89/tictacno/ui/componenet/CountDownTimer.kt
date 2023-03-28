package com.entropic89.tictacno.ui.componenet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

const val log = "timer"
@Composable

fun CountdownTimer(
    modifier: Modifier, turnOver: () -> Unit, gameIsComplete: Boolean = false
) {

    val time by produceState(initialValue = 5) {
        while (value > 0) {
            delay(1.seconds)
            value = value.minus(1)
        }
    }



    LaunchedEffect(key1 = time){
        if (time == 0) {
            delay(1.seconds)
            turnOver()
        }
    }

    if(gameIsComplete){
       GameOverText(modifier = modifier)
    } else {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DefaultText(
                text =  "T I M E",
                alpha = 0.6f,
                modifier = Modifier.padding(5.dp)
            )
            DefaultText(
                text = "$time",
                fontSize = 17.sp,
                modifier = Modifier.padding(top = 5.dp, start = 5.dp, end = 5.dp),
                )
        }
    }
}






