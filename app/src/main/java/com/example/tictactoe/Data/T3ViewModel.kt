package com.example.tictactoe.Data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.ceil
import kotlin.random.Random

const val TAG = "viewmodel"

class T3ViewModel : ViewModel() {

    private val _tileAndGameState = MutableLiveData(listOfState)
    val tileAndGameState: LiveData<List<TileAndGameState>?> = _tileAndGameState

    private val _controllerState = MutableLiveData(ControllerState())
    val controllerState: LiveData<ControllerState> = _controllerState

    private var currentRow: Int by mutableStateOf(0)
    private var currentColumn: Int by mutableStateOf(0)
    private var numColumns: Int = 3
    private var numRows: Int = 3

    private var _position: Int by mutableStateOf(0)
    private var position: Int
        get() = _position
        set(value) {
            _position = value
        }

    private var _timeLeftForTurn: Int by mutableStateOf(12)
    private var timeLeftForTurn: Int
        get() = _timeLeftForTurn
        set(value) {
            _timeLeftForTurn = value
        }

    init {
        initToBoardMiddle()
    }

    fun updateActionButtonState(action: Action) {
        //get the tile at the position of the arrow controls as tileState
        tileAndGameState.value?.getOrNull(position)?.let { ts ->
            when (action) {
                Action.PLACE -> {


                    //everytime a symbol is placed in a non occupied tile do the following:
                    //if tile is empty
                    if (!ts.tileIsOccupied) {

                        //Player one turn
                        if (ts.isPlayer1Turn) {
                            //lock button cd
                            _controllerState.value =
                                _controllerState.value?.lockButtonCooldownLeftP1?.let {
                                    _controllerState.value?.copy(
                                        lockButtonCooldownLeftP1 = it.minus(1).coerceAtLeast(0),
                                    )
                                }


                        }

                        //Player 2 turn
                        if (!ts.isPlayer1Turn) {

                            //lock button cd
                            _controllerState.value =
                                _controllerState.value?.lockButtonCooldownLeftP2?.let {
                                    _controllerState.value?.copy(
                                        lockButtonCooldownLeftP2 = it.minus(1).coerceAtLeast(0),
                                    )
                                }


                        }

                        //transpose
                        //both turns subtract
                        _controllerState.value =
                            _controllerState.value?.transposeCooldownLeftP1?.let {
                                _controllerState.value?.copy(
                                    transposeCooldownLeftP1 = it.minus(1).coerceAtLeast(0),
                                )
                            }

                        //transpose
                        //both turns subtract
                        _controllerState.value =
                            _controllerState.value?.transposeCooldownLeftP2?.let {
                                _controllerState.value?.copy(
                                    transposeCooldownLeftP2 = it.minus(1).coerceAtLeast(0),
                                )
                            }

                        //both turns we subtract
                        _controllerState.value =
                            _controllerState.value?.lockOnTileCooldownLeftP1?.let {
                                if (it > 0) {
                                    _controllerState.value?.copy(
                                        lockOnTileCooldownLeftP1 = it.minus(1)
                                    )
                                } else _controllerState.value
                            }


                        //both turns we subtract
                        _controllerState.value =
                            _controllerState.value?.lockOnTileCooldownLeftP2?.let {
                                if (it > 0) {
                                    controllerState.value?.copy(
                                        lockOnTileCooldownLeftP2 = it.minus(1)
                                    )
                                } else _controllerState.value
                            }


                        //destroy
                        _controllerState.value =
                            _controllerState.value?.destroyCooldownLeftP2?.let {
                                _controllerState.value?.copy(
                                    destroyCooldownLeftP2 = it.minus(1).coerceAtLeast(0),
                                )
                            }

                        //destroy
                        _controllerState.value =
                            _controllerState.value?.destroyCooldownLeftP1?.let {
                                _controllerState.value?.copy(
                                    destroyCooldownLeftP1 = it.minus(1).coerceAtLeast(0),
                                )
                            }


                        //player turn change
                        _tileAndGameState.value = _tileAndGameState.value?.map { tileState ->
                            tileState.copy(
                                isPlayer1Turn = !tileState.isPlayer1Turn,
                            )
                        }

                        //Place Symbol
                        _tileAndGameState.value =
                            _tileAndGameState.value?.mapIndexed { index, tileState ->
                                if (position == index && tileState.isPlayer1Turn) {
                                    tileState.copy(
                                        symbolInTile = TileValue.CROSS, tileIsOccupied = true
                                    )

                                } else if (position == index && !tileState.isPlayer1Turn) {
                                    tileState.copy(
                                        symbolInTile = TileValue.CIRCLE, tileIsOccupied = true
                                    )
                                    //retain the state
                                } else tileState
                            }
                        //Check for victory
                        checkForVictory(TileValue.CROSS)
                        checkForVictory(TileValue.CIRCLE)
                    }


                    // if player 1
                    //lock
                    if (ts.isPlayer1Turn) {
                        if (controllerState.value?.lockButtonIsOnCooldownP1 == true && controllerState.value?.lockButtonCooldownLeftP1!! == 0) {
                            _controllerState.value =
                                _controllerState.value?.copy(lockButtonIsOnCooldownP1 = false)
                        }
                    }

                    // if player 2
                    //lock
                    if (!ts.isPlayer1Turn) {
                        if (controllerState.value?.lockButtonIsOnCooldownP2 == true && controllerState.value?.lockButtonCooldownLeftP2!! == 0) {
                            _controllerState.value =
                                _controllerState.value?.copy(lockButtonIsOnCooldownP2 = false)
                        }
                    }
                    //lock
                    if (controllerState.value?.tileIsLockedP1 == true && controllerState.value?.lockOnTileCooldownLeftP1!! == 0) {
                        _controllerState.value =
                            _controllerState.value?.copy(tileIsLockedP1 = false)
                        _tileAndGameState.value =
                            _tileAndGameState.value?.mapIndexed { index, tileState ->
                                if (index == tileState.lockOnTileP1) {
                                    tileState.copy(
                                        symbolInTile = TileValue.NONE,
                                        tileIsOccupied = false,
                                        lockOnTileP1 = -1
                                    )
                                } else tileState
                            }
                    }


                    //lock
                    if (controllerState.value?.tileIsLockedP2 == true && controllerState.value?.lockOnTileCooldownLeftP2!! == 0) {
                        _controllerState.value =
                            _controllerState.value?.copy(tileIsLockedP2 = false)
                        _tileAndGameState.value =
                            _tileAndGameState.value?.mapIndexed { index, tileState ->
                                if (index == tileState.lockOnTileP2) {
                                    tileState.copy(
                                        symbolInTile = TileValue.NONE,
                                        tileIsOccupied = false,
                                        lockOnTileP2 = -1
                                    )
                                } else tileState
                            }
                    }


                    //destroy
                    if (controllerState.value?.destroyButtonIsOnCooldownP1 == true && controllerState.value?.destroyCooldownLeftP1!! == 0) {
                        _controllerState.value =
                            _controllerState.value?.copy(destroyButtonIsOnCooldownP1 = false)
                    }
                    //destroy
                    if (controllerState.value?.destroyButtonIsOnCooldownP2 == true && controllerState.value?.destroyCooldownLeftP2!! == 0) {
                        _controllerState.value =
                            _controllerState.value?.copy(destroyButtonIsOnCooldownP2 = false)
                    }

                    //transpose
                    if (controllerState.value?.transposeButtonIsOnCooldownP2 == true && controllerState.value?.transposeCooldownLeftP2!! == 0) {
                        _controllerState.value =
                            _controllerState.value?.copy(transposeButtonIsOnCooldownP2 = false)
                    }

                    Log.i(TAG, "transpose cd 1: ${controllerState.value?.transposeCooldownLeftP1}")
                    Log.i(TAG, "transpose cd 2: ${controllerState.value?.transposeCooldownLeftP2}")
                    //transpose
                    if (controllerState.value?.transposeButtonIsOnCooldownP1 == true && controllerState.value?.transposeCooldownLeftP1!! == 0) {
                        _controllerState.value =
                            _controllerState.value?.copy(transposeButtonIsOnCooldownP1 = false)
                    } else ts
                }
                Action.DESTROY -> {

                    if (!ts.gameIsComplete && !controllerState.value?.destroyButtonIsOnCooldownP1!! && ts.isPlayer1Turn) {
                        destroyRandomTiles()
                        _controllerState.value = _controllerState.value?.copy(
                            destroyButtonIsOnCooldownP1 = true, destroyCooldownLeftP1 = 4
                        )
                    }

                    if (!ts.gameIsComplete && !controllerState.value?.destroyButtonIsOnCooldownP2!! && !ts.isPlayer1Turn) {
                        destroyRandomTiles()
                        _controllerState.value = _controllerState.value?.copy(
                            destroyButtonIsOnCooldownP2 = true, destroyCooldownLeftP2 = 4
                        )
                    } else {
                    }


                }
                Action.LOCK -> {

                    //is player 1 and lock button not on CD
                    if (!ts.gameIsComplete && !controllerState.value?.lockButtonIsOnCooldownP1!! && !ts.tileIsOccupied && ts.isPlayer1Turn) {
                        _controllerState.value = _controllerState.value?.copy(
                            lockButtonIsOnCooldownP1 = true,
                            lockButtonCooldownLeftP1 = 2,
                            tileIsLockedP1 = true,
                            lockOnTileCooldownLeftP1 = 2
                        )
                        lockSpecificTile()
                    }
                    //is player 2
                    if (!ts.gameIsComplete && !controllerState.value?.lockButtonIsOnCooldownP2!! && !ts.tileIsOccupied && !ts.isPlayer1Turn) {
                        _controllerState.value = _controllerState.value?.copy(
                            lockButtonIsOnCooldownP2 = true,
                            lockButtonCooldownLeftP2 = 2,
                            tileIsLockedP2 = true,
                            lockOnTileCooldownLeftP2 = 2
                        )
                        lockSpecificTile()
                    } else controllerState
                }

                Action.TRANSPOSE -> {

                    if (!ts.gameIsComplete && !controllerState.value?.transposeButtonIsOnCooldownP2!! && !tileAndGameState.value!!.first().isPlayer1Turn) {
                        transposeTiles()

                        checkForVictory(TileValue.CROSS)
                        checkForVictory(TileValue.CIRCLE)
                    } else {
                    }

                    if (!ts.gameIsComplete && !controllerState.value?.transposeButtonIsOnCooldownP1!! && tileAndGameState.value!!.first().isPlayer1Turn) {
                        transposeTiles()

                        checkForVictory(TileValue.CROSS)
                        checkForVictory(TileValue.CIRCLE)
                    } else {
                    }
                }
                else -> {}
            }
        }
    }

