package com.example.restapp.data

import androidx.room.*
import com.example.restapp.network.Post


@Dao
interface PostDao {
    @Query("SELECT * FROM Post WHERE userId = :id")
    fun getItems(id: Int): List<Post>

    @Query("SELECT * FROM Post WHERE id = :id")
    fun getItem(id: Int): Post

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: Post)

    @Update
    suspend fun update(post: Post)

    @Delete
    suspend fun delete(post: Post)
}