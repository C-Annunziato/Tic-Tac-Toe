package com.example.tictactoe.Data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.ceil

const val TAG = "viewmodel"

class T3ViewModel : ViewModel() {

    private val _tileAndGameState = MutableLiveData(listOfState)
    val tileAndGameState: LiveData<List<TileAndGameState>?> = _tileAndGameState

    private val _arrowButtonState = MutableLiveData(ControllerState())
    val arrowButtonState: LiveData<ControllerState> = _arrowButtonState

    private var _currentTileIndex = MutableLiveData(0)
    val currentTileIndex: LiveData<Int> = _currentTileIndex


    private var currentRow: Int by mutableStateOf(0)
    private var currentColumn: Int by mutableStateOf(0)
    private var numColumns: Int = 3
    private var numRows: Int = 3

    //init to middle position
    private var _position: Int by mutableStateOf(0)

    //    private var position: Int by mutableStateOf(_position)
    private var position: Int
        get() = _position
        set(value) {
            _position = value
        }


//        .coerceIn(0 until (numRows * numColumns)))

    init {
        initToBoardMiddle()
        Log.i(TAG, "position is $_position, row is $currentRow, columns is $currentColumn")
    }

    fun updatePlayerState(listOfStateIndex: Int, bool: Boolean) {

        //global list items change
        _currentTileIndex.value = listOfStateIndex
        _tileAndGameState.value = _tileAndGameState.value?.map { tileState ->
            tileState.copy(isPlayer1Turn = !bool)
        }

        //specific list item change
        _tileAndGameState.value = _tileAndGameState.value?.mapIndexed { index, tileState ->
            if (listOfStateIndex == index && tileState.isPlayer1Turn) {
                tileState.copy(symbolInTile = TileValue.CROSS, tileIsOccupied = true)
            } else if (listOfStateIndex == index && !tileState.isPlayer1Turn) {
                tileState.copy(symbolInTile = TileValue.CIRCLE, tileIsOccupied = true)
                //retain the state
            } else tileState
        }
    }

    fun updateActionButtonState(action: Action) {
        tileAndGameState.value?.getOrNull(position)?.let { tileState ->
            if (!tileState.tileIsOccupied) {
                when (action) {
                    Action.PLACE -> {
                        _tileAndGameState.value = _tileAndGameState.value?.map { tileState ->
                            tileState.copy(isPlayer1Turn = !tileState.isPlayer1Turn)
                        }
                        _tileAndGameState.value =
                            _tileAndGameState.value?.mapIndexed { index, tileState ->
                                if (_position == index && tileState.isPlayer1Turn) {
                                    tileState.copy(
                                        symbolInTile = TileValue.CROSS, tileIsOccupied = true
                                    )

                                } else if (_position == index && !tileState.isPlayer1Turn) {
                                    tileState.copy(
                                        symbolInTile = TileValue.CIRCLE, tileIsOccupied = true
                                    )
                                    //retain the state
                                } else tileState

                            }
                        checkForVictory(TileValue.CROSS)
                        checkForVictory(TileValue.CIRCLE)
                    }
                }
            }
        }
    }

    private fun getTileValue(index: Int): TileValue? {
        return tileAndGameState.value?.get(index)?.symbolInTile
    }

    private fun checkForVictory(tileValue: TileValue): Boolean {
        when {
            getTileValue(0) == tileValue && getTileValue(1) == tileValue && getTileValue(2) == tileValue -> {
                _tileAndGameState.value = _tileAndGameState.value?.map { tileState ->
                    tileState.copy(
                        victoryType = VictoryType.HORIZONTALLINE1,
                        winningIndexes = Triple(0, 1, 2)
                    )
                }
                _tileAndGameState.value = _tileAndGameState.value?.mapIndexed { index, tileState ->
                    if (index == tileState.winningIndexes.first || index == tileState.winningIndexes.second || index == tileState.winningIndexes.third) {
                        tileState.copy(
                            symbolInTile = TileValue.STAR, victoryType = VictoryType.HORIZONTALLINE1
                        )
                    } else tileState
                }
                return true
            }
        }
        return false
    }

    private fun victoryScreen() {

        _tileAndGameState.value = _tileAndGameState.value?.mapIndexed { index, tileState ->
            if (_position == index && tileState.isPlayer1Turn) {
                tileState.copy(
                    symbolInTile = TileValue.STAR, tileIsOccupied = true
                )
            } else tileState
        }

    }

    fun updateArrowButtonState(direction: Direction) {

        when (direction) {
            Direction.UP -> _arrowButtonState.value =
                _arrowButtonState.value?.copy(arrowState = Direction.UP)
            Direction.DOWN -> _arrowButtonState.value =
                _arrowButtonState.value?.copy(arrowState = Direction.DOWN)
            Direction.LEFT -> _arrowButtonState.value =
                _arrowButtonState.value?.copy(arrowState = Direction.LEFT)
            Direction.RIGHT -> _arrowButtonState.value =
                _arrowButtonState.value?.copy(arrowState = Direction.RIGHT)
        }
        removePriorSelection()

        moveOnBoard(numRows, numColumns)
    }


