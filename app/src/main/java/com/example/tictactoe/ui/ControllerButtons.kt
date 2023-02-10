package com.example.tictactoe.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.tictactoe.ui.theme.retroControllerButtons

@Composable
fun ArrowButton(onClick: () -> Unit, iconRotation: Float){
    Button(
        onClick = { onClick()},
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
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = "directional arrow",
            modifier = Modifier
                .size(50.dp)
                .rotate(iconRotation)
        )
    }
}

@Composable
fun ActionButtons(onClick: () -> Unit, icon: ImageVector){
    Button(
        onClick = { onClick()},
        shape = CircleShape,
        modifier = Modifier
            .size(60.dp)
            .shadow(2.dp, shape = CircleShape),
        colors = ButtonDefaults.buttonColors(
            retroControllerButtons
        ),
        border = BorderStroke(2.dp, color = Color.Gray)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "action button",
            modifier = Modifier
                .size(50.dp)
        )
    }
}



















