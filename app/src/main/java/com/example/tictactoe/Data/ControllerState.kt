package com.example.tictactoe.Data

data class ControllerState(
   //Shared State
    val arrowState: Direction = Direction.NONE,
    val actionState: Action = Action.NONE,
    val tileIsLocked: Boolean = false,

    //Player 1
    val destroyButtonIsOnCooldownP1: Boolean = false,
    val destroyCooldownLeftP1: Int = 0,
    val lockButtonIsOnCooldownP1: Boolean = false,
    val lockCooldownLeftP1: Int = 0,
    val transposeButtonIsOnCooldownP1: Boolean = false,
    val transposeCooldownLeftP1: Int = 0,
    val lockOnTileCooldownLeftP1: Int = 0,
    //Player 2
    val destroyButtonIsOnCooldownP2: Boolean = false,
    val destroyCooldownLeftP2: Int = 0,
    val lockButtonIsOnCooldownP2: Boolean = false,
    val lockCooldownLeftP2: Int = 0,
    val transposeButtonIsOnCooldownP2: Boolean = false,
    val transposeCooldownLeftP2: Int = 0,
    val lockOnTileCooldownLeftP2: Int = 0,
)

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    NONE
}

enum class Action {
    PLACE,
    DESTROY,
    LOCK,
    TRANSPOSE,
    NONE
}
        