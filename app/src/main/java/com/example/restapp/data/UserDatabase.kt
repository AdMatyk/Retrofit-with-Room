package com.example.restapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.restapp.network.User

@Database(entities = [User::class], version = 2, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
        abstract fun userDao(): UserDao

        companion object {
            @Volatile
            private var INSTANCE: UserDatabase? = null

            fun getDatabase(context: Context): UserDatabase {
                // if the INSTANCE is not null, then return it,
                // if it is, then create the database
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                    // return instance
                    instance
                }
            }
        }
    }
