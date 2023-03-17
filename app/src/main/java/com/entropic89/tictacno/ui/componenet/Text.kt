package com.entropic89.tictacno.ui.componenet

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
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


