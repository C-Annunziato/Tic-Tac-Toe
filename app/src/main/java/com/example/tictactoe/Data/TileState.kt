package com.example.tictactoe.Data

data class TileState(
    val id: Int,
    val isPlayer1Turn: Boolean = true,
    val tileIsOccupied: Boolean = false,
    val currentTileSymbolState: TileValue = TileValue.NONE,
    val isSelected: Boolean = false,
    val isSelectedIndex: Int = 4


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

