package com.entropic89.tictacno.ui.componenet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.entropic89.tictacno.ui.theme.playerTextFont3
import com.entropic89.tictacno.ui.theme.retroDarkBlue

@Composable
fun DefaultText(
    text: String,
    modifier: Modifier = Modifier,
    alpha: Float = 1f,
    fontSize: TextUnit = 16.sp
) {
    Text(
        text = text,
        color = retroDarkBlue,
        fontSize = fontSize,
        modifier = modifier
            .alpha(alpha),
        textAlign = TextAlign.Center,
        fontFamily = playerTextFont3,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun GameOverText(modifier: Modifier = Modifier){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        DefaultText(
            text =  "Hit Reset",
            alpha = 0.6f,
            modifier = Modifier.padding(5.dp)
        )

        DefaultText(
            text = "To Play Again",
            fontSize = 17.sp,
            modifier = Modifier.padding(top = 5.dp, start = 5.dp, end = 5.dp),

            )
    }
}


