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

    private val _tileState = MutableLiveData(listOfState)
    val tileState: LiveData<List<TileState>?> = _tileState

    private val _arrowButtonState = MutableLiveData(ControllerState())
    val arrowButtonState: LiveData<ControllerState> = _arrowButtonState

    private var currentTileIndex: Int by mutableStateOf(0)
    private var currentRow: Int by mutableStateOf(0)
    private var currentColumn: Int by mutableStateOf(0)
    private var numColumns: Int = 3
    private var numRows: Int = 3

    //init to middle position
    private var position: Int by mutableStateOf(0)
//        .coerceIn(0 until (numRows * numColumns)))

    init {
        initToBoardMiddle()
        Log.i(TAG, "position is $position, row is $currentRow, columns is $currentColumn")
    }

    fun updatePlayerState(listOfStateIndex: Int, bool: Boolean) {

        //global list items change
        currentTileIndex = listOfStateIndex
        _tileState.value = _tileState.value?.map { tileState ->
            tileState.copy(isPlayer1Turn = !bool)
        }

        //specific list item change
        _tileState.value = _tileState.value?.mapIndexed { index, tileState ->
            if (listOfStateIndex == index && tileState.isPlayer1Turn) {
                tileState.copy(currentTileSymbolState = TileValue.CROSS, tileIsOccupied = true)
            } else if (listOfStateIndex == index && !tileState.isPlayer1Turn) {
                tileState.copy(currentTileSymbolState = TileValue.CIRCLE, tileIsOccupied = true)
                //retain the state
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
        _tileState.value = _tileState.value?.map { tileState ->
            tileState.copy(isSelected = false)
        }
        Log.i(TAG,"remove prior selection beijng called")
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
                    _tileState.value = _tileState.value?.mapIndexed { index, tileState ->
                        //moving up
                        if ((position - numOfRows) == index) {

                            tileState.copy(isSelected = true)
                        } else tileState
                    }
                    position -= numOfRows
                    currentRow -= 1

                } else if (currentRow == 1) {
                    _tileState.value = _tileState.value?.mapIndexed { index, tileState ->
                        //moving up
                        if (position == index) {
                            tileState.copy(isSelected = true)
                        } else tileState
                    }
                }
            }

            Direction.DOWN -> {
                if (currentRow < numOfRows) {
                    _tileState.value = _tileState.value?.mapIndexed { index, tileState ->
                        if ((position + numOfRows) == index) {

                            tileState.copy(isSelected = true)
                        } else tileState
                    }
                    position += numOfRows
                    currentRow += 1
                }
                    else if (currentRow == 3) {
                        _tileState.value = _tileState.value?.mapIndexed { index, tileState ->
                            //moving up
                            if (position == index) {
                                tileState.copy(isSelected = true)
                            } else tileState
                        }
                }
            }
            Direction.LEFT -> {
                if (currentColumn > 1) {
                    _tileState.value = _tileState.value?.mapIndexed { index, tileState ->
                        if (position-- == index) {
                            tileState.copy(isSelected = true)
                        } else tileState
                    }
                } else {
                }
                position--
                currentColumn--
            }
            Direction.RIGHT -> {
                if (currentColumn < numOfColumns) {
                    _tileState.value = _tileState.value?.mapIndexed { index, tileState ->
                        if (position++ == index) {
                            tileState.copy(isSelected = true)
                        } else tileState
                    }
                    position++
                    currentColumn++
                } else {
                }
            }
        }
        Log.i(TAG, "position is $position, row is $currentRow, columns is $currentColumn")
    }


//viewmodelscope.launch

    fun updateActionButtonState() {

    }


    fun resetBoard() {

        _tileState.value = _tileState.value?.map { tileState ->
            tileState.copy(
                isPlayer1Turn = true,
                tileIsOccupied = false,
                currentTileSymbolState = TileValue.NONE,
                isSelected = false,
                isSelectedIndex = returnMiddleOfBoard()
            )
        }

        _tileState.value = _tileState.value?.mapIndexed { index, tileState ->
            if (index == returnMiddleOfBoard()) {
                tileState.copy(isSelected = true)
            } else tileState
        }

        _arrowButtonState.value = _arrowButtonState.value?.copy(
            arrowState = Direction.NONE, actionState = Action.NONE
        )

        initToBoardMiddle()
    }


    fun checkForVictory() {

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

        position = returnMiddleOfBoard()

        Log.i(
            TAG,
            " init or reset board :: position $position, row $currentRow, column $currentColumn"
        )

        _tileState.value = _tileState.value?.mapIndexed { index, tileState ->
            if (index == position) {
                tileState.copy(isSelected = true)
            } else tileState
        }
    }
}











