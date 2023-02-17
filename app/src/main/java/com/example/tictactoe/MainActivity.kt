package com.example.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.tictactoe.Data.ControllerState
import com.example.tictactoe.Data.TileAndGameState
import com.example.tictactoe.Data.T3ViewModel
import com.example.tictactoe.Data.listOfState
import com.example.tictactoe.ui.*
import com.example.tictactoe.ui.theme.*

const val TAG = "main"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm = ViewModelProvider(this)[T3ViewModel::class.java]
            MainScreen(
                viewModel = vm,
                liveDataListOfTileAndGameStates = vm.tileAndGameState,
                controllerState = vm.controllerState,
            )
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: T3ViewModel,
    liveDataListOfTileAndGameStates: LiveData<List<TileAndGameState>?>,
    controllerState: LiveData<ControllerState>,

    ) {

    val liveBoardState = liveDataListOfTileAndGameStates.observeAsState()
    val controllerState = controllerState.observeAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(196, 196, 196))
            .drawBehind {
                drawCableUI()
            }, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(0.5f)

        ) {
            TicTacToeBoard(
                listOfTileAndGameStates = liveBoardState.value ?: listOfState,
                viewModel = viewModel,
                arrowState = controllerState
            )
        }
        Column(
            modifier = Modifier
                .weight(0.50f)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            FullController(
                arrowOnClick = { viewModel.updateArrowButtonState(direction = it) },
                actionOnClick = { viewModel.updateActionButtonState(action = it) },
                destroyButtonOnCooldown = { controllerState.value?.destroyButtonIsOnCooldown!! },
                destroyCooldownLeft = { controllerState.value?.destroyCooldownLeft ?: 0 },
                lockButtonOnCooldown = { controllerState.value?.lockButtonIsOnCooldown!! },
                lockCooldownLeft = { controllerState.value?.lockCooldownLeft ?: 0 },
                transposeButtonOnCooldown = { controllerState.value?.transposeButtonIsOnCooldown!! },
                transposeCooldownLeft = { controllerState.value?.transposeCooldownLeft ?: 0 },
            )
            OutlinedButton(
                onClick = { viewModel.resetBoard() },
                shape = CutCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    retroPurple
                ),
                elevation = ButtonDefaults.elevation(defaultElevation = 5.dp),
                border = BorderStroke(5.dp, color = Color.Black),
                modifier = Modifier.size(140.dp, 60.dp)
            ) {
                Text(
                    "Reset",
                    modifier = Modifier.padding(top = 8.dp, start = 5.dp),
                    fontSize = 16.sp,
                    color = Color.White,
                    fontFamily = playerTextFont5
                )
            }

        }
    }
}












