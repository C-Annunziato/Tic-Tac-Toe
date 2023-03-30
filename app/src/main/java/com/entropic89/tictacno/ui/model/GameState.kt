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

    fun setDestroyCooldowns(tileState: TileAndGameState) {
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
//figure out player in this context
    fun setLockCooldowns(tileState: TileAndGameState) {
        _currentPlayer.update { player ->
            player.apply {
                if (!tileState.gameIsComplete && !controllerState.lockButtonIsOnCooldownP1!! && !tileState.tileIsOccupied) {
                    controllerState = controllerState.copy(
                        lockButtonIsOnCooldownP1 = true,
                        lockButtonCooldownLeftP1 = 3,
                        tileIsLockedP1 = true,
                        lockOnTileCooldownLeftP1 = 3,
                    )
                    Log.i(tag, "lock button is on cd ${controllerState.lockOnTileCooldownLeftP1}")
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
                    //boolean expression set directly to controller state
                    //if the cd left is zero and it was on cd  then the overall cd needs to be set to false
                    lockButtonIsOnCooldownP1 = !(controllerState.lockButtonIsOnCooldownP1 && controllerState.lockButtonCooldownLeftP1!! == 0),
                    tileIsLockedP1 = !(controllerState.tileIsLockedP1 && controllerState.lockOnTileCooldownLeftP1!! == 0),
                    destroyButtonIsOnCooldownP1 = !(controllerState.destroyButtonIsOnCooldownP1 && controllerState.destroyCooldownLeftP1!! == 0),
                    transposeButtonIsOnCooldownP1 = !(controllerState.transposeButtonIsOnCooldownP1 && controllerState.transposeCooldownLeftP1!! == 0)

                )
                Log.i(tag, "lock button is on cd ${controllerState.lockOnTileCooldownLeftP1}")
            }
        }
    }

    fun unlockTileGameState(listOfTileState: List<TileAndGameState>, controllerState: ControllerState): List<TileAndGameState> {
        return listOfTileState.mapIndexed { index, tileState ->
            if (index == tileState.lockOnTileP1) {
                if (controllerState.tileIsLockedP1 && controllerState.lockOnTileCooldownLeftP1 == 0) {
                    controllerState.copy(tileIsLockedP1 = false)
                    tileState.copy(
                        symbolInTile = TileValue.NONE, tileIsOccupied = false, lockOnTileP1 = -1
                    )
                } else tileState
            } else tileState
        }
    }

    fun unlockTileControllerState(controllerState: ControllerState) : ControllerState {
       return  if (controllerState.lockButtonIsOnCooldownP1 && controllerState.lockButtonCooldownLeftP1 == 0) {
            controllerState.copy(lockButtonIsOnCooldownP1 = false)
        }  else controllerState
    }

    fun lockTile(listOfTileState: List<TileAndGameState>, position: Int): List<TileAndGameState> {
        return listOfTileState.mapIndexed { index, tileState ->
            if (index == position) {
                tileState.copy(
                    symbolInTile = TileValue.LOCKED, tileIsOccupied = true, lockOnTileP1 = tileState.id
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

//fun updateActionCooldowns() {
//
//    //transpose is subtracted on both turns need to deal with it or split it up to individual players
//    updatePlayer {
//        copy(
//            lockButtonCooldownLeftP1 = lockButtonCooldownLeftP1.minus(1).coerceAtLeast(0),
//            transposeCooldownLeftP1 = transposeCooldownLeftP1.minus(1).coerceAtLeast(0),
//            lockOnTileCooldownLeftP1 = lockOnTileCooldownLeftP1.minus(1).coerceAtLeast(0),
//            destroyCooldownLeftP1 = destroyCooldownLeftP1.minus(1).coerceAtLeast(0),
//            lockButtonIsOnCooldownP1 = (lockButtonIsOnCooldownP1 && lockButtonCooldownLeftP1 == 0),
//            tileIsLockedP1 = (tileIsLockedP1 && lockOnTileCooldownLeftP1 == 0),
//            destroyButtonIsOnCooldownP1 = (destroyButtonIsOnCooldownP1 && destroyCooldownLeftP1 == 0),
//            transposeButtonIsOnCooldownP1 = (transposeButtonIsOnCooldownP1 && transposeCooldownLeftP1 == 0)
//        )
//    }
//}
//private fun updatePlayer(
//    updateController : NewControllerState.() -> NewControllerState,
//) {
//    // PUT THE UPDATED PLAYER IN FLOW
//    _currentPlayer.update { player ->
//        //UPDATE THE PLAYER OBJECT
//        player.apply {
//            //UPDATE THE PLAYER'S CONTROL STATE
//            controllerState = updateController(controllerState)
//        }
//    }
//}


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















