package com.entropic89.tictacno.ui.model

import androidx.compose.runtime.toMutableStateList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

const val tag = "gamestate"

data class GameState(
    val winningIndexes: Triple<Int, Int, Int> = Triple(0, 0, 0),
    val gameIsComplete: Boolean = false,
    val disableCountDown: Boolean = false,
) {


    //initial state of all players
    private val players = (Player.values()).toList().toMutableStateList()
    private val _currentPlayer = MutableStateFlow(Player.PLAYER1)
    val currentPlayer = _currentPlayer.asStateFlow()

    fun updateActionState(action: Action) {
        //Place
        //Destroy
        //Transpose
        //Lock
    }

    fun placeSymbol() {

        //transpose is subtracted on both turns need to deal with it or split it up to individual players

        _currentPlayer.update { player ->
            player.apply {
                controllerState = controllerState.copy(
                    lockButtonCooldownLeftP1 = controllerState.lockButtonCooldownLeftP1.minus(1)
                        .coerceAtLeast(0),
                    transposeCooldownLeftP1 =
                    controllerState.transposeCooldownLeftP1.minus(1).coerceAtLeast(0),
                    lockOnTileCooldownLeftP1 =
                    controllerState.lockOnTileCooldownLeftP1.minus(1).coerceAtLeast(0),
                    destroyCooldownLeftP1 =
                    controllerState.destroyCooldownLeftP1.minus(1).coerceAtLeast(0),
                )


            }
        }
    }

    fun changePlayer() {
        val result = _currentPlayer.getAndUpdate { player ->
            //source of truth ensures we always correlate correct player to record
            val index = players.indexOfFirst { it.name == player.name }
            //select either the first or next player
            if (players.lastIndex == index) {
                players.first()
            } else {
               //next player in chain
                players[index + 1]
            }
        }
        val index = players.indexOfFirst { it.name == result.name }
        players[index] = result
    }

}


fun main() {
    runBlocking {
        var gs = GameState()
        launch {
            gs.currentPlayer.collect {
                println(it.controllerState)
            }
        }
        println("")
        gs.placeSymbol()
    }
}