    private fun removePriorSelection() {
        _tileAndGameState.value = _tileAndGameState.value?.map { tileState ->
            tileState.copy(isSelected = false)
        }
    }


    private fun moveOnBoard(numOfRows: Int, numOfColumns: Int) {

        //track current row and column to get a position
        //if rows and columns are even then your position is a simple multiplication of the row and column your are on
        //else if they are uneven, then you need a correction term which is abs(rows-columns) * rows
        //the correction term does not apply if you only have one row, hence if rows > 1
//        position = currentRow * currentColumn

//            if (numOfRows > 1) currentRow * currentColumns + (abs(numOfRows - numOfColumns) * numOfRows)
//            else currentRow * currentColumns

        //if current row is not an edge

        when (arrowButtonState.value?.arrowState) {
            Direction.UP -> {
                //can move up
                if (currentRow > 1) {
                    _tileAndGameState.value =
                        _tileAndGameState.value?.mapIndexed { index, tileState ->
                            //moving up
                            if ((_position - numOfRows) == index) {

                                tileState.copy(isSelected = true)
                            } else tileState
                        }
                    _position -= numOfRows
                    currentRow -= 1

                } else if (currentRow == 1) {
                    _tileAndGameState.value =
                        _tileAndGameState.value?.mapIndexed { index, tileState ->
                            //moving up
                            if (_position == index) {
                                tileState.copy(isSelected = true)
                            } else tileState
                        }
                }
            }

            Direction.DOWN -> {
                if (currentRow < numOfRows) {
                    _tileAndGameState.value =
                        _tileAndGameState.value?.mapIndexed { index, tileState ->
                            if ((_position + numOfRows) == index) {

                                tileState.copy(isSelected = true)
                            } else tileState
                        }
                    _position += numOfRows
                    currentRow += 1
                } else if (currentRow == 3) {
                    _tileAndGameState.value =
                        _tileAndGameState.value?.mapIndexed { index, tileState ->
                            //moving up
                            if (_position == index) {
                                tileState.copy(isSelected = true)
                            } else tileState
                        }
                }
            }
            Direction.LEFT -> {
                if (currentColumn > 1) {
                    _tileAndGameState.value =
                        _tileAndGameState.value?.mapIndexed { index, tileState ->
                            if (_position - 1 == index) {
                                tileState.copy(isSelected = true)
                            } else tileState
                        }
                    _position -= 1
                    currentColumn -= 1
                } else if (currentColumn == 1) {
                    _tileAndGameState.value =
                        _tileAndGameState.value?.mapIndexed { index, tileState ->
                            //moving up
                            if (_position == index) {
                                tileState.copy(isSelected = true)
                            } else tileState
                        }
                }

            }
            Direction.RIGHT -> {
                if (currentColumn < numOfColumns) {
                    _tileAndGameState.value =
                        _tileAndGameState.value?.mapIndexed { index, tileState ->
                            if (_position + 1 == index) {
                                tileState.copy(isSelected = true)
                            } else tileState
                        }
                    _position += 1
                    currentColumn += 1

                } else if (currentColumn == 3) {
                    _tileAndGameState.value =
                        _tileAndGameState.value?.mapIndexed { index, tileState ->
                            //moving up
                            if (_position == index) {
                                tileState.copy(isSelected = true)
                            } else tileState
                        }
                }
            }
        }
        Log.i(TAG, "position is $_position, row is $currentRow, columns is $currentColumn")
    }


//viewmodelscope.launch


    fun resetBoard() {

        _tileAndGameState.value = _tileAndGameState.value?.map { tileState ->
            tileState.copy(
                isPlayer1Turn = true,
                tileIsOccupied = false,
                symbolInTile = TileValue.NONE,
                isSelected = false,
                isSelectedIndex = returnMiddleOfBoard()
            )
        }

        _tileAndGameState.value = _tileAndGameState.value?.mapIndexed { index, tileState ->
            if (index == returnMiddleOfBoard()) {
                tileState.copy(isSelected = true)
            } else tileState
        }

        _arrowButtonState.value = _arrowButtonState.value?.copy(
            arrowState = Direction.NONE, actionState = Action.NONE
        )

        initToBoardMiddle()
    }


    private fun returnMiddleOfBoard(): Int {
        val midRow = ceil((numRows.toDouble() / 2)).toInt()
        val midColumn = ceil((numColumns.toDouble() / 2)).toInt()
        currentRow = midRow
        currentColumn = midColumn
        Log.i(TAG, " calc middle of board ${midRow * midColumn}")
        return midRow * midColumn
    }

    private fun initToBoardMiddle() {
        //find the middle of a square grid
        //avoid integer division via toDouble cast

        _position = returnMiddleOfBoard()

        Log.i(
            TAG,
            " init or reset board :: position $_position, row $currentRow, column $currentColumn"
        )

        _tileAndGameState.value = _tileAndGameState.value?.mapIndexed { index, tileState ->
            if (index == _position) {
                tileState.copy(isSelected = true)
            } else tileState
        }
    }
}











