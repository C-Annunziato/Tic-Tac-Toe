package com.example.tictactoe.Data

data class BoardState(

    val tileIsVisible: Boolean = true,
    val isTileOccupied: Boolean = false,
    val boardIsExpanded: Boolean = false,
    val isTileOnHold: Boolean = false,
    val isXTurn: Boolean = false,
    val isOTurn: Boolean = false, )
