package com.example.restapp.data

import androidx.room.*
import com.example.restapp.network.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM USER")
    fun getItems(): List<User>

    @Query("SELECT * FROM USER WHERE id = :id")
    suspend fun getItem(id: Int): User
    @Query("DELETE FROM USER")
    suspend fun deleteItems()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: User)
    @Update
    suspend fun update(item: User)
    @Delete
    suspend fun delete(item: User)
}