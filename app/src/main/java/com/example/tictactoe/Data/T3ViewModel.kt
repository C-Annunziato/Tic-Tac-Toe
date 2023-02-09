package com.example.tictactoe.Data

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

    private var currentTileIndex: Int by mutableStateOf(0)

    fun updatePlayerState(listOfStateIndex: Int, bool: Boolean) {

        //global list items change
        currentTileIndex = listOfStateIndex
        _tileState.value = _tileState.value?.mapIndexed { _, tileState ->
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

        _tileState.value = _tileState.value?.mapIndexed { _, tileState ->
            tileState.copy(isPlayer1Turn = true, tileIsOccupied = false, currentTileSymbolState = TileValue.NONE)
        }

    }
}



