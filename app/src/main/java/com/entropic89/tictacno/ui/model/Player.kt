package com.entropic89.tictacno.ui.model

enum class Player(
    var controllerState: NewControllerState = NewControllerState(),
    val tileValue: TileValue
) {

    PLAYER1(tileValue = TileValue.CIRCLE),
    PLAYER2(tileValue = TileValue.CROSS),

}