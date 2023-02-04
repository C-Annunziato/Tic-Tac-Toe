package com.example.tictactoe.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.TAG
import com.example.tictactoe.ui.theme.*
import kotlinx.coroutines.delay
import kotlin.time.ExperimentalTime
import kotlin.time.seconds


//@Composable
//fun GameControlsLeft(modifier: Modifier = Modifier) {
//    Column(
//        modifier = modifier.padding(start = 10.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        Button(
//            onClick = { }, shape = CircleShape, modifier = Modifier.size(80.dp)
//        ) {
//            Icon(
//                Icons.Filled.Refresh, contentDescription = "rewind", modifier = Modifier.size(50.dp)
//            )
//        }
//
//        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
//            Button(
//                onClick = { },
//                shape = CircleShape,
//                modifier = Modifier.size(80.dp),
////            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondaryVariant)
//            ) {
//                ClockIcon()
//            }
//            Button(
//                onClick = { }, shape = CircleShape, modifier = Modifier.size(80.dp)
//            ) {
//                Icon(
//                    Icons.Filled.Clear,
//                    contentDescription = "remove",
//                    modifier = Modifier.size(50.dp)
//                )
//            }
//        }
//        Text("Player 1", fontSize = 30.sp)
//    }
//}

@Composable
fun GameControlsLeft(modifier: Modifier = Modifier) {
    Canvas(
        modifier = Modifier
            .size(80.dp)
            .padding(10.dp)
    ) {

        val width = size.width
        val height = size.height

        val centerX = width / 2
        val centerY = height / 2
        val radius = width / 4


    }
}


@Composable
fun GameControlsRight(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(end = 10.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { },
            shape = CircleShape,
            modifier = Modifier.size(67.dp),
            colors = ButtonDefaults.buttonColors(
                retroControllerButtons
            ),
            border = BorderStroke(2.dp, color = Color.Gray)
        ) {
            Icon(
                Icons.Filled.Refresh, contentDescription = "rewind", modifier = Modifier.size(50.dp)
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Button(
                onClick = { },
                shape = CircleShape,
                modifier = Modifier.size(67.dp),
                colors = ButtonDefaults.buttonColors(
                    retroControllerButtons
                ),
                border = BorderStroke(2.dp, color = Color.Gray)
            ) {
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = "remove",
                    modifier = Modifier.size(50.dp)
                )
            }
            TextButton(
                onClick = { },
                shape = CircleShape,
                modifier = Modifier.size(67.dp),
                colors = ButtonDefaults.buttonColors(
                    retroControllerButtons
                ),
                border = BorderStroke(2.dp, color = Color.Gray)
            ) {
                Text("?", fontSize = 30.sp)
            }
        }
    }
}


@Composable
fun FullController() {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row() {
            GameControlsLeft(modifier = Modifier.weight(1f))
            GameControlsRight(modifier = Modifier.weight(1f))
        }
        CountdownTimer()
    }
}


@Composable
fun CountdownTimer() {

    var timeLeftForTurn by remember { mutableStateOf(12) }
    LaunchedEffect(Unit) {
        for (i in 0 until timeLeftForTurn) {
            delay(1000)
            timeLeftForTurn--
        }
    }

    Text(
        text = when (timeLeftForTurn) {
            12, 11, 10, 9, 8, 7 -> "Time $timeLeftForTurn s"
            6, 5, 4, 3, 2, 1 -> "Hurry! $timeLeftForTurn s"
            else -> {
                "Fatality"
            }
        },

        color = retroDarkBlue,
        fontSize = 25.sp,
        modifier = Modifier.padding(top = 50.dp),
        fontFamily = playerTextFont5
    )

}
































