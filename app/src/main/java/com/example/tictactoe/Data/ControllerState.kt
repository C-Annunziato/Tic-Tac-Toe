package com.example.tictactoe.Data

data class ControllerState(
    val arrowState: Direction = Direction.NONE,
val  actionState: Action = Action.NONE
)

enum class Direction{
    UP,
    DOWN,
    LEFT,
    RIGHT, 
    NONE
}

enum class Action{
    PLACE,
    DESTROY,
    LOCK,
    RANDOM,
    NONE
}
        