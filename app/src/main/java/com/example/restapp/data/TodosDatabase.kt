package com.example.restapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.restapp.network.Todos

@Database(entities = [Todos::class], version = 2, exportSchema = false)
abstract class TodosDatabase: RoomDatabase() {
    abstract fun todosDao(): TodosDao

    companion object {
        @Volatile
        private var INSTANCE: TodosDatabase? = null

        fun getDatabase(context: Context): TodosDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodosDatabase::class.java,
                    "todos_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}