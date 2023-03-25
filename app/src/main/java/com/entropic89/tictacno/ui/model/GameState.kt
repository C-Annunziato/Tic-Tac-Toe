package com.entropic89.tictacno.ui.model

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.update
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

    fun resetPlayer(){
       _currentPlayer.value = Player.PLAYER1
    }

    fun updateDestroyCooldowns(tileState: TileAndGameState) {
        _currentPlayer.update { player ->
            player.apply {
                if (!tileState.gameIsComplete && !controllerState.destroyButtonIsOnCooldownP1!!) {
                    controllerState = controllerState.copy(
                        destroyButtonIsOnCooldownP1 = true, destroyCooldownLeftP1 = 4
                    )
                }
            }
        }
    }

    fun updateTransposeCooldowns(tileState: TileAndGameState) {

    }

    fun isTransposeButtonOnCooldown(): Boolean {
        return currentPlayer.value.controllerState.transposeButtonIsOnCooldownP1
    }

    fun updateLockCooldowns(tileState: TileAndGameState) {
        _currentPlayer.update { player ->
            player.apply {
                if (!tileState.gameIsComplete && !controllerState.lockButtonIsOnCooldownP1!! && !tileState.tileIsOccupied && tileState.isPlayer1Turn) {
                    controllerState = controllerState.copy(
                        lockButtonIsOnCooldownP1 = true,
                        lockButtonCooldownLeftP1 = 3,
                        tileIsLockedP1 = true,
                        lockOnTileCooldownLeftP1 = 3,
                    )
                }
            }
        }
    }


    fun updateActionButtonCooldowns() {

        //transpose is subtracted on both turns need to deal with it or split it up to individual players

        _currentPlayer.update { player ->
            player.apply {
                controllerState = controllerState.copy(
                    lockButtonCooldownLeftP1 = controllerState.lockButtonCooldownLeftP1.minus(1)
                        .coerceAtLeast(0),
                    transposeCooldownLeftP1 = controllerState.transposeCooldownLeftP1.minus(1)
                        .coerceAtLeast(0),
                    lockOnTileCooldownLeftP1 = controllerState.lockOnTileCooldownLeftP1.minus(1)
                        .coerceAtLeast(0),
                    destroyCooldownLeftP1 = controllerState.destroyCooldownLeftP1.minus(1)
                        .coerceAtLeast(0),
                    lockButtonIsOnCooldownP1 = (controllerState.lockButtonIsOnCooldownP1 && controllerState.lockButtonCooldownLeftP1!! == 0),
                    tileIsLockedP1 = (controllerState.tileIsLockedP1 && controllerState.lockOnTileCooldownLeftP1!! == 0),

                    destroyButtonIsOnCooldownP1 = (controllerState.destroyButtonIsOnCooldownP1 && controllerState.destroyCooldownLeftP1!! == 0),
                    transposeButtonIsOnCooldownP1 = (controllerState.transposeButtonIsOnCooldownP1 && controllerState.transposeCooldownLeftP1!! == 0)

                )
            }
        }
    }

    fun lockTile(listOfTileState: List<TileAndGameState>): List<TileAndGameState> {
        return listOfTileState.mapIndexed { index, tileState ->
            if (index == tileState.lockOnTileP1) {
                tileState.copy(
                    symbolInTile = TileValue.NONE, tileIsOccupied = false, lockOnTileP1 = -1
                )
            } else tileState
        }
    }


    fun placeSymbolInTile(
        tileState: List<TileAndGameState>, position: Int
    ): List<TileAndGameState> {
        return tileState.mapIndexed { index, tileState ->
            if (position == index) {
                tileState.copy(
                    symbolInTile = currentPlayer.value.tileValue, tileIsOccupied = true
                )
            } else tileState
        }
    }

    //synchronizing controller states
    fun changePlayer() {
        Log.i(tag, "player change")
        //source of truth ensures we always correlate correct player to record
        //current player matches one of the  players in enum class
        val currentPlayerIndex = players.indexOfFirst { it.name == currentPlayer.value.name }
        players[currentPlayerIndex] = _currentPlayer.getAndUpdate { player ->
            //select either the first or next player
            //cue of players if at last index and that's what currentPlayerIndex is
            //then loop back to first player
            if (players.lastIndex == currentPlayerIndex) {
                players.first()
            } else {
                //next player in cue
                players[currentPlayerIndex + 1]
            }
        }
    }
}


fun main() {
    runBlocking {
        var gs = GameState()
//        launch {
//            gs.currentPlayer.collect {
//                println(it.controllerState)
//            }
//        }
//        println("")
//        gs.placeSymbol()

    }
}