    private fun transposeTiles() {

        val pairArray = arrayOf(
            Pair(0, 8),
            Pair(2, 6),
            Pair(1, 7),
            Pair(3, 5),
        )
        val middleOption = Pair(4, arrayOf(0, 1, 2, 3, 5, 6, 7, 8))

        viewModelScope.launch {

            delay(2000)


        }
        for (pair in pairArray) {
            if (position == pair.first || position == pair.second) {

                val firstIndexSymbol = tileAndGameState.value?.get(pair.first)?.symbolInTile!!
                val secondIndexSymbol = tileAndGameState.value?.get(pair.second)?.symbolInTile!!

                //at least one needs to have a symbol
                if(firstIndexSymbol != secondIndexSymbol) {
                    if (firstIndexSymbol != TileValue.NONE || secondIndexSymbol != TileValue.NONE) {
                        if (firstIndexSymbol != TileValue.LOCKED && secondIndexSymbol != TileValue.LOCKED) {
                            _tileAndGameState.value =
                                _tileAndGameState.value?.mapIndexed { index, tileAndGameState ->
                                    when (index) {
                                        //switch first to second
                                        //if second is NONE then first will end up as NONE so tile is not occupied


                                        pair.first -> {
                                            if (secondIndexSymbol == TileValue.NONE) {
                                                tileAndGameState.copy(
                                                    symbolInTile = secondIndexSymbol,
                                                    tileIsOccupied = false
                                                )
                                            } else tileAndGameState.copy(
                                                symbolInTile = secondIndexSymbol,
                                                tileIsOccupied = true
                                            )

                                        }
                                        //switch second to first
                                        //if first is NONE then second will end up as NONE so tile is not occupied
                                        pair.second -> {
                                            if (firstIndexSymbol == TileValue.NONE) {
                                                tileAndGameState.copy(
                                                    symbolInTile = firstIndexSymbol,
                                                    tileIsOccupied = false
                                                )
                                            } else tileAndGameState.copy(
                                                symbolInTile = firstIndexSymbol,
                                                tileIsOccupied = true
                                            )
                                        }


                                        else -> {
                                            tileAndGameState
                                        }
                                    }


                                }
                            if (tileAndGameState.value!!.first().isPlayer1Turn) {
                                _controllerState.value = _controllerState.value?.copy(
                                    transposeButtonIsOnCooldownP1 = true,
                                    transposeCooldownLeftP1 = 3
                                )
                            }
                            if (!tileAndGameState.value!!.first().isPlayer1Turn) {
                                _controllerState.value = _controllerState.value?.copy(
                                    transposeButtonIsOnCooldownP2 = true,
                                    transposeCooldownLeftP2 = 3
                                )
                            }
                        }
                    }
                }
            }
        }
        if (position == middleOption.first) {

            var randomChoice = middleOption.first
            while (randomChoice == middleOption.first) {
                randomChoice = Random.nextInt(numColumns * numRows)
            }

            val randomSymbolAroundMiddle =
                tileAndGameState.value?.get(if (randomChoice != middleOption.first) randomChoice else middleOption.first)?.symbolInTile!!
            val middleOptionSymbol = tileAndGameState.value?.get(middleOption.first)?.symbolInTile!!
            if (randomSymbolAroundMiddle != TileValue.NONE || middleOptionSymbol != TileValue.NONE) {
                if (randomSymbolAroundMiddle != TileValue.LOCKED && middleOptionSymbol != TileValue.LOCKED) {
                    _tileAndGameState.value =
                        _tileAndGameState.value?.mapIndexed { index, tileAndGameState ->
                            when (index) {
                                middleOption.first -> {
                                    tileAndGameState.copy(
                                        symbolInTile = randomSymbolAroundMiddle,
                                        tileIsOccupied = false
                                    )
                                }
                                randomChoice -> {
                                    tileAndGameState.copy(symbolInTile = middleOptionSymbol)
                                }
                                else -> tileAndGameState
                            }
                        }
                    if (tileAndGameState.value!!.first().isPlayer1Turn) {
                        _controllerState.value = _controllerState.value?.copy(
                            transposeButtonIsOnCooldownP1 = true, transposeCooldownLeftP1 = 3
                        )
                    }
                    if (!tileAndGameState.value!!.first().isPlayer1Turn) {
                        _controllerState.value = _controllerState.value?.copy(
                            transposeButtonIsOnCooldownP2 = true, transposeCooldownLeftP2 = 3
                        )
                    }
                }
            }
        }
    }

