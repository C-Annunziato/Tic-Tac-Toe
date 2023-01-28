package com.example.tictactoe.Data

import android.service.quicksettings.Tile

data class TileState(
    val id: Int,
    val isPlayer1Turn: Boolean = true,
    val tileIsOccupied: Boolean = false,
    val currentTileSymbolState: TileValue = TileValue.NONE,
    val tileSymbolNONE: TileValue = TileValue.NONE,
    val tileSymbolCROSS: TileValue = TileValue.CROSS,
    val tileSymbolCIRCLE: TileValue = TileValue.CIRCLE,
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

