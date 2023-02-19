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

    init {
        initToBoardMiddle()
    }

    fun updateActionButtonState(action: Action) {
        //get the tile at the position of the arrow controls as tileState
        tileAndGameState.value?.getOrNull(position)?.let { tileState ->
            when (action) {
                Action.PLACE -> {
                    //everytime a symbol is placed in a non occupied tile do the following:
                    if (!tileState.tileIsOccupied) {
                        _controllerState.value = _controllerState.value?.destroyCooldownLeft?.let {
                            _controllerState.value?.copy(
                                destroyCooldownLeft = it.minus(1).coerceAtLeast(0),
                            )
                        }
                        _controllerState.value = _controllerState.value?.lockCooldownLeft?.let {
                            _controllerState.value?.copy(
                                lockCooldownLeft = it.minus(1).coerceAtLeast(0),
                            )
                        }
                        _controllerState.value =
                            _controllerState.value?.transposeCooldownLeft?.let {
                                _controllerState.value?.copy(
                                    transposeCooldownLeft = it.minus(1).coerceAtLeast(0),
                                )
                            }
                        _controllerState.value =
                            _controllerState.value?.lockOnTileCooldownLeft?.let {
                                if (it > 0) {
                                    _controllerState.value?.copy(
                                        lockOnTileCooldownLeft = it.minus(1).coerceAtLeast(0)
                                    )
                                } else controllerState.value
                            }

                        _tileAndGameState.value = _tileAndGameState.value?.map { tileState ->
                            tileState.copy(
                                isPlayer1Turn = !tileState.isPlayer1Turn,
                                turnsTakenPlace = tileState.turnsTakenPlace + 1,
                            )
                        }
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
                        checkForVictory(TileValue.CROSS)
                        checkForVictory(TileValue.CIRCLE)
                    }
                    if (controllerState.value?.destroyButtonIsOnCooldown == true && controllerState.value?.destroyCooldownLeft!! == 0) {
                        _controllerState.value =
                            _controllerState.value?.copy(destroyButtonIsOnCooldown = false)
                    }
                    if (controllerState.value?.lockButtonIsOnCooldown == true && controllerState.value?.lockCooldownLeft!! == 0) {
                        _controllerState.value =
                            _controllerState.value?.copy(lockButtonIsOnCooldown = false)
                    }

                    if (controllerState.value?.tileIsLocked == true && controllerState.value?.lockOnTileCooldownLeft!! == 0) {
                        _controllerState.value = _controllerState.value?.copy(tileIsLocked = false)

                        _tileAndGameState.value =
                            _tileAndGameState.value?.mapIndexed { index, tileState ->
                                if (index == tileState.lockOnTile) {
                                    tileState.copy(
                                        symbolInTile = TileValue.NONE, tileIsOccupied = false
                                    )
                                } else tileState
                            }
                    }

                    if (controllerState.value?.transposeButtonIsOnCooldown == true && controllerState.value?.transposeCooldownLeft!! == 0) {
                        _controllerState.value =
                            _controllerState.value?.copy(transposeButtonIsOnCooldown = false)
                    } else tileState

                }
                Action.DESTROY -> {
                    if (!tileState.gameIsComplete && !controllerState.value?.destroyButtonIsOnCooldown!!) {
                        destroyRandomTiles()
                        _controllerState.value = _controllerState.value?.copy(
                            destroyButtonIsOnCooldown = true, destroyCooldownLeft = 4
                        )
                    } else {

                    }
                }
                Action.LOCK -> {
                    if (!tileState.gameIsComplete && !controllerState.value?.lockButtonIsOnCooldown!! && !tileState.tileIsOccupied) {
                        _controllerState.value = _controllerState.value?.copy(
                            lockButtonIsOnCooldown = true,
                            lockCooldownLeft = 5,
                            tileIsLocked = true,
                            lockOnTileCooldownLeft = 3
                        )

                        lockSpecificTile()
                    } else {

                    }
                }

                Action.TRANSPOSE -> {
                    if (!tileState.gameIsComplete && !controllerState.value?.transposeButtonIsOnCooldown!!) {
//                        Log.i(TAG,"value of transpose tiles is ${transposeTiles()}")
                            transposeTiles()

//                            _controllerState.value = _controllerState.value?.copy(
//                                transposeButtonIsOnCooldown = true, transposeCooldownLeft = 4
//                            )

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

        for (pair in pairArray) {
            if (position == pair.first || position == pair.second) {


                val firstIndexSymbol = tileAndGameState.value?.get(pair.first)?.symbolInTile!!
                val secondIndexSymbol = tileAndGameState.value?.get(pair.second)?.symbolInTile!!


                Log.i(TAG,"first and second symbol is ${firstIndexSymbol != TileValue.NONE}, ${secondIndexSymbol != TileValue.NONE}")
                //at least one needs to have a symbol
                if (firstIndexSymbol != TileValue.NONE || secondIndexSymbol != TileValue.NONE) {
                    if(firstIndexSymbol != TileValue.LOCKED && secondIndexSymbol != TileValue.LOCKED) {
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
                                            symbolInTile = secondIndexSymbol, tileIsOccupied = true
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
                                            symbolInTile = firstIndexSymbol, tileIsOccupied = true
                                        )
                                    }
                                    else -> {
                                        tileAndGameState
                                    }
                                }
                            }
                        _controllerState.value = _controllerState.value?.copy(
                            transposeButtonIsOnCooldown = true, transposeCooldownLeft = 4
                        )
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
                                    tileAndGameState.copy(symbolInTile = randomSymbolAroundMiddle)
                                }
                                randomChoice -> {
                                    tileAndGameState.copy(symbolInTile = middleOptionSymbol)
                                }
                                else -> tileAndGameState
                            }
                        }
                    _controllerState.value = _controllerState.value?.copy(
                        transposeButtonIsOnCooldown = true, transposeCooldownLeft = 4
                    )
                }
            }
        }
    }

    private fun lockSpecificTile() {
        _tileAndGameState.value = _tileAndGameState.value?.mapIndexed { index, tileState ->
            if (position == index) {
                tileState.copy(
                    symbolInTile = TileValue.LOCKED,
                    //if the lock cooldown is not zero then tile is occupied
                    tileIsOccupied = true, lockOnTile = tileState.id
                )
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
                    if (index in randomDestruction && index != tileAndGameState.lockOnTile) {
                        tileAndGameState.copy(symbolInTile = TileValue.DESTROYED)
                    } else tileAndGameState
                }

            delay(2000)

            _tileAndGameState.value =
                _tileAndGameState.value?.mapIndexed { index, tileAndGameState ->
                    if (index in randomDestruction && index != tileAndGameState.lockOnTile) {
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
                isSelectedIndex = returnMiddleOfBoard(),
                gameIsComplete = false,
                turnsTakenPlace = 0,
                lockOnTile = -1,
                winningIndexes = Triple(0, 0, 0)
            )
        }

        _controllerState.value = _controllerState.value?.copy(
            arrowState = Direction.NONE,
            actionState = Action.NONE,
            destroyButtonIsOnCooldown = false,
            destroyCooldownLeft = 0,
            lockButtonIsOnCooldown = false,
            lockCooldownLeft = 0,
            tileIsLocked = false,
            lockOnTileCooldownLeft = 0,
            transposeButtonIsOnCooldown = false,
            transposeCooldownLeft = 0
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
}











