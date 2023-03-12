package com.entropic89.tictacno.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.entropic89.tictacno.ui.theme.retroControllerButtons

@Composable
fun ArrowButton(onClick: () -> Unit, iconRotation: Float){
    Button(
        onClick = { onClick()},
        shape = CircleShape,
        modifier = Modifier
            .offset((-5).dp)
            .size(55.dp)
            .shadow(2.dp, shape = CircleShape),
        colors = ButtonDefaults.buttonColors(
            retroControllerButtons
        ),
        border = BorderStroke(2.dp, color = Color.Gray),
    ) {
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = "directional arrow",
            modifier = Modifier
                .size(50.dp)
                .rotate(iconRotation)
        )
    }
}

@Composable
fun ActionButtons(onClick: () -> Unit, icon: ImageVector, borderColor: Color){
    Button(
        onClick = {onClick()},
        shape = CircleShape,
        modifier = Modifier
            .size(58.dp)
            .shadow(2.dp, shape = CircleShape),
        colors = ButtonDefaults.buttonColors(
            retroControllerButtons
        ),
        border = BorderStroke(3.dp, color = borderColor)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "action button",
            modifier = Modifier
                .size(50.dp)
        )
    }
}

@Composable
fun DeadButton (onCooldown: Int, modifier: Modifier =Modifier){
    Button(
        onClick = { },
        shape = CircleShape,
        modifier = Modifier
            .size(60.dp)
            .shadow(2.dp, shape = CircleShape),
        colors = ButtonDefaults.buttonColors(
            retroControllerButtons
        ),
        border = BorderStroke(2.dp, color = Color.Gray)
    ) {
            Text("$onCooldown")
    }
}














