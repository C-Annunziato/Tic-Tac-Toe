package com.example.tictactoe.Data

import android.service.quicksettings.Tile

data class TileState(
    val isPlayer1Turn: Boolean = true,
    val currentTileSymbolState: TileValue = TileValue.NONE,
//    val tileIsVisible: Boolean = true,
//    val tileIsOccupied: Boolean = false,
//    val boardIsExpanded: Boolean = false,
//    val isTileOnHold: Boolean = false,
    val id: Int,
    val tileSymbolNONE: TileValue = TileValue.NONE,
    val tileSymbolCROSS: TileValue = TileValue.CROSS,
    val tileSymbolCIRCLE: TileValue = TileValue.CIRCLE,
)

val listOfState = List(9) { id ->
    TileState(id = id)
}

enum class TileValue {
    NONE, CROSS, CIRCLE,
}

