package com.entropic89.tictacno.ui.componenet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdsClick
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.entropic89.tictacno.ui.theme.playerTextFont4
import com.entropic89.tictacno.ui.theme.retroGrey


@Composable
fun DrawerContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(
                        retroGrey, Color(139, 137, 137, 255)
                    )
                )
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            "RULES:",
            fontSize = 26.sp,
            fontFamily = playerTextFont4,
            textAlign = TextAlign.Start,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 40.dp, top = 20.dp)
        )
        Text(
            text = "\u2022 Complete a row diagonal or column.\n\n\u2022 You can transpose to win.\n\n\u2022 Lock is immune to all actions.",
            fontSize = 19.sp,
            fontFamily = playerTextFont4,
            textAlign = TextAlign.Start,
            color = Color.White,
            modifier = Modifier.padding(start = 40.dp, end = 30.dp)

        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "\u2022 Actions have cooldowns like ",
                fontSize = 19.sp,
                fontFamily = playerTextFont4,
                textAlign = TextAlign.Start,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 40.dp)
                    .weight(1.8f)
            )
            Box(
                modifier = Modifier
                    .padding(start = 30.dp, top = 10.dp)
                    .weight(1f)
            ) {
                DeadButton(onCooldown = 2)
            }
        }
        Text(
            "ACTIONS:",
            fontSize = 26.sp,
            fontFamily = playerTextFont4,
            textAlign = TextAlign.Start,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 40.dp)
        )
        Column(
            modifier = Modifier.padding(end = 30.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.padding(start = 40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.AdsClick,
                    contentDescription = "",
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )
                Text(
                    "Place an X or O",
                    modifier = Modifier.padding(start = 20.dp),
                    fontSize = 19.sp,
                    color = Color.White,
                    fontFamily = playerTextFont4
                )
            }

            Row(
                modifier = Modifier.padding(start = 40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Bolt,
                    contentDescription = "",
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )
                Text(
                    "Destroy tiles",
                    modifier = Modifier.padding(start = 20.dp),
                    fontSize = 19.sp,
                    color = Color.White,
                    fontFamily = playerTextFont4
                )
            }

            Row(
                modifier = Modifier.padding(start = 40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "",
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )
                Text(
                    "Lock a tile",
                    modifier = Modifier.padding(start = 20.dp),
                    fontSize = 19.sp,
                    color = Color.White,
                    fontFamily = playerTextFont4
                )
            }

            Row(
                modifier = Modifier.padding(start = 40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Shuffle,
                    contentDescription = "",
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )
                Text(
                    "Transpose symbols",
                    modifier = Modifier.padding(start = 20.dp),
                    fontSize = 19.sp,
                    color = Color.White,
                    fontFamily = playerTextFont4
                )
            }
        }
    }
}
