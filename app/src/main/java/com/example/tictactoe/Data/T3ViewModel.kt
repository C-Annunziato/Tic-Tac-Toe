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

    private val _boardState = MutableLiveData(BoardState())
    val boardState: LiveData<BoardState> = _boardState
    fun updateBoardState(bool: Boolean){
        Log.i(TAG, "update board state called")
        _boardState.value = _boardState.value?.copy(isOTurn = bool)
    }

    init {
        Log.i(TAG, "viewmodel called")
        Log.i(TAG, "${boardState.value}")
    }

    fun populateTile() {
        Log.i(TAG, "populate tile called")
        Log.i(TAG, "${boardState.value?.isOTurn}")
       _boardState.value?.copy(isOTurn = true)
        Log.i(TAG, "${boardState.value?.isOTurn}")
//        bstate.copy(isOTurn = true)
        Log.i(TAG, "${boardState.value?.isXTurn}")
    }
}