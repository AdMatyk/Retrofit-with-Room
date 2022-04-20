package com.example.restapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.restapp.network.Comment


@Database(entities = [Comment::class], version = 2, exportSchema = false)
abstract class CommentDatabase: RoomDatabase() {
    abstract fun commentDao(): CommentDao

    companion object {
        @Volatile
        private var INSTANCE: CommentDatabase? = null

        fun getDatabase(context: Context): CommentDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CommentDatabase::class.java,
                    "comment_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}