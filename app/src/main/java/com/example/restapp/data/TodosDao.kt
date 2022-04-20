package com.example.restapp.data

import android.content.Context
import androidx.room.*
import com.example.restapp.network.Post
import com.example.restapp.network.Todos

@Dao
interface TodosDao {
    @Query("SELECT * FROM Todos WHERE userId = :id")
    fun getItems(id: Int): List<Todos>

    @Query("SELECT * FROM Todos WHERE id = :id")
    fun getItem(id: Int): Todos

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todos: Todos)

    @Update
    suspend fun update(todos: Todos)

    @Delete
    suspend fun delete(todos: Todos)
}