    private fun lockSpecificTile() {
        _tileAndGameState.value = _tileAndGameState.value?.mapIndexed { index, tileState ->
            if (position == index) {
                //player 1
                if (tileState.isPlayer1Turn) {
                    tileState.copy(
                        symbolInTile = TileValue.LOCKED,
                        tileIsOccupied = true,
                        lockOnTileP1 = tileState.id
                    )
                } else if (!tileState.isPlayer1Turn) {
                    //player2
                    tileState.copy(
                        symbolInTile = TileValue.LOCKED,
                        tileIsOccupied = true,
                        lockOnTileP2 = tileState.id
                    )
                } else tileState
            } else tileState
        }
    }

    private fun getTileValue(index: Int): TileValue? {
        return tileAndGameState.value?.get(index)?.symbolInTile
    }

    private fun updateVictoryState(first: Int, second: Int, third: Int) {

        _tileAndGameState.value = _tileAndGameState.value?.map { tileAndGameState ->
            tileAndGameState.copy(tileIsOccupied = true, gameIsComplete = true)
        }

        //specific update of Tile to produce a Star
        _tileAndGameState.value = _tileAndGameState.value?.mapIndexed { index, tileState ->
            if (index == first || index == second || index == third) {
                tileState.copy(
                    symbolInTile = TileValue.STAR
                )
            } else tileState
        }
    }

