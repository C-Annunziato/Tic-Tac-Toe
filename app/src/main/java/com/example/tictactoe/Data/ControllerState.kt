package com.example.tictactoe.Data

data class ControllerState(
    val arrowState: Direction = Direction.NONE,
    val actionState: Action = Action.NONE,
    val buttonIsOnCooldown: Boolean = false,
    val cooldownLeft: Int = 0
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
    RANDOM,
    NONE
}
        