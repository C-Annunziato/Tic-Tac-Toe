package com.example.tictactoe.Data

data class TileState(
    val tileIsVisible: Boolean = true,
    val isTileOccupied: Boolean = false,
    val boardIsExpanded: Boolean = false,
    val isTileOnHold: Boolean = false,
    val isXTurn: Boolean = false,
    val isOTurn: Boolean = false,
    val id: Int
    )

val listOfState = List(9){ id ->
    TileState(id = id)
}