    private fun checkForVictory(tileValue: TileValue): Boolean {

        val winningCombinations = arrayOf(
            intArrayOf(0, 1, 2),
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6),
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8),
            intArrayOf(2, 4, 6)
        )

        for (intArr in winningCombinations) {
            //get and store the values of each combo in a reusable Triple
            val (firstIndex, secondIndex, thirdIndex) = Triple(
                intArr[0], intArr[1], intArr[2]
            )
            //get the 3 symbols in the Tiles at a specific winning combo
            val tileValues = Triple(
                getTileValue(firstIndex), getTileValue(secondIndex), getTileValue(thirdIndex)
            )
            //check if the symbols match entirely X or O and therefore 3 are in a row
            if ((tileValues.first == tileValue && tileValues.second == tileValue && tileValues.third == tileValue)) {
                //place stars in the winning tile line
                updateVictoryState(firstIndex, secondIndex, thirdIndex)
                return true
            }
        }
        return false
    }

    fun updateArrowButtonState(direction: Direction) {
        when (direction) {
            Direction.UP -> _controllerState.value =
                _controllerState.value?.copy(arrowState = Direction.UP)
            Direction.DOWN -> _controllerState.value =
                _controllerState.value?.copy(arrowState = Direction.DOWN)
            Direction.LEFT -> _controllerState.value =
                _controllerState.value?.copy(arrowState = Direction.LEFT)
            Direction.RIGHT -> _controllerState.value =
                _controllerState.value?.copy(arrowState = Direction.RIGHT)
        }
        removePriorSelection()
        moveOnBoard(numRows, numColumns)
    }


    private fun removePriorSelection() {
        _tileAndGameState.value = _tileAndGameState.value?.map { tileState ->
            tileState.copy(isSelected = false)
        }
    }


    private fun moveOnBoard(numOfRows: Int, numOfColumns: Int) {
        when (controllerState.value?.arrowState) {
            Direction.UP -> {
                //can move up
                //if current row is not an edge i.e. > 1
                if (currentRow > 1) {
                    _tileAndGameState.value =
                        _tileAndGameState.value?.mapIndexed { index, tileState ->
                            //subtract a row (3), which is equivalent to moving up, find the tile and select it
                            if ((_position - numOfRows) == index) {
                                tileState.copy(isSelected = true)
                            } else tileState
                        }
                    _position -= numOfRows
                    currentRow -= 1
                    //if it is an edge keep it selected
                } else if (currentRow == 1) {
                    _tileAndGameState.value =
                        _tileAndGameState.value?.mapIndexed { index, tileState ->

                            if (_position == index) {
                                tileState.copy(isSelected = true)
                            } else tileState
                        }
                }
            }

            Direction.DOWN -> {
                if (currentRow < numOfRows) {
                    _tileAndGameState.value =
                        _tileAndGameState.value?.mapIndexed { index, tileState ->
                            if ((_position + numOfRows) == index) {

                                tileState.copy(isSelected = true)
                            } else tileState
                        }
                    _position += numOfRows
                    currentRow += 1
                } else if (currentRow == 3) {
                    _tileAndGameState.value =
                        _tileAndGameState.value?.mapIndexed { index, tileState ->
                            if (_position == index) {
                                tileState.copy(isSelected = true)
                            } else tileState
                        }
                }
            }
            Direction.LEFT -> {
                if (currentColumn > 1) {
                    _tileAndGameState.value =
                        _tileAndGameState.value?.mapIndexed { index, tileState ->
                            if (_position - 1 == index) {
                                tileState.copy(isSelected = true)
                            } else tileState
                        }
                    _position -= 1
                    currentColumn -= 1
                } else if (currentColumn == 1) {
                    _tileAndGameState.value =
                        _tileAndGameState.value?.mapIndexed { index, tileState ->
                            if (_position == index) {
                                tileState.copy(isSelected = true)
                            } else tileState
                        }
                }

            }
            Direction.RIGHT -> {
                if (currentColumn < numOfColumns) {
                    _tileAndGameState.value =
                        _tileAndGameState.value?.mapIndexed { index, tileState ->
                            if (_position + 1 == index) {
                                tileState.copy(isSelected = true)
                            } else tileState
                        }
                    _position += 1
                    currentColumn += 1

                } else if (currentColumn == 3) {
                    _tileAndGameState.value =
                        _tileAndGameState.value?.mapIndexed { index, tileState ->
                            if (_position == index) {
                                tileState.copy(isSelected = true)
                            } else tileState
                        }
                }
            }
        }
    }

    private fun destroyRandomTiles() {
        val possibleTilesToDestroy = arrayOf(
            intArrayOf(0, 2, 6, 8),
            intArrayOf(1, 3, 5, 7),
            intArrayOf(4),
        )
        val randomDestruction = possibleTilesToDestroy[Random.nextInt(possibleTilesToDestroy.size)]


        viewModelScope.launch {
            _tileAndGameState.value =
                _tileAndGameState.value?.mapIndexed { index, tileAndGameState ->
                    if (index in randomDestruction && index != tileAndGameState.lockOnTileP2 && index != tileAndGameState.lockOnTileP1) {
                        tileAndGameState.copy(
                            symbolInTile = TileValue.DESTROYED, tileIsOccupied = true
                        )
                    } else tileAndGameState
                }

            delay(2000)

            _tileAndGameState.value =
                _tileAndGameState.value?.mapIndexed { index, tileAndGameState ->
                    if (index in randomDestruction && index != tileAndGameState.lockOnTileP2 && index != tileAndGameState.lockOnTileP1) {
                        tileAndGameState.copy(
                            symbolInTile = TileValue.NONE, tileIsOccupied = false
                        )
                    } else tileAndGameState
                }
        }
    }


    fun resetBoard() {

        _tileAndGameState.value = _tileAndGameState.value?.map { tileState ->
            tileState.copy(
                isPlayer1Turn = true,
                tileIsOccupied = false,
                symbolInTile = TileValue.NONE,
                isSelected = false,
                gameIsComplete = false,
                lockOnTileP2 = -1,
                lockOnTileP1 = -1,
                winningIndexes = Triple(0, 0, 0)
            )
        }

        _controllerState.value = _controllerState.value?.copy(
            arrowState = Direction.NONE,
            actionState = Action.NONE,
            destroyButtonIsOnCooldownP2 = false,
            destroyButtonIsOnCooldownP1 = false,
            destroyCooldownLeftP2 = 0,
            destroyCooldownLeftP1 = 0,
            lockButtonIsOnCooldownP2 = false,
            lockButtonCooldownLeftP2 = 0,
            tileIsLockedP2 = false,
            tileIsLockedP1 = false,
            lockOnTileCooldownLeftP2 = 0,
            transposeButtonIsOnCooldownP2 = false,
            transposeButtonIsOnCooldownP1 = false,
            transposeCooldownLeftP2 = 0,
            transposeCooldownLeftP1 = 0,
            lockButtonIsOnCooldownP1 = false,
            lockButtonCooldownLeftP1 = 0,
        )

        _tileAndGameState.value = _tileAndGameState.value?.mapIndexed { index, tileState ->
            if (index == returnMiddleOfBoard()) {
                tileState.copy(isSelected = true)
            } else tileState
        }

        initToBoardMiddle()
    }


    private fun returnMiddleOfBoard(): Int {
        val midRow = ceil((numRows.toDouble() / 2)).toInt()
        val midColumn = ceil((numColumns.toDouble() / 2)).toInt()
        currentRow = midRow
        currentColumn = midColumn
        return midRow * midColumn
    }

    private fun initToBoardMiddle() {
        //find the middle of a square grid
        //avoid integer division via toDouble cast

        _position = returnMiddleOfBoard()


        _tileAndGameState.value = _tileAndGameState.value?.mapIndexed { index, tileState ->
            if (index == _position) {
                tileState.copy(isSelected = true)
            } else tileState
        }
    }
    fun outOfTime(){

        Log.i(TAG,"outoftime called")
        _tileAndGameState.value = _tileAndGameState.value?.map{ tileState ->
            tileState.copy(gameIsComplete = true, tileIsOccupied = true)
        }
    }
}











