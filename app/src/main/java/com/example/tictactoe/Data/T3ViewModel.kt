package com.example.tictactoe.Data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.abs
import kotlin.math.ceil

const val TAG = "viewmodel"

class T3ViewModel : ViewModel() {

    private val _tileState = MutableLiveData(listOfState)
    val tileState: LiveData<List<TileState>?> = _tileState

    private val _arrowButtonState = MutableLiveData(ControllerState())
    val arrowButtonState: LiveData<ControllerState> = _arrowButtonState

    private var currentTileIndex: Int by mutableStateOf(0)
    private var selectedIndex: Int by mutableStateOf(4)

    init {
        _tileState.value = _tileState.value?.mapIndexed { index, tileState ->
            if (index == 4) {
                tileState.copy(isSelected = true)
            } else tileState
        }
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

//            Direction.UP -> Log.i(TAG, "Direction up ")
//            Direction.DOWN -> Log.i(TAG, "Direction down ")
//            Direction.LEFT -> Log.i(TAG, "Direction left ")
//            Direction.RIGHT -> Log.i(TAG, "Direction right ")

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
        moveOnBoard(3, 3)
    }

    private fun removePriorSelection() {
        _tileState.value = _tileState.value?.map { tileState ->
            tileState.copy(isSelected = false)
        }
    }


    private fun moveOnBoard(rows: Int, columns: Int) {

        //start in the middle
        var currentRow = ceil((rows / 2).toDouble()).toInt()
        var currentColumns = ceil((columns / 2).toDouble()).toInt()

        //track current row and column to get a position
        //if rows and columns are even then your position is a simple multiplication of the row and column your are on
        //else if they are uneven, then you need a correction term which is abs(rows-columns) * rows
        //the correction term does not apply if you only have one row, hence if rows > 1
        var position: Int = if (currentRow > 1)
            currentRow * currentColumns + (abs(rows - columns) * rows) else
            currentRow * currentColumns

        //if current row is not an edge
        if (currentRow > 1) {
            when (arrowButtonState.value?.arrowState) {
                Direction.UP -> {
                    //move up
                    _tileState.value = _tileState.value?.mapIndexed { index, tileState ->
                        //find the new position index by subtracting an entire row
                        if (position - rows == index) {
                            //set that index tile-state to true, aka move to that index
                            tileState.copy(isSelected = true)
                        } else tileState
                    }
                }
            }


//        } else if ( rows < )

        }
    }

//    fun moveOnBoard(rows: Int, columns: Int) {
//        if (rows == columns) {
//            when (arrowButtonState.value?.arrowState) {
//                Direction.UP -> {
//                    selectedIndex -= rows
//                    _tileState.value = _tileState.value?.mapIndexed { index, tileState ->
//                        if (selectedIndex == index) {
//                            tileState.copy(isSelected = true)
//                        } else tileState
//                    }
//                }
//
//                Direction.DOWN -> {
//                    selectedIndex += rows
//
//                    _tileState.value = _tileState.value?.mapIndexed { index, tileState ->
//                        if (selectedIndex == index) {
//                            tileState.copy(isSelected = true)
//                        } else tileState
//                    }
////                Direction.LEFT -> selectedIndex
////                    Direction.RIGHT ->
//                }
//            }
//        }
//    }


//    private fun moveOnBoard() {
//
//        when (arrowButtonState.value?.arrowState) {
//
//            Direction.UP -> {
//                selectedIndex = (selectedIndex - 3).coerceIn(0, 8)
//                _tileState.value = _tileState.value?.mapIndexed { index, tileState ->
//                    if (selectedIndex == index) {
//                        tileState.copy(isSelected = true)
//                    } else tileState
//                }
//            }
//            Direction.DOWN -> {
//                ;  selectedIndex = (selectedIndex + 3).coerceIn(0, 8)
//                _tileState.value = _tileState.value?.mapIndexed { index, tileState ->
//                    if (selectedIndex == index) {
//                        tileState.copy(isSelected = true)
//                    } else tileState
//                }
//            }
//            Direction.LEFT -> {
//
//                selectedIndex = (selectedIndex - 1).coerceIn(0, 8)
//                _tileState.value = _tileState.value?.mapIndexed { index, tileState ->
//                    if (selectedIndex == index) {
//                        tileState.copy(isSelected = true)
//                    } else tileState
//                }
//            }
//
//            Direction.RIGHT -> {
//                selectedIndex = (selectedIndex + 1).coerceIn(0, 8)
//                _tileState.value = _tileState.value?.mapIndexed { index, tileState ->
//                    if (selectedIndex == index) {
//                        tileState.copy(isSelected = true)
//                    } else tileState
//
//                }
//
//            }
//        }
//    }

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
                isSelectedIndex = 4
            )
        }

        _tileState.value = _tileState.value?.mapIndexed { index, tileState ->
            if (index == 4) {
                tileState.copy(isSelected = true)
            } else tileState
        }

        _arrowButtonState.value = _arrowButtonState.value?.copy(
            arrowState = Direction.NONE, actionState = Action.NONE
        )
    }


    fun checkForVictory() {

    }

}









