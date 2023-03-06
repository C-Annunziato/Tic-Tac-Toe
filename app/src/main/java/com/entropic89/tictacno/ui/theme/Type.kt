package com.entropic89.tictacno.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.entropic89.tictacno.R

val playerTextFont1 = FontFamily(Font(R.font.inide_flower_reg))
val playerTextFont2 = FontFamily(Font(R.font.evil_empire_reg))
val playerTextFont3 = FontFamily(Font(R.font.orbitron_reg))
val playerTextFont4 = FontFamily(Font(R.font.op_mono_med_))
val playerTextFont5 = FontFamily(Font(R.font.press_start_reg))
// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.inide_flower_reg)),
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    )


    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

