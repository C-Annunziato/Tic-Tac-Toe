package com.example.tictactoe.Data

data class TileAndGameState(
    //tilestate
    val id: Int,
    val tileIsOccupied: Boolean = false,
    val symbolInTile: TileValue = TileValue.NONE,
    val isSelected: Boolean = false,
    val isSelectedIndex: Int = 0,
    //gamestate
    val isPlayer1Turn: Boolean = true,
    val winningIndexes: Triple<Int, Int, Int> = Triple(0, 0, 0),
    val gameIsComplete: Boolean = false,
    val turnsTakenPlace: Int = 0
)

val listOfState = List(9) { id ->
    TileAndGameState(id = id)
}

enum class TileValue {
    NONE, CROSS, CIRCLE, STAR, DESTROYED
}

