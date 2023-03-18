package com.entropic89.tictacno.ui.model

data class TileAndGameState(
    //tilestate
    val id: Int,
    val tileIsOccupied: Boolean = false,
    val symbolInTile: TileValue = TileValue.NONE,
    val isSelected: Boolean = false,
    val lockOnTileP2: Int = -1,
    val lockOnTileP1: Int = -1,
    //gamestate
    val isPlayer1Turn: Boolean = true,
    val winningIndexes: Triple<Int, Int, Int> = Triple(0, 0, 0),
    val gameIsComplete: Boolean = false,
    val disableCountDown: Boolean = false,
    val currentPlayer: Player = Player.PLAYER1
    )

val listOfState = List(9) { id ->
    TileAndGameState(id = id)
}

enum class TileValue {
    NONE, CROSS, CIRCLE, STAR, DESTROYED, LOCKED
}

