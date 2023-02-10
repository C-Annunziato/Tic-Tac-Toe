package com.example.tictactoe.Data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

const val TAG = "viewmodel"

class T3ViewModel : ViewModel() {

    private val _tileState = MutableLiveData(listOfState)
    val tileState: LiveData<List<TileState>?> = _tileState

    private val _arrowButtonState = MutableLiveData(ControllerState())
    val arrowButtonState: LiveData<ControllerState> = _arrowButtonState

    private var currentTileIndex: Int by mutableStateOf(0)

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

    fun checkForVictory(){

    }

    fun resetBoard(){

        _tileState.value = _tileState.value?.map { tileState ->
            tileState.copy(isPlayer1Turn = true, tileIsOccupied = false, currentTileSymbolState = TileValue.NONE)
        }
    }


    fun updateArrowButtonState(direction: Direction) {

        when (direction) {

            Direction.UP ->   Log.i(TAG,"Direction up ")
            Direction.DOWN -> Log.i(TAG,"Direction down ")
            Direction.LEFT -> Log.i(TAG,"Direction left ")
            Direction.RIGHT -> Log.i(TAG,"Direction right ")

            Direction.UP -> _arrowButtonState.value = _arrowButtonState.value?.copy(arrowState = Direction.UP)
            Direction.DOWN -> _arrowButtonState.value = _arrowButtonState.value?.copy(arrowState = Direction.DOWN)
            Direction.LEFT -> _arrowButtonState.value = _arrowButtonState.value?.copy(arrowState = Direction.LEFT)
            Direction.RIGHT -> _arrowButtonState.value = _arrowButtonState.value?.copy(arrowState = Direction.RIGHT)
        }
    }

   //viewmodelscope.launch

fun updateActionButtonState(){

}
}

















