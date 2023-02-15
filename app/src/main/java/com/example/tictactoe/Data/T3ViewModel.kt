package com.example.tictactoe.Data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.ceil
import kotlin.random.Random

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
                when (action) {
                    Action.PLACE -> {
                        if (!tileState.tileIsOccupied) {
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
                    Action.DESTROY -> {
                        destroyRandomTiles()
                    }
                    else -> {}
                }
        }
    }

    private fun getTileValue(index: Int): TileValue? {
        return tileAndGameState.value?.get(index)?.symbolInTile
    }

    private fun updateVictoryState(first: Int, second: Int, third: Int) {
        //specific update of Tile to produce a Star
        _tileAndGameState.value = _tileAndGameState.value?.mapIndexed { index, tileState ->
            if (index == first || index == second || index == third) {
                tileState.copy(
                    symbolInTile = TileValue.STAR
                )
            } else tileState
        }

        _tileAndGameState.value = _tileAndGameState.value?.map { tileAndGameState ->
            tileAndGameState.copy(tileIsOccupied = true)
        }
    }

    private fun checkForVictory(tileValue: TileValue): Boolean {

        val winningCombinations = arrayOf(
            intArrayOf(0, 1, 2),
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6),
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8),
            intArrayOf(2, 4, 6)
        )

        for (intArr in winningCombinations) {
            //get and store the values of each combo in a reusable Triple
            val (firstIndex, secondIndex, thirdIndex) = Triple(intArr[0], intArr[1], intArr[2])
            //get the 3 symbols in the Tiles at a specific winning combo
            val tileValues = Triple(
                getTileValue(firstIndex), getTileValue(secondIndex), getTileValue(thirdIndex)
            )
            //check if the symbols match entirely X or O and therefore 3 are in a row
            if ((tileValues.first == tileValue && tileValues.second == tileValue && tileValues.third == tileValue)) {
                //place stars in the winning tile line
                updateVictoryState(firstIndex, secondIndex, thirdIndex)
                return true
            }
        }
        return false
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
        when (arrowButtonState.value?.arrowState) {
            Direction.UP -> {
                //can move up
                //if current row is not an edge i.e. > 1
                if (currentRow > 1) {
                    _tileAndGameState.value =
                        _tileAndGameState.value?.mapIndexed { index, tileState ->
                            //subtract a row (3), which is equivalent to moving up, find the tile and select it
                            if ((_position - numOfRows) == index) {
                                tileState.copy(isSelected = true)
                            } else tileState
                        }
                    _position -= numOfRows
                    currentRow -= 1
                    //if it is an edge keep it selected but
                } else if (currentRow == 1) {
                    _tileAndGameState.value =
                        _tileAndGameState.value?.mapIndexed { index, tileState ->

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
//        Log.i(TAG, "position is $_position, row is $currentRow, columns is $currentColumn")
    }

    private fun destroyRandomTiles() {
        Log.i(TAG, "destroy triggerd")
        val possibleTilesToDestroy = arrayOf(
            intArrayOf(0, 2, 6, 8),
            intArrayOf(4),
            intArrayOf(1, 3, 5, 7),
            intArrayOf(0, 2, 4, 6, 8),
            intArrayOf(1, 3, 4, 5, 7)
        )
        val randomDestruction = possibleTilesToDestroy[Random.nextInt(possibleTilesToDestroy.size)]

        viewModelScope.launch {
            _tileAndGameState.value = _tileAndGameState.value?.mapIndexed { index, tileAndGameState ->
                if (index in randomDestruction) {
                    Log.i(TAG, "index = $index, is index in randomdest: ${index in randomDestruction}")
                    tileAndGameState.copy(symbolInTile = TileValue.DESTROYED)
                } else tileAndGameState
            }

            delay(2000)

            _tileAndGameState.value = _tileAndGameState.value?.mapIndexed { index, tileAndGameState ->
                if (index in randomDestruction) {
                    Log.i(TAG, "index = $index, is index in randomdest: ${index in randomDestruction}")
                    tileAndGameState.copy(symbolInTile = TileValue.NONE)
                } else tileAndGameState
            }
        }

    }


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
        return midRow * midColumn
    }

    private fun initToBoardMiddle() {
        //find the middle of a square grid
        //avoid integer division via toDouble cast

        _position = returnMiddleOfBoard()


        _tileAndGameState.value = _tileAndGameState.value?.mapIndexed { index, tileState ->
            if (index == _position) {
                tileState.copy(isSelected = true)
            } else tileState
        }
    }
}











