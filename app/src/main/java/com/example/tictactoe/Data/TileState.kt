package com.example.tictactoe.Data

data class TileState(
    val id: Int,
    val isPlayer1Turn: Boolean = true,
    val tileIsNotOccupied: Boolean = true,
    val currentTileSymbolState: TileValue = TileValue.NONE,

//    val tileIsVisible: Boolean = true,
//    val boardIsExpanded: Boolean = false,
//    val isTileOnHold: Boolean = false,
)

val listOfState = List(9) { id ->
    TileState(id = id)
}

enum class TileValue {
    NONE, CROSS, CIRCLE,
}

