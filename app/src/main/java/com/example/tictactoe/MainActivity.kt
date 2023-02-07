package com.example.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.tictactoe.Data.TileState
import com.example.tictactoe.Data.T3ViewModel
import com.example.tictactoe.Data.TileValue
import com.example.tictactoe.Data.listOfState
import com.example.tictactoe.ui.*
import com.example.tictactoe.ui.theme.*

const val TAG = "main"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm = ViewModelProvider(this)[T3ViewModel::class.java]
            TicTacToe(viewModel = vm, liveDataListOfTileStates = vm.tileState)
        }
    }
}

@Composable
fun TicTacToe(
    modifier: Modifier = Modifier,
    viewModel: T3ViewModel,
    liveDataListOfTileStates: LiveData<List<TileState>?>
) {

    val liveBoardstate = liveDataListOfTileStates.observeAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(196, 196, 196))
            .drawBehind {
                val canvasWidth = size.width
                val canvasHeight = size.height

                val startx = canvasWidth / 2f
                val starty = canvasHeight * 0.589f

                val endx = canvasWidth / 2f
                val endy = canvasHeight * 0.40f

                val ctr1x = canvasWidth / 2 - 100f
                val ctr1y = canvasHeight * 0.49f

                val ctr2x = canvasWidth / 2 + 100f
                val xtr2y = canvasHeight * 0.48f


                val wire = Path().apply {
                    reset()
                    moveTo(x = startx, y = starty)
                    cubicTo(x1 = ctr1x, y1 = ctr1y, x2 = ctr2x, y2 = xtr2y, x3 = endx, y3 = endy)
                }

                drawPath(
                    wire, color = Color.Black, style = Stroke(
                        width = 12f,
                    )
                )

                val startxLeftWires = canvasWidth / 5.6f
                val startyLeftWires = canvasHeight * 0.37f

                val endxLeftWires = canvasWidth / 5.6f
                val endyLeftWires = canvasHeight * 0.20f

                val ctr1xLeftWires = canvasWidth / 6 - 20f
                val ctr1yLeftWires = canvasHeight * 0.33f

                val ctr2xLeftWires = canvasWidth / 6 - 70f
                val xtr2yLeftWires = canvasHeight * 0.25f


                val leftSideConnectors = Path().apply {
                    reset()
                    moveTo(
                        x = startxLeftWires, y = startyLeftWires
                    )
                    cubicTo(
                        x1 = ctr1xLeftWires,
                        y1 = ctr1yLeftWires,
                        x2 = ctr2xLeftWires,
                        y2 = xtr2yLeftWires,
                        x3 = endxLeftWires,
                        y3 = endyLeftWires
                    )
                }

                drawPath(
                    leftSideConnectors, color = Color.Black, style = Stroke(
                        width = 10f
                    )
                )


                val startxLeftWiresTop = canvasWidth / 5.6f
                val startyLeftWiresTop = canvasHeight * 0.23f

                val endxLeftWiresTop = canvasWidth / 5.6f
                val endyLeftWiresTop = canvasHeight * 0.1f

                val ctr1xLeftWiresTop = canvasWidth / 6 - 20f
                val ctr1yLeftWiresTop = canvasHeight * 0.26f

                val ctr2xLeftWiresTop = canvasWidth / 6 - 30f
                val xtr2yLeftWiresTop = canvasHeight * 0.18f


                val leftSideConnectorsTop = Path().apply {
                    reset()
                    moveTo(
                        x = startxLeftWiresTop, y = startyLeftWiresTop
                    )
                    cubicTo(
                        x1 = ctr1xLeftWiresTop,
                        y1 = ctr1yLeftWiresTop,
                        x2 = ctr2xLeftWiresTop,
                        y2 = xtr2yLeftWiresTop,
                        x3 = endxLeftWiresTop,
                        y3 = endyLeftWiresTop
                    )
                }

                drawPath(
                    leftSideConnectorsTop, color = Color.Black, style = Stroke(
                        width = 10f
                    )
                )


                val startxLeftWiresTop2 = canvasWidth / 5.6f
                val startyLeftWiresTop2 = canvasHeight * 0.28f

                val endxLeftWiresTop2 = canvasWidth / 5.6f
                val endyLeftWiresTop2 = canvasHeight * 0.15f

                val ctr1xLeftWiresTop2 = canvasWidth / 6 - 70f
                val ctr1yLeftWiresTop2 = canvasHeight * 0.26f

                val ctr2xLeftWiresTop2 = canvasWidth / 6 - 30f
                val xtr2yLeftWiresTop2 = canvasHeight * 0.18f


                val leftSideConnectorsTop2 = Path().apply {
                    reset()
                    moveTo(
                        x = startxLeftWiresTop2, y = startyLeftWiresTop2
                    )
                    cubicTo(
                        x1 = ctr1xLeftWiresTop2,
                        y1 = ctr1yLeftWiresTop2,
                        x2 = ctr2xLeftWiresTop2,
                        y2 = xtr2yLeftWiresTop2,
                        x3 = endxLeftWiresTop2,
                        y3 = endyLeftWiresTop2
                    )
                }

                drawPath(
                    leftSideConnectorsTop2, color = Color.Black, style = Stroke(
                        width = 10f
                    )
                )


                val startxRightWires = canvasWidth / 1.2f
                val startyRightWires = canvasHeight * 0.37f

                val endxRightWires = canvasWidth / 1.2f
                val endyRightWires = canvasHeight * 0.23f

                val ctr1xRightWires = canvasWidth / 1.2f + 50f
                val ctr1yRighttWires = canvasHeight * 0.33f

                val ctr2xRightWires = canvasWidth / 1.2f + 80f
                val xtr2yRightWires = canvasHeight * 0.25f


                val rightSideConnectors = Path().apply {
                    reset()
                    moveTo(
                        x = startxRightWires, y = startyRightWires
                    )
                    cubicTo(
                        x1 = ctr1xRightWires,
                        y1 = ctr1yRighttWires,
                        x2 = ctr2xRightWires,
                        y2 = xtr2yRightWires,
                        x3 = endxRightWires,
                        y3 = endyRightWires
                    )
                }

                drawPath(
                    rightSideConnectors, color = Color.Black, style = Stroke(
                        width = 10f
                    )
                )


                val startxRightWiresTop = canvasWidth / 1.2f
                val startyRightWiresTop = canvasHeight * 0.26f

                val endxRightWiresTop = canvasWidth / 1.2f
                val endyRightWiresTop = canvasHeight * 0.12f

                val ctr1xRightWiresTop = canvasWidth / 1.2f + 65f
                val ctr1yRighttWiresTop = canvasHeight * 0.23f

                val ctr2xRightWiresTop = canvasWidth / 1.2f + 25f
                val xtr2yRightWiresTop = canvasHeight * 0.16f


                val rightSideConnectorsTop = Path().apply {
                    reset()
                    moveTo(
                        x = startxRightWiresTop, y = startyRightWiresTop
                    )
                    cubicTo(
                        x1 = ctr1xRightWiresTop,
                        y1 = ctr1yRighttWiresTop,
                        x2 = ctr2xRightWiresTop,
                        y2 = xtr2yRightWiresTop,
                        x3 = endxRightWiresTop,
                        y3 = endyRightWiresTop
                    )
                }

                drawPath(
                    rightSideConnectorsTop, color = Color.Black, style = Stroke(
                        width = 10f
                    )
                )


                val startxMid1 = canvasWidth / 1.2f
                val startyMid1 = canvasHeight * 0.26f

                val endxMid1 = canvasWidth / 1.2f
                val endyMid1 = canvasHeight * 0.12f

                val ctr1xMid1 = canvasWidth / 1.2f + 65f
                val ctr1yMid1 = canvasHeight * 0.23f

                val ctr2xMid1 = canvasWidth / 1.2f + 25f
                val xtr2yMid1 = canvasHeight * 0.16f




                drawLine(
                    color = Color.Black,
                    strokeWidth = 10f,
                    start = Offset(x = canvasWidth / 2 *0.95f , y = canvasHeight * 0.35f),
                    end = Offset(x = canvasWidth / 2*0.95f , y = canvasHeight * 0.25f),
                )


                drawLine(
                    color = Color.Black,
                    strokeWidth = 10f,
                    start = Offset(x = canvasWidth / 1.89f, y = canvasHeight * 0.24f),
                    end = Offset(x = canvasWidth / 1.89f, y = canvasHeight * 0.14f),
                )


                drawLine(
                    color = Color.Black,
                    strokeWidth = 10f,
                    start = Offset(x = canvasWidth / 5, y = canvasHeight * 0.14f),
                    end = Offset(x = canvasWidth / 2f, y = canvasHeight * 0.14f),
                )
                drawLine(
                    color = Color.Black,
                    strokeWidth = 10f,
                    start = Offset(x = canvasWidth / 2, y = canvasHeight * 0.12f),
                    end = Offset(x = canvasWidth / 1.2f, y = canvasHeight * 0.12f),
                )

                drawLine(
                    color = Color.Black,
                    strokeWidth = 10f,
                    start = Offset(x = canvasWidth / 5, y = canvasHeight * 0.33f),
                    end = Offset(x = canvasWidth / 2f, y = canvasHeight * 0.33f),
                )


                drawLine(
                    color = Color.Black,
                    strokeWidth = 10f,
                    start = Offset(x = canvasWidth / 1.4f, y = canvasHeight * 0.23f),
                    end = Offset(x = canvasWidth / 1.4f, y = canvasHeight * 0.15f),
                )

                drawLine(
                    color = Color.Black,
                    strokeWidth = 10f,
                    start = Offset(x = canvasWidth / 1.30f, y = canvasHeight * 0.38f),
                    end = Offset(x = canvasWidth / 1.30f, y = canvasHeight * 0.25f),
                )



                drawLine(
                    color = Color.Black,
                    strokeWidth = 10f,
                    start = Offset(x = canvasWidth / 2, y = canvasHeight * 0.38f),
                    end = Offset(x = canvasWidth / 1.2f, y = canvasHeight * 0.38f),
                )

                drawLine(
                    color = Color.Black,
                    strokeWidth = 10f,
                    start = Offset(x = canvasWidth / 3.3f, y = canvasHeight * 0.38f),
                    end = Offset(x = canvasWidth / 3.3f, y = canvasHeight * 0.28f),
                )

                drawLine(
                    color = Color.Black,
                    strokeWidth = 10f,
                    start = Offset(x = canvasWidth / 4.2f, y = canvasHeight * 0.28f),
                    end = Offset(x = canvasWidth / 4.2f, y = canvasHeight * 0.15f),
                )







            }, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = modifier.weight(0.5f)
        ) {
            Board(
                listOfTileStates = liveBoardstate.value ?: listOfState, viewModel = viewModel
            )
        }
        Row(
            modifier = Modifier
                .weight(0.4f)
                .padding(8.dp)

        ) {
            FullController()
        }
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(.1f)
        ) {
            OutlinedButton(
                onClick = { /*TODO*/ },
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

@Composable
fun Board(
    modifier: Modifier = Modifier, listOfTileStates: List<TileState>?, viewModel: T3ViewModel
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize(),
    ) {

        Text(
            "Complete a row, diagonal or column",
            fontFamily = playerTextFont4,
            color = Color.DarkGray
        )
        for (i in 1..3) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.padding(10.dp)
            ) {
                for (j in 1..3) {
                    val currentIndex = (i - 1) * 3 + (j - 1)

                    listOfTileStates?.getOrNull(currentIndex).let { tileState ->
                        Tile(
                            onChooseTile = { bool ->
                                //only flip state if the tile is not occupied
                                if (!tileState?.tileIsOccupied!!) {
                                    viewModel.updatePlayerState(
                                        listOfStateIndex = currentIndex, bool = bool
                                    )
                                }
                            }, state = tileState, currentIndex = currentIndex, viewModel = viewModel
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier.width(420.dp), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)
            ) {
                Text(
                    "${if (listOfTileStates?.first()?.isPlayer1Turn == true) "Player 1" else "Player 2"}",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = playerTextFont3,
                    color = Color.Blue,
                    modifier = Modifier
                        .padding(5.dp)
                        .alpha(alpha = 0.40f)

                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)
            ) {

                CountdownTimer()
            }
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Tile(
    modifier: Modifier = Modifier,
    onChooseTile: (Boolean) -> Unit,
    state: TileState?,
    currentIndex: Int,
    viewModel: T3ViewModel,
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Card(modifier = Modifier
            .border(
                4.dp, Color.Black, shape = RoundedCornerShape(8.dp)
            )
            .size(80.dp)
            .clickable(onClick = {
                when (state?.isPlayer1Turn) {
                    true -> onChooseTile(true)
                    false -> onChooseTile(false)
                }
            })
            .drawBehind {
                drawRoundRect(
                    color = Color.Black,
                    size = Size(width = 84.dp.toPx(), height = 84.dp.toPx()),
                    cornerRadius = CornerRadius(x = 30f, y = 30f)
                )
            },
            elevation = 5.dp,
            shape = RoundedCornerShape(8.dp),
            backgroundColor = retroNearWhite
        ) {
            AnimatedVisibility(
                visible = viewModel.tileState.value?.get(currentIndex)?.currentTileSymbolState != TileValue.NONE,
                enter = scaleIn(tween(150))
            ) {
                when (state?.currentTileSymbolState?.ordinal) {
                    TileValue.NONE.ordinal -> {}
                    TileValue.CROSS.ordinal -> XX()
                    TileValue.CIRCLE.ordinal -> CircleOfSquares()
                }
            }
        }
    }
}

@Preview
@Composable

fun Preview() {
}














