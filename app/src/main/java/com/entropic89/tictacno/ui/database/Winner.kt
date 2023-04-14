package com.entropic89.tictacno.ui.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.type.DateTime


@Entity
data class Winner(
    val player: String,
    val dateTime: DateTime,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
