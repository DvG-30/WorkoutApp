package com.example.myworkoutapp

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Insert
    suspend fun insert(historyEntity: HistoryEntity)

    @Query("SELECT * FROM 'history-table'")
    fun fetchAllDates(): Flow<List<HistoryEntity>>
}