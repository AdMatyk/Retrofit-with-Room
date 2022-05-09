package com.example.restapp.data

import androidx.room.*
import com.example.restapp.network.Comment
import com.example.restapp.network.Post

@Dao
interface CommentDao {
    @Query("SELECT * FROM Comment WHERE postId = :id")
    fun getItems(id: Int): List<Comment>

    @Query("SELECT * FROM Comment WHERE id = :id")
    suspend fun getItem(id: Int): Comment

    @Query("DELETE FROM COMMENT")
    suspend fun deleteItems()

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comment: Comment)

    @Update
    suspend fun update(comment: Comment)

    @Delete
    suspend fun delete(comment: Comment)
}