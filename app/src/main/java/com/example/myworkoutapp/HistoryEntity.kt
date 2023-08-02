package com.example.myworkoutapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "history-table")
data class HistoryEntity(
    @PrimaryKey
    var date:String
)
