package com.example.tictactoe.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.TAG


@Composable
fun GameControlsLeft(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(start = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = { }, shape = CircleShape, modifier = Modifier.size(80.dp)
        ) {
            Icon(
                Icons.Filled.Refresh, contentDescription = "rewind", modifier = Modifier.size(50.dp)
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            Button(
                onClick = { },
                shape = CircleShape,
                modifier = Modifier.size(80.dp),
//            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondaryVariant)
            ) {
                ClockIcon()
            }
            Button(
                onClick = { }, shape = CircleShape, modifier = Modifier.size(80.dp)
            ) {
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = "remove",
                    modifier = Modifier.size(50.dp)
                )
            }
        }
        Text("Player 1", fontSize = 30.sp)
    }
}


@Composable
fun GameControlsRight(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(end = 10.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { }, shape = CircleShape, modifier = Modifier.size(80.dp)
        ) {
            Icon(
                Icons.Filled.Refresh, contentDescription = "rewind", modifier = Modifier.size(50.dp)
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            Button(
                onClick = { }, shape = CircleShape, modifier = Modifier.size(80.dp)
            ) {
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = "remove",
                    modifier = Modifier.size(50.dp)
                )
            }
            Button(
                onClick = { },
                shape = CircleShape,
                modifier = Modifier.size(80.dp),
//            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondaryVariant)
            ) {
                ClockIcon()
            }
        }
        Text("Player 2", fontSize = 30.sp)
    }
}
