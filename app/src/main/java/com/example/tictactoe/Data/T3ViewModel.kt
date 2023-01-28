package com.example.tictactoe.Data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.lang.reflect.Array.set

const val TAG = "viewmodel"

class T3ViewModel : ViewModel() {

    private val _tileState = MutableLiveData(listOfState)
    val tileState: LiveData<List<TileState>?> = _tileState

    private var currentTileIndex: Int by mutableStateOf(0)

    fun updatePlayerState(listOfStateIndex: Int, bool: Boolean) {

        currentTileIndex = listOfStateIndex
        _tileState.value = _tileState.value?.mapIndexed { index, tileState ->
            tileState.copy(isPlayer1Turn = !bool)
        }

        _tileState.value = _tileState.value?.mapIndexed { index, tileState ->
            if(listOfStateIndex== index && tileState.isPlayer1Turn) {
                tileState.copy(currentTileSymbolState = TileValue.CROSS)
            } else if(listOfStateIndex== index && !tileState.isPlayer1Turn) {
                 tileState.copy(currentTileSymbolState = TileValue.CIRCLE)
            } else  tileState.copy(currentTileSymbolState = TileValue.NONE)
        }

//        listOfState[listOfStateIndex].currentTileSymbolState = TileValue.CROSS
//

//        val currentTileState = tileState.value!![listOfStateIndex]
//        // update individual
//        Log.i(TAG, "${currentTileState.isPlayer1Turn}")
//
//        val updatedTileState = if (currentTileState.isPlayer1Turn) {
//            currentTileState.copy(currentTileSymbolState = TileValue.CROSS)
//        } else currentTileState.copy(currentTileSymbolState = TileValue.CIRCLE)

//        if(currentTileState.id == listOfStateIndex){
//            _tileState.value = _tileState.value?.mapIndexed { index, tileState ->
////            Log.i(TAG, "$index, $listOfStateIndex, ${tileState.id}")
//                if (index == listOfStateIndex) updatedTileState else tileState
//            }
//        }
//
//        val newList = _tileState.value!!.toMutableList()
//        newList[listOfStateIndex] = newList[listOfStateIndex].copy(tileIsOccupied = true)
//        _tileState.value = newList



//
//        _tileState.value = _tileState.value?.mapIndexed { index, tileState ->

//            if (listOfStateIndex == index) {
//                tileState.copy(isPlayer1Turn = tileState.isPlayer1Turn == !bool)
//
//
//            if (tileState.isPlayer1Turn) {
//                tileState.copy(currentTileSymbolState = TileValue.CROSS)
//            } else if (!tileState.isPlayer1Turn) {
//                tileState.copy(currentTileSymbolState = TileValue.CIRCLE)
//            } else tileState
//        }


        //Global update of player turn
//        listOfState.forEach {
//            it.copy(isPlayer1Turn = it.isPlayer1Turn == !bool)
//        }

    }

//    fun updateTileState(id: Int, tileIsOccupied: Boolean) {
//        val newList = _tileState.value!!.toMutableList()
//        newList[id] = newList[id].copy(tileIsOccupied = tileIsOccupied)
//        _tileState.emit(newList)
//    }


//        _tileState.value = listOfState.mapIndexed { index, tileState ->
//            if (listOfStateIndex == index) {
//                tileState.copy(isOTurn = bool)
//            } else tileState
//        }


    fun nextPlayerTurn() {

//        _tileState.value = listOf(_tileState.value!![currentTileIndex].copy(isPlayer1Turn = false))

//        _tileState.value = listOfState?.mapIndexed { index, tileState ->
//            if (currentTileIndex == index) {
//                tileState.copy(isPlayer1Turn = false)
//            } else tileState
//        }

//        var i = TileState(id= 0).copy(isPlayer1Turn = false, isOTurn = false, isXTurn = true)


//    Log.i(
//        TAG,
//        "${_tileState.value?.get(currentTileIndex)?.isPlayer1Turn} is player turn for index: $currentTileIndex"
//    )
    }

    fun populateTile() {

    }
}

