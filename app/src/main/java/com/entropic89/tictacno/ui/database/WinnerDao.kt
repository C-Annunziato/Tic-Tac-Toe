package com.entropic89.tictacno.ui.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface WinnerDao {

    @Upsert
    fun upsertWinner(winner: Winner)

    @Delete
    fun deleteWinner(winner: Winner)

    @Query("SELECT * FROM winner ORDER BY dateTime ASC")
    fun getWinnersOrderedByTime(): LiveData<List<Winner>>


    @Query("SELECT * FROM winner ORDER BY player ASC")
    fun getWinnersOrderedByPlayer(): LiveData<List<Winner>>
}
