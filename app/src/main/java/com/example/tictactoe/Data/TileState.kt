package com.example.tictactoe.Data

data class TileState(
    val isPlayer1Turn: Boolean = true,
    val tileIsVisible: Boolean = true,
    val tileIsOccupied: Boolean = false,
    val boardIsExpanded: Boolean = false,
    val isTileOnHold: Boolean = false,
    val id: Int
    )

val listOfState = List(9){ id ->
    TileState(id = id, )
}
