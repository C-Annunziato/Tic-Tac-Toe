package com.example.tictactoe.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class T3ViewModel: ViewModel() {

    private val _boardState = MutableLiveData (BoardState())
    val boardState:  LiveData<BoardState> = _boardState


val state = 3

}