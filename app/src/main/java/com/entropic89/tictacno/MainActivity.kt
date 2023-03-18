package com.entropic89.tictacno

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cached
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.entropic89.tictacno.ui.componenet.DrawerContent
import com.entropic89.tictacno.ui.componenet.FullController
import com.entropic89.tictacno.ui.componenet.TicTacToeBoard
import com.entropic89.tictacno.ui.componenet.drawCableUI
import com.entropic89.tictacno.ui.model.ControllerState
import com.entropic89.tictacno.ui.model.TileAndGameState
import com.entropic89.tictacno.ui.model.listOfState
import com.entropic89.tictacno.ui.theme.retroAppBarColor
import com.entropic89.tictacno.ui.theme.retroGrey
import com.entropic89.tictacno.ui.theme.retroNearWhite
import com.entropic89.tictacno.ui.viewmodel.T3ViewModel
import kotlinx.coroutines.launch

const val TAG = "main"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm = ViewModelProvider(this)[T3ViewModel::class.java]
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            var rotateScreen180 by remember { mutableStateOf(false) }

            Scaffold(modifier = if (rotateScreen180) Modifier.rotate(180f) else Modifier,
                scaffoldState = scaffoldState,
                topBar = {
                    AppBar(countDownOff = { vm.disableCountDown(it) }, rotateScreen180 = {
                        rotateScreen180 = !rotateScreen180
                    }) { scope.launch { scaffoldState.drawerState.apply { if (isClosed) open() else close() } } }
                },
                drawerContent = { DrawerContent() }) {
                MainScreen(
                    viewModel = vm,
                    liveDataListOfTileAndGameStates = vm.tileAndGameState,
                    controllerState = vm.controllerState,
                )
            }
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

    val tileAndGameState = liveDataListOfTileAndGameStates.observeAsState()
    val controllerState = controllerState.observeAsState()
    val player by viewModel.gameState.currentPlayer.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(196, 196, 196))
            .drawBehind {
                drawCableUI()
            }, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(0.55f)
        ) {
            TicTacToeBoard(listOfTileAndGameStates = tileAndGameState.value ?: listOfState,
                viewModel = viewModel,
                turnOver = { viewModel.outOfTime() })
        }
        FullController(
            modifier = Modifier.weight(0.45f),
            onUpdateActionButtonClick = viewModel::updateActionButtonState,
            onUpdateArrowButtonClick = viewModel::updateArrowButtonState,
            onResetBoard = viewModel::resetBoard,
            player = player
        )
    }
}


@Composable
fun AppBar(
    countDownOff: (Boolean) -> Unit, rotateScreen180: () -> Unit, scaffoldState: () -> Unit,
) {

    var expandedMenu by remember { mutableStateOf(false) }
    var switchState by remember { mutableStateOf(false) }

    TopAppBar(title = {
        Text("Tic Tac No", color = Color.White)
    }, actions = {


        IconButton(onClick = rotateScreen180) {
            Icon(
                imageVector = Icons.Filled.Cached,
                contentDescription = "rotate screen 180",
                tint = Color.White
            )
        }

        IconButton(onClick = scaffoldState) {
            Icon(
                imageVector = Icons.Filled.Help,
                contentDescription = "game rules",
                tint = Color.White,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp)
            )
        }
        IconButton(
            onClick = { expandedMenu = !expandedMenu }, Modifier.padding(end = 10.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "drop down menu",
                tint = Color.White
            )
        }


    }, backgroundColor = retroAppBarColor, elevation = 2.dp)

    MaterialTheme(colors = MaterialTheme.colors.copy(surface = retroNearWhite)) {

        DropdownMenu(
            expanded = expandedMenu,
            onDismissRequest = { expandedMenu = false },
            offset = DpOffset(x = (-50).dp, y = (-118).dp),
        ) {
            DropdownMenuItem(onClick = { }) {
                Text("Disable Timer", color = Color.Black)
                Switch(
                    checked = switchState,
                    onCheckedChange = {
                        switchState = it
                        if (switchState) {
                            countDownOff(true)
                        } else {
                            countDownOff(false)
                        }
                    },
                    modifier = Modifier.padding(start = 20.dp),
                    colors = SwitchDefaults.colors(uncheckedThumbColor = retroGrey)
                )
            }
        }
    }
}











