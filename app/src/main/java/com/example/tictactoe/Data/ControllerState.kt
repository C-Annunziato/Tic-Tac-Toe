package com.example.tictactoe.Data

data class ControllerState(
    val arrowState: Direction = Direction.NONE,
    val actionState: Action = Action.NONE,
    val destroyButtonIsOnCooldown: Boolean = false,
    val destroyCooldownLeft: Int = 0,
    val lockButtonIsOnCooldown: Boolean = false,
    val lockCooldownLeft: Int = 0,
    val tileIsLocked: Boolean = false,
    val lockOnTileCooldownLeft: Int = 0,
    val transposeButtonIsOnCooldown: Boolean = false,
    val transposeCooldownLeft: Int = 0
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
        