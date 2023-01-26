package com.example.tictactoe.Data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

const val TAG = "viewmodel"

class T3ViewModel : ViewModel() {

    private val _tileState = MutableLiveData(listOfState)
    val tileState: LiveData<List<TileState>?> = _tileState

    fun updateBoardState(listOfStateIndex: Int, bool: Boolean) {
        _tileState.value = listOfState.mapIndexed { index, tileState ->
            if (listOfStateIndex == index) {
                tileState.copy(isOTurn = bool)
            } else tileState
        }

        fun populateTile() {

        }
    }
}