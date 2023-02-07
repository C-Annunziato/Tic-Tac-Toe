package com.example.tictactoe.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Stream
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.*
import kotlinx.coroutines.delay


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
////            colors = ButtonDefaults.buttonColors(backgroundColor = Materialtheme.colors.secondaryVariant)
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
    Column(
        modifier = modifier
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(
                onClick = { },
                shape = CircleShape,
                modifier = Modifier
                    .size(55.dp)
                    .shadow(2.dp, shape = CircleShape),
                colors = ButtonDefaults.buttonColors(
                    retroControllerButtons
                ),
                border = BorderStroke(2.dp, color = Color.Gray)
            ) {
                Icon(
                    Icons.Filled.PlayArrow,
                    contentDescription = "left arrow",
                    modifier = Modifier
                        .size(50.dp)
                        .rotate(180f)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {

                Button(
                    onClick = { },
                    shape = CircleShape,
                    modifier = Modifier
                        .size(55.dp)
                        .shadow(2.dp, shape = CircleShape),
                    colors = ButtonDefaults.buttonColors(
                        retroControllerButtons
                    ),
                    border = BorderStroke(2.dp, color = Color.Gray)
                ) {
                    Icon(
                        Icons.Filled.PlayArrow,
                        contentDescription = "up arrow",
                        modifier = Modifier
                            .size(50.dp)
                            .rotate(-90f)
                    )
                }

                Button(
                    onClick = { },
                    shape = CircleShape,
                    modifier = Modifier
                        .size(55.dp)
                        .shadow(2.dp, shape = CircleShape),
                    colors = ButtonDefaults.buttonColors(
                        retroControllerButtons
                    ),
                    border = BorderStroke(2.dp, color = Color.Gray)
                ) {
                    Icon(
                        Icons.Filled.PlayArrow,
                        contentDescription = "down arrow",
                        modifier = Modifier
                            .size(50.dp)
                            .rotate(90f)
                    )
                }
            }
            Button(
                onClick = { },
                shape = CircleShape,
                modifier = Modifier
                    .size(55.dp)
                    .shadow(2.dp, shape = CircleShape),
                colors = ButtonDefaults.buttonColors(
                    retroControllerButtons
                ),
                border = BorderStroke(2.dp, color = Color.Gray)
            ) {
                Icon(
                    Icons.Filled.PlayArrow,
                    contentDescription = "right arrow",
                    modifier = Modifier.size(50.dp)
                )
            }

        }
    }
}


@Composable
fun GameControlsRight(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,

    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)){
                Button(
                    onClick = { },
                    shape = CircleShape,
                    modifier = Modifier
                        .size(62.dp)
                        .shadow(2.dp, shape = CircleShape),
                    colors = ButtonDefaults.buttonColors(
                        retroControllerButtons
                    ),
                    border = BorderStroke(2.dp, color = Color.Gray),
                ) {
                    Icon(
                        Icons.Filled.AdsClick,
                        contentDescription = "rewind",
                        modifier = Modifier.size(50.dp)
                    )
                }

                Button(
                    onClick = { },
                    shape = CircleShape,
                    modifier = Modifier
                        .size(62.dp)
                        .shadow(2.dp, shape = CircleShape),
                    colors = ButtonDefaults.buttonColors(
                        retroControllerButtons
                    ),
                    border = BorderStroke(2.dp, color = Color.Gray),
                ) {
                    Icon(
                        Icons.Filled.Bolt,
                        contentDescription = "rewind",
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Button(
                    onClick = { },
                    shape = CircleShape,
                    modifier = Modifier
                        .size(62.dp)
                        .shadow(2.dp, shape = CircleShape),
                    colors = ButtonDefaults.buttonColors(
                        retroControllerButtons
                    ),
                    border = BorderStroke(2.dp, color = Color.Gray)
                ) {
                    Icon(
                        Icons.Filled.Lock,
                        contentDescription = "remove",
                        modifier = Modifier.size(50.dp)
                    )
                }
                TextButton(
                    onClick = { },
                    shape = CircleShape,
                    modifier = Modifier
                        .size(62.dp)
                        .shadow(2.dp, shape = CircleShape),
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
}


@Composable
fun FullController() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .clip(CircleShape),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .border(width = 5.dp, color = Color.Black, shape = CircleShape)
                .shadow(5.dp, shape = CircleShape),
            elevation = 5.dp,
            color = retroDarkGrey
        ) {
                Row(
                    modifier = Modifier.padding(start = 35.dp, end = 35.dp, top = 25.dp, bottom = 25.dp)
                ) {
                    GameControlsLeft(modifier = Modifier.weight(1f))
                    GameControlsRight(modifier = Modifier.weight(1f))
                }
            }
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
                12, 11, 10, 9, 8, 7 -> "$timeLeftForTurn s"
                6, 5, 4, 3, 2, 1 -> "Hurry! $timeLeftForTurn s"
                0 -> {
                    "Fatality"
                }
                else -> ""
            },

            color = if (timeLeftForTurn == 0) Color.Red else retroDarkBlue,
            fontSize = 25.sp,
            modifier = Modifier.padding(5.dp),
            fontFamily = playerTextFont3,
            fontWeight = FontWeight.Bold
        )
    }

































