package com.example.restapp.data

import androidx.room.*
import com.example.restapp.network.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM USER")
    fun getItems(): List<User>

    @Query("SELECT * FROM USER WHERE id = :id")
    fun getItem(id: Int): User

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: User)

    @Update
    suspend fun update(item: User)

    @Delete
    suspend fun delete(item: User)
}