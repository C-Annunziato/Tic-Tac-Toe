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

    private var currentTileIndex: Int by mutableStateOf(0)

    fun updatePlayerState(listOfStateIndex: Int, bool: Boolean) {
        currentTileIndex = listOfStateIndex

        _tileState.value = _tileState.value?.mapIndexed { index, tileState ->
            Log.i(TAG, "${tileState.isPlayer1Turn} is player one turn for index $index")
            if (listOfStateIndex == index && tileState.isPlayer1Turn) {
                tileState.copy(isPlayer1Turn = !bool)
            } else tileState
        }

//        _tileState.value = listOfState.mapIndexed { index, tileState ->
//            if (listOfStateIndex == index) {
//                tileState.copy(isOTurn = bool)
//            } else tileState
//        }

    }

    fun nextPlayerTurn() {

//        _tileState.value = listOf(_tileState.value!![currentTileIndex].copy(isPlayer1Turn = false))

//        _tileState.value = listOfState?.mapIndexed { index, tileState ->
//            if (currentTileIndex == index) {
//                tileState.copy(isPlayer1Turn = false)
//            } else tileState
//        }

//        var i = TileState(id= 0).copy(isPlayer1Turn = false, isOTurn = false, isXTurn = true)


        Log.i(TAG, "${_tileState.value?.get(currentTileIndex)?.isPlayer1Turn} is player turn for index: $currentTileIndex")
    }

    fun populateTile() {

    }
}

